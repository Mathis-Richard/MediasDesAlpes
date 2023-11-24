package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.PostMediasWithLimitsRequestJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.UtilisateurJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.MediaMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.UtilisateurMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Utilisateur;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IUtilisateurRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.IUtilisateurService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UtilisateurServiceImpl implements IUtilisateurService {

    private final IUtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(IUtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur getUserByEmail(String email) {
        return UtilisateurMapper.INSTANCE.mapToModel(utilisateurRepository.getUserByEmail(email));
    }

    @Override
    public Integer postNewUtilisateur(Utilisateur u) {
        return utilisateurRepository.createUser(u.getEmailutilisateur(), u.getNomutilisateur(), u.getPrenomutilisateur(), u.getAdresseutilisateur(), u.getNumtelephoneutilisateur(), u.getMdputilisateur(), u.getDatenaissanceutilisateur(), u.getTypeprofil().equals(Utilisateur.TypeProfil.ADMINISTRATEUR) ? "ADMINISTRATEUR" : "UTILISATEUR");
    }

    @Override
    public Integer deleteUtilisateurByEmail(String emailUtilisateur) {
        return utilisateurRepository.deleteUser(emailUtilisateur);
    }

    @Override
    public List<Utilisateur> getUtilisateurs() {
        return UtilisateurMapper.INSTANCE.mapToModel(utilisateurRepository.getUsers());
    }

    @Override
    public Integer patchUtilisateurByEmail(String emailUtilisateur, Utilisateur utilisateur) {
        return utilisateurRepository.updateUser(emailUtilisateur,utilisateur.getEmailutilisateur(),utilisateur.getNomutilisateur(),utilisateur.getPrenomutilisateur(),utilisateur.getAdresseutilisateur(),utilisateur.getNumtelephoneutilisateur(),utilisateur.getMdputilisateur(),utilisateur.getDatenaissanceutilisateur(),utilisateur.getTypeprofil().toString());
    }

    @Override
    public List<Utilisateur> postUtilisateursWithLimit(Integer limit, Integer page) {
        Integer offset = (page - 1) * limit;
        return UtilisateurMapper.INSTANCE.mapToModel(utilisateurRepository.getUsersLimitAndPage(limit, offset));
    }
}
