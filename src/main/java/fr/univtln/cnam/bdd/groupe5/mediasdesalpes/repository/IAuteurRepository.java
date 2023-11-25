package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.AuteurEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuteurRepository extends JpaRepository<AuteurEntity, Long> {

    @Query(value = "SELECT * FROM Auteur WHERE idauteur = :id", nativeQuery = true)
    AuteurEntity getAuteurById(Integer id);

    @Query(value = "SELECT a.* FROM Auteur a " +
            "JOIN AuteurMedia am ON a.idauteur = am.idauteur " +
            "WHERE am.idmedia = :id",
            nativeQuery = true)
    List<AuteurEntity> getAuteursByMediaId(Integer id);

    @Query(value = "SELECT * FROM Auteur", nativeQuery = true)
    List<AuteurEntity> getAuteurs();

    @Query(value = "INSERT INTO Auteur(nomAuteur, prenomAuteur, nationaliteAuteur) VALUES (:nomAuteur, :prenomAuteur, :nationaliteAuteur)", nativeQuery = true)
    @Modifying
    Integer insertAuteur(String nomAuteur, String prenomAuteur, Integer nationaliteAuteur);

    @Query(value = "UPDATE Auteur " +
            "SET nomAuteur = :nomAuteur, prenomAuteur = :prenomAuteur, nationaliteAuteur = :nationaliteAuteur " +
            "WHERE idAuteur = :idAuteur", nativeQuery = true)
    @Modifying
    Integer updateAuteur(Integer idAuteur, String nomAuteur, String prenomAuteur, Integer nationaliteAuteur);

    @Query(value = "DELETE FROM Auteur WHERE idauteur = :id", nativeQuery = true)
    @Modifying
    Integer deleteAuteur(Integer id);
}
