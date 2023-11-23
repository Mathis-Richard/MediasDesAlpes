package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Utilisateur;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IUtilisateurRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.IConnexionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@Transactional
public class ConnexionServiceImpl implements IConnexionService {

    private final IUtilisateurRepository utilisateurRepository;

    public ConnexionServiceImpl(IUtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Void registerUser(Utilisateur u) {
        utilisateurRepository.createUser(u.getEmailutilisateur(), u.getNomutilisateur(), u.getPrenomutilisateur(), u.getAdresseutilisateur(), u.getNumtelephoneutilisateur(), u.getMdputilisateur(), u.getDatenaissanceutilisateur());
        return null;
    }
}
