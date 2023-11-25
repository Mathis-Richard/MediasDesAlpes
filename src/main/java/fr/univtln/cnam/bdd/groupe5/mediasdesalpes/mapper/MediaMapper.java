package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.GenreMediaEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.MediaEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.TypeMediaEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.GenreMediaJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.MediaJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.TypeMediaJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.GenreMedia;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Media;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.TypeMedia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.List;

@Mapper(uses = {AuteurMapper.class, UtilsMapper.class})
public interface MediaMapper {

    MediaMapper INSTANCE = Mappers.getMapper(MediaMapper.class);

    @Mapping(target = "anneePublicationSortie", source = "anneepublicationsortie")
    @Mapping(target = "note", source = "notemoyenne")
    @Mapping(target = "img", source = "imgmedia")
    @Mapping(target = "idType", source = "type.id")
    @Mapping(target = "idGenre", source = "genre.id")
    MediaJson mapToJson(Media media);

    @Mapping(source = "idtype", target = "type")
    @Mapping(source = "idgenre", target = "genre")
    Media mapToModel(MediaEntity mediaEntity);

    @Mapping(source = "anneePublicationSortie", target = "anneepublicationsortie")
    @Mapping(source = "note", target = "notemoyenne")
    @Mapping(source = "img", target = "imgmedia")
    Media mapToModel(MediaJson mediaEntity);


    List<MediaJson> mapToJson(List<Media> media);

    List<Media> mapToModel(List<MediaEntity> mediaEntity);

    @Mapping(source = "designationtype", target = "designationType")
    @Mapping(source = "designationauteur", target = "designationAuteur")
    TypeMediaJson mapTypeToJson(TypeMedia type);

    GenreMediaJson mapGenreToJson(GenreMedia type);

    TypeMedia mapTypeToModel(TypeMediaEntity typeMediaEntity);

    GenreMedia mapGenreToModel(GenreMediaEntity genreMediaEntity);

    List<TypeMediaJson> mapTypeToJson(List<TypeMedia> type);

    List<GenreMediaJson> mapGenreToJson(List<GenreMedia> type);

    List<TypeMedia> mapTypeToModel(List<TypeMediaEntity> typeMediaEntity);

    List<GenreMedia> mapGenreToModel(List<GenreMediaEntity> genreMediaEntity);


}

