package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Utilisateur;
import org.springframework.stereotype.Service;

@Service
public interface IUtilisateurService {

    Utilisateur getUserByEmail(String email);

}
