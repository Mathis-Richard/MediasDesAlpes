package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestRepository extends JpaRepository<Test,Integer> {

    @Query(value = "SELECT version()", nativeQuery = true)
    String getVersion();

    @Query(value = "SELECT * FROM coucou", nativeQuery = true)
    Integer getTest();
}
