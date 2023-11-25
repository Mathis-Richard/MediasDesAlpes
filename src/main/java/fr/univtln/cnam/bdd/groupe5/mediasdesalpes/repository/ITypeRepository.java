package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.TypeMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITypeRepository extends JpaRepository<TypeMediaEntity,Long> {

    @Query(value = "SELECT * FROM typemedia",
            nativeQuery = true)
    List<TypeMediaEntity> getTypesMedia();
}
