package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.EmpruntEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpruntRepository extends JpaRepository<EmpruntEntity, Long> {

    @Query(value = "CALL empruntExemplaire(:idUtilisateur,:idExemplaire)", nativeQuery = true)
    @Modifying
    Integer startEmprunt(Integer idUtilisateur, Integer idExemplaire);

    @Query(value = "CALL retourExemplaire(:idExemplaire)", nativeQuery = true)
    @Modifying
    Integer returnEmprunt(Integer idExemplaire);

    @Query(value = "CALL annulerReservation(:idUtilisateur,:idExemplaire)", nativeQuery = true)
    @Modifying
    Integer cancelEmprunt(Integer idUtilisateur, Integer idExemplaire);

    @Query(value = "SELECT * FROM emprunt", nativeQuery = true)
    List<EmpruntEntity> getEmprunts();
    @Query(value = "SELECT * FROM emprunt WHERE statutemprunt = 'E_ENCOURS'", nativeQuery = true)
    List<EmpruntEntity> getEmpruntsEncours();
    @Query(value = "SELECT * FROM emprunt WHERE statutemprunt = 'E_TERMINE'", nativeQuery = true)
    List<EmpruntEntity> getEmpruntTermine();
    @Query(value = "SELECT * FROM emprunt WHERE statutemprunt = 'R_ANNULE'", nativeQuery = true)
    List<EmpruntEntity> getEmpruntAnnule();
    @Query(value = "SELECT * FROM emprunt WHERE statutemprunt = 'R_ATTENTE'", nativeQuery = true)
    List<EmpruntEntity> getEmpruntAttente();
}
