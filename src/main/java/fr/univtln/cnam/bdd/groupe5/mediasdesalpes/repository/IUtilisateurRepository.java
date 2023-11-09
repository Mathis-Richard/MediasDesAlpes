package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUtilisateurRepository extends JpaRepository<Version,Long> {

    @Query(value = "SELECT nom FROM Utilisateurs", nativeQuery = true)
    List<String> getNom();

    @Query(value = "SELECT prenom FROM Utilisateurs", nativeQuery = true)
    List<String> getPrenom();
}
