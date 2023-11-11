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
    idType                 SERIAL REFERENCES TypeMedia (idType),
    idGenre                SERIAL REFERENCES GenreMedia (idGenre),
    anneePublicationSortie INTEGER,
    noteMedia              FLOAT,
    imgMedia               VARCHAR(500)
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
       (2, 'L Gore', 'Martin', 1);
INSERT INTO Media(idMedia,
                  titre,
                  idType,
                  idGenre,
                  anneePublicationSortie,
                  noteMedia,
                  imgMedia)
VALUES (1, 'Violator', 1, 1, 1990, 5,
        'https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/714McwlTjNL._UF1000,1000_QL80_.jpg');
INSERT INTO AuteurMedia(idAuteur, idMedia)
VALUES (1, 1),
       (2, 1);