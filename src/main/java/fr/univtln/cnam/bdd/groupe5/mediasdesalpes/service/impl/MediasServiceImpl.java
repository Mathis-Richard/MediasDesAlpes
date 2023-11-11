package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.MediasMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Media;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IMediasRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.IMediasService;
import org.springframework.stereotype.Service;

@Service
public class MediasServiceImpl implements IMediasService {

    private final IMediasRepository mediasRepository;

    public MediasServiceImpl(IMediasRepository mediasRepository) {
        this.mediasRepository = mediasRepository;
    }

    @Override
    public Media getMediaById(Integer id) {
        return MediasMapper.INSTANCE.mapToModel(mediasRepository.getMediaById(id));
    }
}
