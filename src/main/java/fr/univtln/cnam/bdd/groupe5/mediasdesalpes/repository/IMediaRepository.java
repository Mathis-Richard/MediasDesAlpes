package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMediaRepository extends JpaRepository<MediaEntity,Long> {

    @Query(value = "SELECT * FROM Media WHERE idmedia = :id", nativeQuery = true)
    MediaEntity getMediaById(Integer id);

    @Query(value = "SELECT * FROM Media", nativeQuery = true)
    List<MediaEntity> getMedia();
}
