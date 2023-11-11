package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.TypeMediaEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.TypeMedia;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.TypeMediaJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeMediaMapper {

    TypeMediaMapper INSTANCE = Mappers.getMapper(TypeMediaMapper.class);

    @Mapping(target = "idType", source = "id")
    @Mapping(target = "designationType", source = "designationtype")
    @Mapping(target = "designationAuteur", source = "designationauteur")
    TypeMediaJson mapToJson(TypeMedia type);

    TypeMedia mapToModel(TypeMediaEntity mediaEntity);
}

