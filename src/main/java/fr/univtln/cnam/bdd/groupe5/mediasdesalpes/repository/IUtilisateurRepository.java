package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface IUtilisateurRepository extends JpaRepository<UtilisateurEntity,Long> {

    @Query(value = "SELECT * FROM utilisateur WHERE emailutilisateur LIKE :email", nativeQuery = true)
    UtilisateurEntity getUserByEmail(String email);
    @Query(value = "SELECT * FROM utilisateur WHERE idutilisateur = :id", nativeQuery = true)
    UtilisateurEntity getUserById(Integer id);

    @Query(value = "SELECT * FROM utilisateur", nativeQuery = true)
    List<UtilisateurEntity> getUsers();

    @Query(value = "SELECT * FROM utilisateur LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<UtilisateurEntity> getUsersLimitAndPage(Integer limit, Integer offset);

    @Modifying
    @Query(value = "INSERT INTO utilisateur (emailUtilisateur, nomUtilisateur, prenomUtilisateur, adresseUtilisateur, numTelephoneUtilisateur, mdpUtilisateur, dateNaissanceUtilisateur, typeProfil)" +
            "VALUES (:emailutilisateur, :nomutilisateur, :prenomutilisateur, :adresseutilisateur, :numtelephoneutilisateur, :mdputilisateur, :datenaissanceutilisateur, CAST(:typeProfil AS typeprofil))", nativeQuery = true)
    Integer createUser(String emailutilisateur, String nomutilisateur, String prenomutilisateur, String adresseutilisateur, String numtelephoneutilisateur, String mdputilisateur, Date datenaissanceutilisateur, String typeProfil);

    @Modifying
    @Query(value = "DELETE FROM utilisateur WHERE emailutilisateur = :email", nativeQuery = true)
    Integer deleteUser(String email);

    @Modifying
    @Query(value = "DELETE FROM utilisateur WHERE idutilisateur = :id", nativeQuery = true)
    Integer deleteUserById(Integer id);

    @Modifying
    @Query(value = "UPDATE utilisateur " +
            "SET emailutilisateur = :emailutilisateur, nomutilisateur = :nomutilisateur, prenomutilisateur = :prenomutilisateur, adresseutilisateur = :adresseutilisateur, numtelephoneutilisateur = :numtelephoneutilisateur, mdputilisateur = :mdputilisateur, datenaissanceutilisateur = :datenaissanceutilisateur, typeProfil = CAST(:typeProfil AS typeprofil) " +
            "WHERE emailutilisateur= :email", nativeQuery = true)
    Integer updateUser(String email, String emailutilisateur, String nomutilisateur, String prenomutilisateur, String adresseutilisateur, String numtelephoneutilisateur, String mdputilisateur, Date datenaissanceutilisateur, String typeProfil);

    @Modifying
    @Query(value = "UPDATE utilisateur " +
            "SET emailutilisateur = :emailutilisateur, nomutilisateur = :nomutilisateur, prenomutilisateur = :prenomutilisateur, adresseutilisateur = :adresseutilisateur, numtelephoneutilisateur = :numtelephoneutilisateur, mdputilisateur = :mdputilisateur, datenaissanceutilisateur = :datenaissanceutilisateur, typeProfil = CAST(:typeProfil AS typeprofil) " +
            "WHERE idutilisateur = :idutilisateur", nativeQuery = true)
    Integer updateUserById(Integer idutilisateur, String emailutilisateur, String nomutilisateur, String prenomutilisateur, String adresseutilisateur, String numtelephoneutilisateur, String mdputilisateur, Date datenaissanceutilisateur, String typeProfil);

}
