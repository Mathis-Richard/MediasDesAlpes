package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Utilisateur;
import org.springframework.stereotype.Service;

@Service
public interface IConnexionService {

    Void registerUser(Utilisateur user);

}
