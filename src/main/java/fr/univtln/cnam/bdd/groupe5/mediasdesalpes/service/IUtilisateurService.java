package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.PostMediasWithLimitsRequestJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.UtilisateurJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUtilisateurService {

    Utilisateur getUserByEmail(String email);

    Integer postNewUtilisateur(Utilisateur u);

    Integer deleteUtilisateurByEmail(String emailUtilisateur);

    List<Utilisateur> getUtilisateurs();

    Integer patchUtilisateurByEmail(String emailUtilisateur, Utilisateur utilisateur);

    List<Utilisateur> postUtilisateursWithLimit(Integer limit, Integer page);
}
