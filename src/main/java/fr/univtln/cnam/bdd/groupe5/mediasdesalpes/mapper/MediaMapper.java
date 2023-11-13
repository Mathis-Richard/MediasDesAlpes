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

@Mapper(uses = AuteurMapper.class)
public interface MediaMapper {

    MediaMapper INSTANCE = Mappers.getMapper(MediaMapper.class);


    @Mapping(target = "anneePublicationSortie", source = "anneepublicationsortie")
    @Mapping(target = "note", source = "notemedia")
    @Mapping(target = "img", source = "imgmedia")
    MediaJson mapToJson(Media media);

    @Mapping(source="idtype",target="type")
    @Mapping(source="idgenre",target="genre")
    Media mapToModel(MediaEntity mediaEntity);

    @Mapping(source="designationtype",target="designationType")
    @Mapping(source="designationauteur",target="designationAuteur")
    TypeMediaJson mapToJson(TypeMedia type);

    GenreMediaJson mapToJson(GenreMedia type);

    TypeMedia mapToModel(TypeMediaEntity typeMediaEntity);

    GenreMedia mapToModel(GenreMediaEntity genreMediaEntity);
}
