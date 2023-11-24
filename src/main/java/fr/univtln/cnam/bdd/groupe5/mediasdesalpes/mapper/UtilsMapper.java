package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.AuteurEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.NationaliteAuteurEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.AuteurJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.NationaliteAuteurJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Auteur;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.NationaliteAuteur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.openapitools.jackson.nullable.JsonNullable;

import java.sql.Date;
import java.util.List;

@Mapper
public interface UtilsMapper {

    UtilsMapper INSTANCE = Mappers.getMapper(UtilsMapper.class);
    default JsonNullable<Integer> map(Integer value) {
        return JsonNullable.of(value);
    }
    default Integer map(JsonNullable<Integer> value){
        return value.orElse(0);
    }
    default Date map(String value){
        return Date.valueOf(value);
    }

}

