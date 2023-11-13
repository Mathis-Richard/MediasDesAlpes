DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE IF NOT EXISTS TypeProfil
(
    idType      INT PRIMARY KEY,
    designation VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS TypeMedia
(
    idType            INT PRIMARY KEY,
    designationType   VARCHAR(255) NOT NULL,
    designationAuteur VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS StatutEmprunt
(
    idStatut    INT PRIMARY KEY,
    designation VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS NationaliteAuteur
(
    idNationalite INT PRIMARY KEY,
    designation   VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS GenreMedia
(
    idGenre     SERIAL PRIMARY KEY,
    designation VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS Auteur
(
    idAuteur          SERIAL PRIMARY KEY,
    nomAuteur         VARCHAR(255) NOT NULL,
    prenomAuteur      VARCHAR(255),
    nationaliteAuteur INT          NOT NULL REFERENCES NationaliteAuteur (idNationalite)
);
CREATE TABLE IF NOT EXISTS Media
(
    idMedia                SERIAL PRIMARY KEY,
    titre                  VARCHAR(255),
    idType                 SERIAL NOT NULL REFERENCES TypeMedia (idType),
    idGenre                SERIAL NOT NULL REFERENCES GenreMedia (idGenre),
    anneePublicationSortie INTEGER,
    noteMedia              FLOAT,
    imgMedia               VARCHAR(1000)
);
CREATE TABLE IF NOT EXISTS ExemplaireMedia
(
    idExemplaire SERIAL PRIMARY KEY,
    idMedia      SERIAL NOT NULL REFERENCES Media (idMedia)
);
CREATE TABLE IF NOT EXISTS AuteurMedia
(
    idAuteur SERIAL REFERENCES Auteur (idAuteur),
    idMedia  SERIAL REFERENCES Media (idMedia),
    PRIMARY KEY (idAuteur, idMedia)
);
CREATE TABLE IF NOT EXISTS Utilisateur
(
    idUtilisateur           SERIAL PRIMARY KEY,
    emailUtilisateur        VARCHAR(255) NOT NULL,
    nomUtilisateur          VARCHAR(255) NOT NULL,
    prenomUtilisateur       VARCHAR(255) NOT NULL,
    adresseUtilisateur      VARCHAR(255) NOT NULL,
    numTelephoneUtilisateur VARCHAR(20)  NOT NULL,
    mdpUtilisateur          VARCHAR(255) NOT NULL,
    typeProfil              SERIAL       NOT NULL REFERENCES TypeProfil (idType)
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
    commentaire   TEXT,
    note          FLOAT
);
CREATE TABLE IF NOT EXISTS Emprunt
(
    idEmprunt        SERIAL PRIMARY KEY,
    idUtilisateur    SERIAL REFERENCES Utilisateur (idUtilisateur),
    dateReservation  DATE,
    dateEmprunt      DATE NOT NULL,
    dateRetourPrevue DATE NOT NULL,
    dateRetourReelle DATE,
    statutEmprunt    INT REFERENCES StatutEmprunt (idStatut),
    idExemplaire     SERIAL REFERENCES ExemplaireMedia (idExemplaire)
);

------ INSERTIONS ------

INSERT INTO TypeProfil (idType, designation)
VALUES (0, 'Utilisateur'),
       (1, 'Admin');
INSERT INTO StatutEmprunt (idStatut, designation)
VALUES (0, 'Réservation en attente'),
       (1, 'Réservation confirmée'),
       (2, 'Réservation annulée'),
       (3, 'Emprunt en cours'),
       (4, 'Emprunt terminé'),
       (5, 'Retour en retard');
INSERT INTO NationaliteAuteur(idNationalite, designation)
VALUES (1, 'Anglais');
INSERT INTO TypeMedia(idType, designationType, designationAuteur)
VALUES (1, 'Musique', 'Auteur');
INSERT INTO GenreMedia(idGenre, designation)
VALUES (1, 'Rock');
INSERT INTO Auteur(idAuteur, nomAuteur, prenomAuteur, nationaliteAuteur)
VALUES (1, 'Wilder', 'Alan', 1),
       (2, 'L Gore', 'Martin', 1),
       (3, 'Gahan', 'Dave', 1),
       (4, 'Fletcher', 'Andrew', 1),
       (5, 'Clarke', 'Vince', 1);;
INSERT INTO Media(idMedia,
                  titre,
                  idType,
                  idGenre,
                  anneePublicationSortie,
                  noteMedia,
                  imgMedia)
VALUES (1, 'Speak & Spell', 1, 1, 1981, 5,
        'https://ia800908.us.archive.org/27/items/mbid-f1a3f01c-77e9-40f0-904c-6728330ec57c/mbid-f1a3f01c-77e9-40f0-904c-6728330ec57c-8343767175.jpg'),
       (2, 'A Broken Frame', 1, 1, 1982, 5,
        'https://ia803203.us.archive.org/8/items/mbid-01ca78a3-0722-4c7b-971f-e174e5d2d1bc/mbid-01ca78a3-0722-4c7b-971f-e174e5d2d1bc-4058211639.jpg'),
       (3, 'Construction Time Again', 1, 1, 1983, 5,
        'https://ia903206.us.archive.org/10/items/mbid-b8cdaddb-d19e-41fa-8b93-fba58d160d4b/mbid-b8cdaddb-d19e-41fa-8b93-fba58d160d4b-4135544853.jpg'),
       (4, 'Some Great Reward', 1, 1, 1984, 5,
        'https://ia800900.us.archive.org/25/items/mbid-3e3a9c1d-aee4-4f73-9376-fbe70e8366d8/mbid-3e3a9c1d-aee4-4f73-9376-fbe70e8366d8-9000459494.jpg'),
       (5, 'Black Celebration', 1, 1, 1986, 5,
        'https://ia800606.us.archive.org/12/items/mbid-f3afa63c-dd50-4408-8a78-f90ce2afb529/mbid-f3afa63c-dd50-4408-8a78-f90ce2afb529-1916133301.jpg'),
       (6, 'Music For The Masses', 1, 1, 1987, 5,
        'https://ia800609.us.archive.org/24/items/mbid-8d059e75-d9bb-4d90-97a9-1cb6ed7472c6/mbid-8d059e75-d9bb-4d90-97a9-1cb6ed7472c6-9552817368.jpg'),
       (7, 'Violator', 1, 1, 1990, 5,
        'https://ia600600.us.archive.org/35/items/mbid-5454efa9-445a-4b73-8d16-e9fdbf022119/mbid-5454efa9-445a-4b73-8d16-e9fdbf022119-18529866710.jpg'),
       (8, 'Songs Of Faith And Devotion', 1, 1, 1993, 5,
        'https://ia804706.us.archive.org/15/items/mbid-13ee7e6b-2f9a-30af-b4bb-2c2dbde12c44/mbid-13ee7e6b-2f9a-30af-b4bb-2c2dbde12c44-20967138423.jpg'),
       (9, 'Ultra', 1, 1, 1997, 5,
        'https://ia801307.us.archive.org/18/items/mbid-3e7e0454-c2b2-3976-b3d1-c9cdacff51ef/mbid-3e7e0454-c2b2-3976-b3d1-c9cdacff51ef-11289364430.jpg'),
       (10, 'Exciter', 1, 1, 2001, 5,
        'https://ia800307.us.archive.org/24/items/mbid-2689a2ed-19b4-4095-8c77-f2e24e915962/mbid-2689a2ed-19b4-4095-8c77-f2e24e915962-22524388802.jpg'),
       (11, 'Playing The Angel', 1, 1, 2005, 5,
        'https://ia803207.us.archive.org/21/items/mbid-f0509d77-568b-42db-98cd-bc46e03c0312/mbid-f0509d77-568b-42db-98cd-bc46e03c0312-26223233842.jpg'),
       (12, 'Sounds Of The Universe', 1, 1, 2009, 5,
        'https://ia802900.us.archive.org/28/items/mbid-035f7624-347d-44f8-9bb9-3e9b9ce9ca62/mbid-035f7624-347d-44f8-9bb9-3e9b9ce9ca62-1932442965.jpg'),
       (13, 'Delta Machine', 1, 1, 2013, 5,
        'https://ia801904.us.archive.org/22/items/mbid-d5afe106-4b6d-45b2-a1c9-00d591ca905f/mbid-d5afe106-4b6d-45b2-a1c9-00d591ca905f-3698508740.jpg'),
       (14, 'Spirit', 1, 1, 2017, 5,
        'https://ia801409.us.archive.org/28/items/mbid-d2f52f4a-d7df-46b3-9d6f-c60757d8326c/mbid-d2f52f4a-d7df-46b3-9d6f-c60757d8326c-16231834931.jpg'),
       (15, 'Memento Mori', 1, 1, 2023, 5,
        'https://ia801602.us.archive.org/33/items/mbid-b3fdc1ce-7561-45a2-9a81-853f988859c8/mbid-b3fdc1ce-7561-45a2-9a81-853f988859c8-34849885476.jpg')
;
INSERT INTO AuteurMedia(idAuteur, idMedia)
VALUES (2,1),
       (3,1),
       (4,1),
       (5,1),
       (1,2),
       (2,2),
       (3,2),
       (4,2),
       (1,3),
       (2,3),
       (3,3),
       (4,3),
       (1,4),
       (2,4),
       (3,4),
       (4,4),
       (1,5),
       (2,5),
       (3,5),
       (4,5),
       (1,6),
       (2,6),
       (3,6),
       (4,6),
       (1,7),
       (2,7),
       (3,7),
       (4,7),
       (1,8),
       (2,8),
       (3,8),
       (4,8),
       (2,9),
       (3,9),
       (4,9),
       (2,10),
       (3,10),
       (4,10),
       (2,11),
       (3,11),
       (4,11),
       (2,12),
       (3,12),
       (4,12),
       (2,13),
       (3,13),
       (4,13),
       (2,14),
       (3,14),
       (4,14),
       (2,15),
       (3,15),
       (4,15);