package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.GenreMediaEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.MediaEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.TypeMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMediaRepository extends JpaRepository<MediaEntity,Long> {

    @Query(value = "SELECT * FROM media", nativeQuery = true)
    List<MediaEntity> getMedia();
    @Query(value = "SELECT * FROM media ORDER BY noteMoyenne DESC", nativeQuery = true)
    List<MediaEntity> getMediaClassement();
    @Query(value = "SELECT * FROM media LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<MediaEntity> getMediaLimitAndPage(Integer limit, Integer offset);
    @Query(value = "SELECT * FROM media WHERE idmedia = :id", nativeQuery = true)
    MediaEntity getMediaById(Integer id);

    @Modifying
    @Query(value = "DELETE FROM media WHERE idmedia = :id", nativeQuery = true)
    Integer deleteMediaById(Integer id);

    @Modifying
    @Query(value = "INSERT INTO media(titre,idType,idGenre,anneePublicationSortie,noteMoyenne,imgMedia) " +
            "VALUES (:titre, :idType, :idGenre, :anneePublicationSortie, 0, :imgMedia)",
            nativeQuery = true)
    Integer insertMedia(String titre, Integer idType, Integer idGenre, Integer anneePublicationSortie, String imgMedia);

    @Modifying
    @Query(value = "UPDATE media " +
            "SET titre = :titre, idType = :idType, idGenre = :idGenre, anneePublicationSortie = :anneePublicationSortie, imgMedia = :imgMedia " +
            "WHERE idmedia = :id",
            nativeQuery = true)
    Integer updateMedia(Integer id, String titre, Integer idType, Integer idGenre, Integer anneePublicationSortie, String imgMedia);

    @Query(value = "SELECT * FROM typemedia WHERE idtype = :id",
            nativeQuery = true)
    TypeMediaEntity getTypeMedia(Integer id);
    @Query(value = "SELECT * FROM genremedia WHERE idgenre = :id",
            nativeQuery = true)
    GenreMediaEntity getGenreMedia(Integer id);
}
