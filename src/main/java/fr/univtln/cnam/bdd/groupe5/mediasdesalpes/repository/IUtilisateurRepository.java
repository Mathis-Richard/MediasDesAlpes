package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.UtilisateurEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface IUtilisateurRepository extends JpaRepository<UtilisateurEntity,Long> {

    @Query(value = "SELECT * FROM utilisateur WHERE emailutilisateur LIKE :email", nativeQuery = true)
    UtilisateurEntity findByEmail(String email);

    @Modifying
    @Query(value = "INSERT INTO utilisateur (emailUtilisateur, nomUtilisateur, prenomUtilisateur, adresseUtilisateur, numTelephoneUtilisateur, mdpUtilisateur, dateNaissanceUtilisateur, typeProfil)" +
            "VALUES (:emailutilisateur, :nomutilisateur, :prenomutilisateur, :adresseutilisateur, :numtelephoneutilisateur, :mdputilisateur, :datenaissanceutilisateur, 'UTILISATEUR')", nativeQuery = true)
    Integer createUser(String emailutilisateur, String nomutilisateur, String prenomutilisateur, String adresseutilisateur, String numtelephoneutilisateur, String mdputilisateur, Date datenaissanceutilisateur);
}
