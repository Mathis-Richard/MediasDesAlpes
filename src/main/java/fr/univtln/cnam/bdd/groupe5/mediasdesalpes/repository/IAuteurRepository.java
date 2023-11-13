package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.AuteurEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuteurRepository extends JpaRepository<AuteurEntity,Long> {

    @Query(value = "SELECT * FROM Auteur WHERE idauteur = :id", nativeQuery = true)
    AuteurEntity getAuteurById(Integer id);
    @Query(value = "SELECT a.* FROM Auteur a " +
            "JOIN AuteurMedia am ON a.idauteur = am.idauteur " +
            "WHERE am.idmedia = :id",
            nativeQuery = true)
    List<AuteurEntity> getAuteursByMediaId(Integer id);


}
