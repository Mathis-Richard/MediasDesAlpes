package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.MediaEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.MediaJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Media;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MediasMapper {

    MediasMapper INSTANCE = Mappers.getMapper(MediasMapper.class);

    @Mapping(target = "idMedia", source = "id")
    @Mapping(target = "anneePublicationSortie", source = "anneepublicationsortie")
    @Mapping(target = "noteMedia", source = "notemedia")
    @Mapping(target = "imgMedia", source = "imgmedia")
    MediaJson mapToJson(Media media);

    Media mapToModel(MediaEntity mediaEntity);
}

