package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.AuteurEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.EmpruntEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.NationaliteAuteurEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.AuteurJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.EmpruntJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.NationaliteAuteurJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Auteur;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Emprunt;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.NationaliteAuteur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Mapper(uses = UtilsMapper.class)
public interface EmpruntMapper {

    EmpruntMapper INSTANCE = Mappers.getMapper(EmpruntMapper.class);

    @Mapping(source = "id", target = "idExemplaire")
    @Mapping(expression="java(emprunt.getIdutilisateur().getId())", target = "idUtilisateur")
    @Mapping(source = "datereservation", target = "dateReservation")
    @Mapping(source = "dateemprunt", target = "dateEmprunt")
    @Mapping(source = "dateretourprevue", target = "dateRetourPrevue")
    @Mapping(source = "dateretourreelle", target = "dateRetourReelle")
    @Mapping(target = "statutEmprunt",expression = "java(emprunt.getStatut().toString())")
    EmpruntJson mapToJson(Emprunt emprunt);

    @Mapping(target = "statut", source = "statutemprunt")
    Emprunt mapToModel(EmpruntEntity empruntEntity);

    List<Emprunt> mapToModel(List<EmpruntEntity> empruntEntities);

    List<EmpruntJson> mapToJson(List<Emprunt> emprunts);


}

