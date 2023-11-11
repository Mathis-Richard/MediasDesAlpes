------ TABLES ------

CREATE TABLE IF NOT EXISTS TypeProfil
(
    idType      INT PRIMARY KEY,
    designation VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS TypeMedia
(
    idType            INT PRIMARY KEY,
    nomType           VARCHAR(255),
    designationAuteur VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS StatutMedia
(
    idStatut    INT PRIMARY KEY,
    designation VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS StatutReservation
(
    idStatut    INT PRIMARY KEY,
    designation VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS StatutEmprunt
(
    idStatut    INT PRIMARY KEY,
    designation VARCHAR(30)
);


CREATE TABLE IF NOT EXISTS Utilisateur
(
    idUtilisateur        INT PRIMARY KEY,
    nomUtilisateur       VARCHAR(255) NOT NULL,
    prenomUtilisateur    VARCHAR(255) NOT NULL,
    adresseUtilisateur   VARCHAR(255) NOT NULL,
    numeroTelUtilisateur VARCHAR(20)  NOT NULL,
    emailUtilisateur     VARCHAR(255) NOT NULL,
    mdpUtilisateur       VARCHAR(255) NOT NULL,
    typeProfil           INT          NOT NULL REFERENCES TypeProfil (idType)
);

CREATE TABLE IF NOT EXISTS Auteur
(
    idAuteur          INT PRIMARY KEY,
    nomAuteur         VARCHAR(255),
    prenomAuteur      VARCHAR(255),
    nationaliteAuteur VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS AuteurMedia
(
    idAuteur INT REFERENCES Auteur (idAuteur),
    idMedia  INT REFERENCES Media (idMedia),
    PRIMARY KEY (idAuteur, idMedia)
);

CREATE TABLE IF NOT EXISTS Don
(
    idDon            INT PRIMARY KEY,
    emailUtilisateur VARCHAR(255) REFERENCES Utilisateur (idUtilisateur),
    idMedia          INT  NOT NULL REFERENCES Media (idMedia),
    dateDon          DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS Avis
(
    idAvis           INT PRIMARY KEY,
    emailUtilisateur VARCHAR(255) REFERENCES Utilisateur (idUtilisateur),
    idMedia          INT REFERENCES Media (idMedia),
    commentaire      TEXT,
    note             INTEGER
);

CREATE TABLE IF NOT EXISTS Genre
(
    idGenre  INT PRIMARY KEY,
    nomGenre VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Media
(
    idMedia                INT PRIMARY KEY,
    titre                  VARCHAR(255),
    idType                 INT REFERENCES TypeMedia (idType),
    idGenre                INT REFERENCES Genre (idGenre),
    idAuteur               INT REFERENCES Auteur (idAuteur),
    anneePublicationSortie INTEGER
);

CREATE TABLE IF NOT EXISTS Emprunt
(
    idEmprunt        INT PRIMARY KEY,
    emailUtilisateur VARCHAR(255) REFERENCES Utilisateur (emailUtilisateur),
    dateEmprunt      DATE,
    dateRetourPrevue DATE,
    dateRetourReelle DATE,
    statutEmprunt    VARCHAR(50),
    dateReservation  DATE,
    idExemplaire     INT REFERENCES ExemplaireMedia (idExemplaire)
);

CREATE TABLE IF NOT EXISTS ExemplaireMedia
(
    idExemplaire INT PRIMARY KEY,
    idMedia      INT REFERENCES Media (idMedia)
);


------ INSERTIONS ------

INSERT INTO TypeProfil (id, designation)
VALUES (0, 'Utilisateur'),
       (1, 'Admin');
INSERT INTO StatutMedia (id, designation)
VALUES (0, 'Disponible'),
       (1, 'Réservé'),
       (2, 'Emprunté');
INSERT INTO StatutEmprunt (id, designation)
VALUES (0, 'En cours'),
       (1, 'Terminé'),
       (2, 'En retard');
INSERT INTO StatutReservation (id, designation)
VALUES (0, 'En attente'),
       (1, 'Confirmée'),
       (2, 'Annulée');