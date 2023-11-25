DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TYPE TypeProfil AS ENUM ('UTILISATEUR','ADMINISTRATEUR');
CREATE TYPE StatutEmprunt AS ENUM ('R_ATTENTE','R_CONFIRME','R_ANNULE','E_ENCOURS','E_TERMINE','R_RETARD');

CREATE TABLE IF NOT EXISTS TypeMedia
(
    idType            SERIAL PRIMARY KEY,
    designationType   VARCHAR(255) UNIQUE NOT NULL,
    designationAuteur VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS NationaliteAuteur
(
    idNationalite SERIAL PRIMARY KEY,
    designation   VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS GenreMedia
(
    idGenre     SERIAL PRIMARY KEY,
    designation VARCHAR(255) UNIQUE NOT NULL
);
CREATE TABLE IF NOT EXISTS Auteur
(
    idAuteur          SERIAL PRIMARY KEY,
    nomAuteur         VARCHAR(255) NOT NULL,
    prenomAuteur      VARCHAR(255),
    nationaliteAuteur INT REFERENCES NationaliteAuteur (idNationalite)
);
CREATE TABLE IF NOT EXISTS Media
(
    idMedia                SERIAL PRIMARY KEY,
    titre                  VARCHAR(255) NOT NULL,
    idType                 SERIAL       NOT NULL REFERENCES TypeMedia (idType),
    idGenre                SERIAL       NOT NULL REFERENCES GenreMedia (idGenre),
    anneePublicationSortie INTEGER,
    noteMoyenne            FLOAT,
    imgMedia               VARCHAR(1000),
    disponible             BOOLEAN
);
CREATE TABLE IF NOT EXISTS ExemplaireMedia
(
    idExemplaire SERIAL PRIMARY KEY,
    idMedia      SERIAL NOT NULL REFERENCES Media (idMedia)
);
CREATE TABLE IF NOT EXISTS AuteurMedia
(
    idAuteur SERIAL REFERENCES Auteur (idAuteur) ON DELETE CASCADE,
    idMedia  SERIAL REFERENCES Media (idMedia) ON DELETE CASCADE,
    PRIMARY KEY (idAuteur, idMedia)
);
CREATE TABLE IF NOT EXISTS Utilisateur
(
    idUtilisateur            SERIAL PRIMARY KEY,
    emailUtilisateur         VARCHAR(255) UNIQUE NOT NULL,
    nomUtilisateur           VARCHAR(255)        NOT NULL,
    prenomUtilisateur        VARCHAR(255)        NOT NULL,
    adresseUtilisateur       VARCHAR(255)        NOT NULL,
    numTelephoneUtilisateur  VARCHAR(20) UNIQUE  NOT NULL,
    mdpUtilisateur           VARCHAR(255)        NOT NULL,
    dateNaissanceUtilisateur DATE                NOT NULL,
    typeProfil               TypeProfil          NOT NULL
);
CREATE TABLE IF NOT EXISTS Don
(
    idDon         SERIAL PRIMARY KEY,
    idUtilisateur SERIAL NOT NULL REFERENCES Utilisateur (idUtilisateur),
    idMedia       SERIAL NOT NULL REFERENCES Media (idMedia),
    dateDon       DATE   NOT NULL
);
CREATE TABLE IF NOT EXISTS Avis
(
    idAvis        SERIAL PRIMARY KEY,
    idUtilisateur SERIAL REFERENCES Utilisateur (idUtilisateur),
    idMedia       SERIAL REFERENCES Media (idMedia),
    commentaire   TEXT NOT NULL,
    note          FLOAT
);
CREATE TABLE IF NOT EXISTS Emprunt
(
    idEmprunt        SERIAL PRIMARY KEY,
    idUtilisateur    SERIAL        NOT NULL REFERENCES Utilisateur (idUtilisateur),
    dateReservation  DATE,
    dateEmprunt      DATE          NOT NULL,
    dateRetourPrevue DATE          NOT NULL,
    dateRetourReelle DATE,
    statutEmprunt    StatutEmprunt NOT NULL,
    idExemplaire     SERIAL        NOT NULL REFERENCES ExemplaireMedia (idExemplaire)
);

------ VUES ------

CREATE OR REPLACE VIEW MediasSouventEmpruntes AS
SELECT Media.idMedia, titre, COUNT(*) AS nombre_emprunts
FROM Emprunt
         JOIN ExemplaireMedia ON Emprunt.idExemplaire = ExemplaireMedia.idExemplaire
         JOIN Media ON ExemplaireMedia.idMedia = Media.idMedia
GROUP BY Media.idMedia, titre
ORDER BY nombre_emprunts DESC;


CREATE OR REPLACE VIEW ClassementMediasNoteMoyenne AS
SELECT RANK() OVER (ORDER BY noteMoyenne DESC) AS classement, idMedia, titre, noteMoyenne
FROM Media
ORDER BY noteMoyenne DESC;

CREATE OR REPLACE VIEW UtilisateursAdministrateurs AS
SELECT *
FROM Utilisateur
WHERE typeProfil = 'ADMINISTRATEUR';

CREATE OR REPLACE VIEW UtilisateursNormaux AS
SELECT *
FROM Utilisateur
WHERE typeProfil = 'UTILISATEUR';

------ FONCTIONS & TRIGGERS ------

CREATE OR REPLACE FUNCTION calculMoyenne() RETURNS TRIGGER AS
$$
BEGIN
    UPDATE Media
    SET noteMoyenne = (SELECT AVG(note) FROM Avis WHERE idMedia = NEW.idMedia)
    WHERE idMedia = NEW.idMedia;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION verifDisponibiliteMedia(id_media INT) RETURNS BOOLEAN AS
$$
BEGIN
    RETURN EXISTS (SELECT 1
                   FROM ExemplaireMedia
                   WHERE idMedia = id_media
                     AND idExemplaire NOT IN
                         (SELECT idExemplaire FROM Emprunt WHERE statutEmprunt NOT IN ('E_TERMINE', 'R_ANNULE')));
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION setDisponibiliteMedia() RETURNS TRIGGER AS
$$
BEGIN
    UPDATE Media
    SET disponible = EXISTS (SELECT 1
                             FROM ExemplaireMedia
                             WHERE idMedia = NEW.idMedia
                               AND (idExemplaire NOT IN (SELECT idExemplaire FROM Emprunt) OR idExemplaire IN
                                                                                              (SELECT idExemplaire
                                                                                               FROM Emprunt
                                                                                               WHERE statutEmprunt IN ('R_ANNULE', 'E_TERMINE'))))
    WHERE idMedia = NEW.idMedia;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER majMoyenne
    AFTER INSERT OR UPDATE
    ON Avis
    FOR EACH ROW
EXECUTE FUNCTION calculMoyenne();

CREATE OR REPLACE TRIGGER majDisponibilite
    AFTER INSERT OR UPDATE
    ON Emprunt
    FOR EACH ROW
EXECUTE PROCEDURE setDisponibiliteMedia();

CREATE OR REPLACE FUNCTION verifierEmpruntsAvantInsertion() RETURNS TRIGGER AS $$
DECLARE
    toutesCopiesReserveesOuEmpruntees BOOLEAN;
BEGIN
    -- Vérifiez si tous les exemplaires du média sont empruntés ou réservés (sauf si la réservation est annulée)
    SELECT NOT EXISTS (
        SELECT 1 FROM ExemplaireMedia WHERE idMedia = NEW.idMedia AND idExemplaire NOT IN (
            SELECT idExemplaire FROM Emprunt WHERE NOT (statutEmprunt = 'R_ANNULE' OR statutEmprunt = 'E_TERMINE')
        )
    ) INTO toutesCopiesReserveesOuEmpruntees;

    -- Si tous les exemplaires sont empruntés ou réservés, créez un nouvel emprunt avec le statut 'R_ENATTENTE'
    IF toutesCopiesReserveesOuEmpruntees THEN
        INSERT INTO Emprunt (idUtilisateur, dateReservation, dateEmprunt, dateRetourPrevue, statutEmprunt, idExemplaire)
        VALUES (NEW.idUtilisateur, CURRENT_DATE, NULL, NULL, 'R_ENATTENTE', NEW.idExemplaire);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER empruntAvantInsertion
    BEFORE INSERT ON Emprunt
    FOR EACH ROW EXECUTE PROCEDURE verifierEmpruntsAvantInsertion();

CREATE OR REPLACE PROCEDURE retourExemplaire(IN idExemplaireParam INTEGER)
    LANGUAGE plpgsql
AS $$
BEGIN
    -- Mettez à jour le statut de l'emprunt en 'E_TERMINE' et la date de retour réelle
    UPDATE Emprunt
    SET statutEmprunt = 'E_TERMINE',
        dateRetourReelle = CURRENT_DATE
    WHERE idExemplaire = idExemplaireParam AND statutEmprunt = 'E_ENCOURS';

    -- Trouvez la première réservation en attente pour le média
    WITH reservationEnAttente AS (
        SELECT idEmprunt
        FROM Emprunt
        WHERE idExemplaire = idExemplaireParam AND statutEmprunt = 'R_ENATTENTE'
        ORDER BY dateReservation
        LIMIT 1
    )
    -- Si une réservation en attente existe, mettez à jour son statut en 'R_CONFIRME'
    UPDATE Emprunt
    SET statutEmprunt = 'R_CONFIRME'
    WHERE idEmprunt IN (TABLE reservationEnAttente);
END;
$$;

CREATE OR REPLACE PROCEDURE empruntExemplaire(IN idUtilisateurParam INTEGER)
    LANGUAGE plpgsql
AS $$
DECLARE
    idExemplaireDisponible INTEGER;
BEGIN
    -- Trouvez le premier exemplaire disponible
    SELECT idExemplaire INTO idExemplaireDisponible
    FROM ExemplaireMedia
    WHERE idExemplaire NOT IN (
        SELECT idExemplaire FROM Emprunt WHERE NOT (statutEmprunt = 'R_ANNULE' OR statutEmprunt = 'E_TERMINE')
    )
    LIMIT 1;

    -- Si un exemplaire est disponible, créez un nouvel emprunt avec le statut 'E_ENCOURS'
    IF idExemplaireDisponible IS NOT NULL THEN
        INSERT INTO Emprunt (idUtilisateur, dateReservation, dateEmprunt, dateRetourPrevue, statutEmprunt, idExemplaire)
        VALUES (idUtilisateurParam, CURRENT_DATE, CURRENT_DATE, CURRENT_DATE + INTERVAL '14 days', 'E_ENCOURS', idExemplaireDisponible);
        -- Sinon, créez un nouvel emprunt avec le statut 'R_ENATTENTE'
    ELSE
        INSERT INTO Emprunt (idUtilisateur, dateReservation, dateEmprunt, dateRetourPrevue, statutEmprunt, idExemplaire)
        VALUES (idUtilisateurParam, CURRENT_DATE, NULL, NULL, 'R_ENATTENTE', idExemplaireDisponible);
    END IF;
END;
$$;



CREATE OR REPLACE PROCEDURE ajouterTypes(nouveaux_types VARCHAR[], designations_auteur VARCHAR[]) AS
$$
DECLARE
    i INT;
BEGIN
    FOR i IN 1..array_length(nouveaux_types, 1)
        LOOP
            INSERT INTO TypeMedia (designationType, designationAuteur)
            VALUES (nouveaux_types[i], designations_auteur[i]);
        END LOOP;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE ajouterGenres(nouveaux_genres VARCHAR[]) AS
$$
DECLARE
    i INT;
BEGIN
    FOR i IN 1..array_length(nouveaux_genres, 1)
        LOOP
            INSERT INTO GenreMedia (designation) VALUES (nouveaux_genres[i]);
        END LOOP;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE ajouterAuteursAMedias(idsMedia VARCHAR[], idsAuteur VARCHAR[]) AS
$$
DECLARE
    i INT;
BEGIN
    FOR i IN 1..array_length(idsMedia, 1)
        LOOP
            INSERT INTO AuteurMedia (idMedia, idAuteur)
            VALUES (idsMedia[i], idsAuteur[i]);
        END LOOP;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION checkEmpruntBeforeDelete() RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM Emprunt WHERE idExemplaire = OLD.idExemplaire AND NOT (statutEmprunt = 'R_ANNULE' OR statutEmprunt = 'E_TERMINE')) THEN
        RAISE EXCEPTION 'Cet exemplaire est actuellement emprunté et ne peut pas être supprimé';
    END IF;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER exemplaire_before_delete
    BEFORE DELETE ON ExemplaireMedia
    FOR EACH ROW EXECUTE PROCEDURE checkEmpruntBeforeDelete();
------ INSERTIONS ------

INSERT INTO NationaliteAuteur(designation)
VALUES ('Anglais'),
       ('Français'),
       ('Américain'),
       ('Canadien'),
       ('Suédois'),
       ('Australien'),
       ('Néo-Zélandais');
INSERT INTO TypeMedia(designationType, designationAuteur)
VALUES ('Musique', 'Compositeur'),
       ('Film', 'Réalisateur'),
       ('Série TV', 'Créateur'),
       ('Livre', 'Auteur');
;
INSERT INTO GenreMedia(designation)
VALUES ('Rock'),
       ('Rap'),
       ('Pop'),
       ('Country'),
       ('Comédie'),
       ('Drame'),
       ('Thriller'),
       ('Fantastique'),
       ('Pop rock'),
       ('Fantasy'),
       ('Science-fiction');
INSERT INTO Auteur(nomAuteur, prenomAuteur, nationaliteAuteur)
VALUES ('Wilder', 'Alan', 1),
       ('L Gore', 'Martin', 1),
       ('Gahan', 'Dave', 1),
       ('Fletcher', 'Andrew', 1),
       ('Clarke', 'Vince', 1),
       ('Booba', '', 2),
       ('Tarantino', 'Quentin', 3),
       ('Cohen', 'Ethan', 3),
       ('Cohen', 'Joel', 3),
       ('Swift', 'Taylor', 3),
       ('Drake', '', 4),
       ('Larsson', 'Stieg', 5),
       ('Miller', 'George', 6),
       ('Martin', 'George R. R.', 3),
       ('Lennon', 'John', 1),
       ('McCartney', 'Paul', 1),
       ('Tolkien', 'J. R. R.', 1),
       ('Lucas', 'George', 3),
       ('Jackson', 'Peter', 7);
INSERT INTO Media(titre,
                  idType,
                  idGenre,
                  anneePublicationSortie,
                  noteMoyenne,
                  imgMedia,
                  disponible)
VALUES ('Speak & Spell', 1, 1, 1981, 5,
        'https://ia800908.us.archive.org/27/items/mbid-f1a3f01c-77e9-40f0-904c-6728330ec57c/mbid-f1a3f01c-77e9-40f0-904c-6728330ec57c-8343767175.jpg',
        false),
       ('A Broken Frame', 1, 1, 1982, 5,
        'https://ia803203.us.archive.org/8/items/mbid-01ca78a3-0722-4c7b-971f-e174e5d2d1bc/mbid-01ca78a3-0722-4c7b-971f-e174e5d2d1bc-4058211639.jpg',
        false),
       ('Construction Time Again', 1, 1, 1983, 5,
        'https://ia903206.us.archive.org/10/items/mbid-b8cdaddb-d19e-41fa-8b93-fba58d160d4b/mbid-b8cdaddb-d19e-41fa-8b93-fba58d160d4b-4135544853.jpg',
        false),
       ('Some Great Reward', 1, 1, 1984, 5,
        'https://ia800900.us.archive.org/25/items/mbid-3e3a9c1d-aee4-4f73-9376-fbe70e8366d8/mbid-3e3a9c1d-aee4-4f73-9376-fbe70e8366d8-9000459494.jpg',
        false),
       ('Black Celebration', 1, 1, 1986, 5,
        'https://ia800606.us.archive.org/12/items/mbid-f3afa63c-dd50-4408-8a78-f90ce2afb529/mbid-f3afa63c-dd50-4408-8a78-f90ce2afb529-1916133301.jpg',
        false),
       ('Music For The Masses', 1, 1, 1987, 5,
        'https://ia800609.us.archive.org/24/items/mbid-8d059e75-d9bb-4d90-97a9-1cb6ed7472c6/mbid-8d059e75-d9bb-4d90-97a9-1cb6ed7472c6-9552817368.jpg',
        false),
       ('Violator', 1, 1, 1990, 5,
        'https://ia600600.us.archive.org/35/items/mbid-5454efa9-445a-4b73-8d16-e9fdbf022119/mbid-5454efa9-445a-4b73-8d16-e9fdbf022119-18529866710.jpg',
        false),
       ('Songs Of Faith And Devotion', 1, 1, 1993, 5,
        'https://ia804706.us.archive.org/15/items/mbid-13ee7e6b-2f9a-30af-b4bb-2c2dbde12c44/mbid-13ee7e6b-2f9a-30af-b4bb-2c2dbde12c44-20967138423.jpg',
        false),
       ('Ultra', 1, 1, 1997, 5,
        'https://ia801307.us.archive.org/18/items/mbid-3e7e0454-c2b2-3976-b3d1-c9cdacff51ef/mbid-3e7e0454-c2b2-3976-b3d1-c9cdacff51ef-11289364430.jpg',
        false),
       ('Exciter', 1, 1, 2001, 5,
        'https://ia800307.us.archive.org/24/items/mbid-2689a2ed-19b4-4095-8c77-f2e24e915962/mbid-2689a2ed-19b4-4095-8c77-f2e24e915962-22524388802.jpg',
        false),
       ('Playing The Angel', 1, 1, 2005, 5,
        'https://ia803207.us.archive.org/21/items/mbid-f0509d77-568b-42db-98cd-bc46e03c0312/mbid-f0509d77-568b-42db-98cd-bc46e03c0312-26223233842.jpg',
        false),
       ('Sounds Of The Universe', 1, 1, 2009, 5,
        'https://ia802900.us.archive.org/28/items/mbid-035f7624-347d-44f8-9bb9-3e9b9ce9ca62/mbid-035f7624-347d-44f8-9bb9-3e9b9ce9ca62-1932442965.jpg',
        false),
       ('Delta Machine', 1, 1, 2013, 5,
        'https://ia801904.us.archive.org/22/items/mbid-d5afe106-4b6d-45b2-a1c9-00d591ca905f/mbid-d5afe106-4b6d-45b2-a1c9-00d591ca905f-3698508740.jpg',
        false),
       ('Spirit', 1, 1, 2017, 5,
        'https://ia801409.us.archive.org/28/items/mbid-d2f52f4a-d7df-46b3-9d6f-c60757d8326c/mbid-d2f52f4a-d7df-46b3-9d6f-c60757d8326c-16231834931.jpg',
        false),
       ('Memento Mori', 1, 1, 2023, 5,
        'https://ia801602.us.archive.org/33/items/mbid-b3fdc1ce-7561-45a2-9a81-853f988859c8/mbid-b3fdc1ce-7561-45a2-9a81-853f988859c8-34849885476.jpg',
        false),
       ('ULTRA', 1, 1, 2015, 0,
        'https://images.genius.com/0b8290c44d2de7466237e6797139a5f3.1000x1000x1.jpg', false),
       ('Pulp Fiction', 2, 4, 1994, 5, '', false),
       ('The Big Lebowski', 2, 4, 1998, 5, '', false),
       ('1989', 1, 3, 2014, 5, '', false),
       ('Scorpion', 1, 3, 2018, 5, '', false),
       ('Millennium', 2, 4, 2005, 5, '', false),
       ('Mad Max: Fury Road', 2, 4, 2015, 5, '', false),
       ('Game of Thrones', 3, 3, 2011, 5, '', false),
       ('Abbey Road', 1, 5, 1969, 5, '', false),
       ('Le Seigneur des Anneaux', 4, 6, 1954, 5, '', false),
       ('Star Wars, épisode IV : Un nouvel espoir', 2, 7, 1977, 5, '', false),
       ('Le Seigneur des Anneaux : La Communauté de l''Anneau', 2, 6, 2001, 5, '', false);
;
INSERT INTO AuteurMedia(idAuteur, idMedia)
VALUES (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (1, 2),
       (2, 2),
       (3, 2),
       (4, 2),
       (1, 3),
       (2, 3),
       (3, 3),
       (4, 3),
       (1, 4),
       (2, 4),
       (3, 4),
       (4, 4),
       (1, 5),
       (2, 5),
       (3, 5),
       (4, 5),
       (1, 6),
       (2, 6),
       (3, 6),
       (4, 6),
       (1, 7),
       (2, 7),
       (3, 7),
       (4, 7),
       (1, 8),
       (2, 8),
       (3, 8),
       (4, 8),
       (2, 9),
       (3, 9),
       (4, 9),
       (2, 10),
       (3, 10),
       (4, 10),
       (2, 11),
       (3, 11),
       (4, 11),
       (2, 12),
       (3, 12),
       (4, 12),
       (2, 13),
       (3, 13),
       (4, 13),
       (2, 14),
       (3, 14),
       (4, 14),
       (2, 15),
       (3, 15),
       (4, 15),
       (6, 16),
       (7, 17),
       (8, 18),
       (9, 18),
       (10, 19),
       (11, 20),
       (12, 21),
       (13, 22),
       (14, 23),
       (15, 24),
       (16, 24),
       (17, 25),
       (18, 26),
       (19, 27);

INSERT INTO Utilisateur (emailUtilisateur, nomUtilisateur, prenomUtilisateur, adresseUtilisateur,
                         numTelephoneUtilisateur, mdpUtilisateur, dateNaissanceUtilisateur, typeProfil)
VALUES ('davegahan@local.int', 'Gahan', 'Dave', 'Basildon', '20231981',
        '$2y$10$Xa6bJiw4K9Ux8FUiz7r0n.DLed8imaKPDteU5Wgar7KD1vgQm1FTO', '1962-05-09', 'ADMINISTRATEUR');
