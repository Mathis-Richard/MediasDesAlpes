package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.GenreMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGenreRepository extends JpaRepository<GenreMediaEntity,Long> {

    @Query(value = "SELECT idgenre,designation FROM genremedia",
            nativeQuery = true)
    List<GenreMediaEntity> getGenresMedia();
}
