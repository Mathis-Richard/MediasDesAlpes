package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.PostEmpruntStartRequestJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.EmpruntMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Emprunt;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IEmpruntRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.IEmpruntService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpruntServiceImpl implements IEmpruntService {

    private final IEmpruntRepository empruntRepository;

    public EmpruntServiceImpl(IEmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }

    @Override
    public List<Emprunt> getEmprunts() {
        return EmpruntMapper.INSTANCE.mapToModel(empruntRepository.getEmprunts());
    }

    @Override
    public List<Emprunt> getEmpruntsAnnule() {
        return EmpruntMapper.INSTANCE.mapToModel(empruntRepository.getEmpruntAnnule());
    }

    @Override
    public List<Emprunt> getEmpruntsAttente() {
        return EmpruntMapper.INSTANCE.mapToModel(empruntRepository.getEmpruntAttente());
    }

    @Override
    public List<Emprunt> getEmpruntsEncours() {
        return EmpruntMapper.INSTANCE.mapToModel(empruntRepository.getEmpruntsEncours());
    }

    @Override
    public List<Emprunt> getEmpruntsTermine() {
        return EmpruntMapper.INSTANCE.mapToModel(empruntRepository.getEmpruntTermine());
    }

    @Override
    public Void postEmpruntCancel(String idExemplaire, PostEmpruntStartRequestJson postEmpruntStartRequestJson) {
        empruntRepository.cancelEmprunt(Integer.valueOf(idExemplaire),Integer.valueOf(postEmpruntStartRequestJson.getIdUtilisateur()));
        return null;
    }

    @Override
    public Void postEmpruntReturn(String idExemplaire, PostEmpruntStartRequestJson postEmpruntStartRequestJson) {
        empruntRepository.returnEmprunt(Integer.valueOf(idExemplaire));
        return null;
    }

    @Override
    public Void postEmpruntStart(String idExemplaire, PostEmpruntStartRequestJson postEmpruntStartRequestJson) {
        empruntRepository.startEmprunt(Integer.valueOf(idExemplaire),Integer.valueOf(postEmpruntStartRequestJson.getIdUtilisateur()));
        return null;
    }
}
