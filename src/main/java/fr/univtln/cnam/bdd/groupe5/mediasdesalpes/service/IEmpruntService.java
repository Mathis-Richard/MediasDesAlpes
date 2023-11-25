package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.EmpruntJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.PostEmpruntStartRequestJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Emprunt;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Utilisateur;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEmpruntService {


    List<Emprunt> getEmprunts();

    List<Emprunt> getEmpruntsAnnule();

    List<Emprunt> getEmpruntsAttente();

    List<Emprunt> getEmpruntsEncours();

    List<Emprunt> getEmpruntsTermine();

    Void postEmpruntCancel(String idExemplaire, PostEmpruntStartRequestJson postEmpruntStartRequestJson);

    Void postEmpruntReturn(String idExemplaire, PostEmpruntStartRequestJson postEmpruntStartRequestJson);

    Void postEmpruntStart(String idExemplaire, PostEmpruntStartRequestJson postEmpruntStartRequestJson);
}
