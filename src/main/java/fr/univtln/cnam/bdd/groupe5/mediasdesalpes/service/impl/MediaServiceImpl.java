package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.AuteurMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.MediaMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Media;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IAuteurRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IMediaRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.IMediaService;
import org.springframework.stereotype.Service;

@Service
public class MediaServiceImpl implements IMediaService {

    private final IMediaRepository mediaRepository;
    private final IAuteurRepository auteurRepository;

    public MediaServiceImpl(IMediaRepository mediaRepository, IAuteurRepository auteurRepository) {
        this.mediaRepository = mediaRepository;
        this.auteurRepository = auteurRepository;
    }

    @Override
    public Media getMediaById(Integer id) {
        Media out = MediaMapper.INSTANCE.mapToModel(mediaRepository.getMediaById(id));
        out.setAuteurs(AuteurMapper.INSTANCE.mapToModel(auteurRepository.getAuteursByMediaId(id)));
        return out;
    }
}
