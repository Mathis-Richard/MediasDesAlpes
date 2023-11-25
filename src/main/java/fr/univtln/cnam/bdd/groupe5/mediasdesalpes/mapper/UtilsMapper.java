package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.jackson.nullable.JsonNullable;

import java.sql.Date;
import java.text.SimpleDateFormat;

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
    default String map(Date value){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(value);
    }

}

