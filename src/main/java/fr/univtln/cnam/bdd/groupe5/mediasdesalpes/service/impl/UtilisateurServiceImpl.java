package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IUtilisateurRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IVersionRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.IUtilisateurService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurServiceImpl implements IUtilisateurService {

    private final IUtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(IUtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }
    @Override
    public List<String> getList() {
        List<String> out = new ArrayList<>();
        List<String> noms = this.utilisateurRepository.getNom();
        List<String> prenoms = this.utilisateurRepository.getPrenom();
        for(int i = 0; i<noms.size(); i++) {
            out.add(noms.get(i) + prenoms.get(i));
        }
        return out;
    }
}
