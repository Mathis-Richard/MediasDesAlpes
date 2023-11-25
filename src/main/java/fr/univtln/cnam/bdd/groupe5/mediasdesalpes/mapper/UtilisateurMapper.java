package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.UtilisateurEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.UtilisateurJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.openapitools.jackson.nullable.JsonNullable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Mapper(uses = {AuteurMapper.class, UtilsMapper.class})
public interface UtilisateurMapper {

    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);


    @Mapping(target = "emailUtilisateur", source = "emailutilisateur")
    @Mapping(target = "nomUtilisateur", source = "nomutilisateur")
    @Mapping(target = "prenomUtilisateur", source = "prenomutilisateur")
    @Mapping(target = "adresseUtilisateur", source = "adresseutilisateur")
    @Mapping(target = "numTelephoneUtilisateur", source = "numtelephoneutilisateur")
    @Mapping(target = "dateNaissanceUtilisateur", source = "datenaissanceutilisateur")
    @Mapping(target = "typeProfilUtilisateur",expression = "java(utilisateur.getTypeprofil().toString())")
    UtilisateurJson mapToJson(Utilisateur utilisateur);
    Utilisateur mapToModel(UtilisateurEntity utilisateurEntity);

    @Mapping(source = "emailUtilisateur", target = "emailutilisateur")
    @Mapping(source = "nomUtilisateur", target = "nomutilisateur")
    @Mapping(source = "prenomUtilisateur", target = "prenomutilisateur")
    @Mapping(source = "adresseUtilisateur", target = "adresseutilisateur")
    @Mapping(source = "numTelephoneUtilisateur", target = "numtelephoneutilisateur")
    @Mapping(source = "dateNaissanceUtilisateur", target = "datenaissanceutilisateur")
    Utilisateur map(UtilisateurJson utilisateurJson);

    List<UtilisateurJson> mapToJson(List<Utilisateur> utilisateur);
    List<Utilisateur> mapToModel(List<UtilisateurEntity> utilisateurEntity);

}

