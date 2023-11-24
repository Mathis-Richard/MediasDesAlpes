package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.*;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.*;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.List;

@Mapper(uses = UtilsMapper.class)
public interface AuteurMapper {

    AuteurMapper INSTANCE = Mappers.getMapper(AuteurMapper.class);

    Auteur mapToModel(AuteurEntity auteurEntity);

    List<Auteur> mapToModel(List<AuteurEntity> auteurEntity);

    @Mapping(source = "nomauteur",target = "nom")
    @Mapping(source = "prenomauteur",target = "prenom")
    @Mapping(source = "nationaliteauteur", target = "nationalite")
    AuteurJson mapToJson(Auteur auteur);

    List<AuteurJson> mapToJson(List<Auteur> auteur);

    NationaliteAuteurJson mapToJson(NationaliteAuteur nationaliteAuteur);

    NationaliteAuteur mapToModel(NationaliteAuteurEntity nationaliteAuteurEntity);


}

