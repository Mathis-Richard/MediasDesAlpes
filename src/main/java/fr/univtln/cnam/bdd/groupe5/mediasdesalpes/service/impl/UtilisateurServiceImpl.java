package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.UtilisateurMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Utilisateur;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IUtilisateurRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.IUtilisateurService;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements IUtilisateurService {

    private final IUtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(IUtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur getUserByEmail(String email) {
        return UtilisateurMapper.INSTANCE.map(utilisateurRepository.findByEmail(email));
    }
}
