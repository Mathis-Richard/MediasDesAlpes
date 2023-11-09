CREATE TABLE Utilisateurs (
                              idUtilisateur INT PRIMARY KEY,
                              nom VARCHAR(255) NOT NULL,
                              prenom VARCHAR(255) NOT NULL,
                              adresse VARCHAR(255) NOT NULL,
                              numeroDeTelephone VARCHAR(20) NOT NULL,
                              adresseEmail VARCHAR(255) NOT NULL,
                              motDePasse VARCHAR(255) NOT NULL,
                              typeDeProfil INT NOT NULL,
                              CONSTRAINT fk_TypeProfil
                                  FOREIGN KEY(typeDeProfil)
                                  REFERENCES TypesProfil(id)
);
CREATE TABLE Medias (
                        idMedia INT PRIMARY KEY,
                        titre VARCHAR(255) NOT NULL,
                        auteurRealisateur VARCHAR(255) NOT NULL,
                        anneeDePublicationSortie INT NOT NULL,
                        typeDeMedia VARCHAR(30) NOT NULL,
                        quantiteDisponible INT CHECK (quantiteDisponible > 0),
                        statut VARCHAR(30) CHECK (statut IN ('Disponible', 'Emprunte', 'Reserve')),
                        urlImage VARCHAR(255) NOT NULL
);
CREATE TABLE Emprunts (
                          idEmprunt INT PRIMARY KEY,
                          idUtilisateur INT,
                          idMedia INT,
                          dateDemprunt DATE NOT NULL,
                          dateDeRetourPrevue DATE NOT NULL,
                          dateDeRetourReelle DATE,
                          statutDeLEmprunt VARCHAR(30) CHECK (statutDeLEmprunt IN ('En cours', 'Termine', 'En retard')),
                          FOREIGN KEY (idUtilisateur) REFERENCES Utilisateurs(idUtilisateur),
                          FOREIGN KEY (idMedia) REFERENCES Medias(idMedia)
);
CREATE TABLE Reservations (
                              idReservation INT PRIMARY KEY,
                              idUtilisateur INT,
                              idMedia INT,
                              dateDeReservation DATE NOT NULL,
                              statutDeLaReservation VARCHAR(30) CHECK (statutDeLaReservation IN ('En attente', 'Confirmee', 'Annulee')),
                              FOREIGN KEY (idUtilisateur) REFERENCES Utilisateurs(idUtilisateur),
                              FOREIGN KEY (idMedia) REFERENCES Medias(idMedia)
);
CREATE TABLE Avis (
                      idAvis INT PRIMARY KEY,
                      idUtilisateur INT,
                      idMedia INT,
                      commentaire TEXT,
                      note INT,
                      FOREIGN KEY (idUtilisateur) REFERENCES Utilisateurs(idUtilisateur),
                      FOREIGN KEY (idMedia) REFERENCES Medias(idMedia)
);
CREATE TABLE Dons (
                      idDon INT PRIMARY KEY,
                      idUtilisateur INT,
                      idMedia INT,
                      dateDuDon DATE NOT NULL,
                      FOREIGN KEY (idUtilisateur) REFERENCES Utilisateurs(idUtilisateur),
                      FOREIGN KEY (idMedia) REFERENCES Medias(idMedia)
);

CREATE TABLE TypesProfil (
    id INT PRIMARY KEY,
    designation VARCHAR(30)
)