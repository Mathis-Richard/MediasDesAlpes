package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.UtilisateurEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.UtilisateurJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.Date;
import java.time.LocalDate;

@Mapper(uses = AuteurMapper.class)
public interface UtilisateurMapper {

    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    Utilisateur map(UtilisateurEntity utilisateurEntity);

    @Mapping(source = "emailUtilisateur", target = "emailutilisateur")
    @Mapping(source = "nomUtilisateur", target = "nomutilisateur")
    @Mapping(source = "prenomUtilisateur", target = "prenomutilisateur")
    @Mapping(source = "adresseUtilisateur", target = "adresseutilisateur")
    @Mapping(source = "numTelephoneUtilisateur", target = "numtelephoneutilisateur")
    @Mapping(source = "dateNaissanceUtilisateur", target = "datenaissanceutilisateur")
    Utilisateur map(UtilisateurJson utilisateurJson);

    default Date map(String value){
        return Date.valueOf(value);
    }
}

