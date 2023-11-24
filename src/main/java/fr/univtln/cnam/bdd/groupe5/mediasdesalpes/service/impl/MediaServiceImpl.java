package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.AuteurMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.MediaMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Media;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IAuteurRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IMediaRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.IMediaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MediaServiceImpl implements IMediaService {

    private final IMediaRepository mediaRepository;
    private final IAuteurRepository auteurRepository;

    public MediaServiceImpl(IMediaRepository mediaRepository, IAuteurRepository auteurRepository) {
        this.mediaRepository = mediaRepository;
        this.auteurRepository = auteurRepository;
    }

    @Override
    public List<Media> getMedia() {
        return MediaMapper.INSTANCE.mapToModel(mediaRepository.getMedia());
    }

    @Override
    public List<Media> getMediasClassement() {
        return MediaMapper.INSTANCE.mapToModel(mediaRepository.getMediaClassement());
    }

    @Override
    public Media getMediaById(Integer id) {
        return MediaMapper.INSTANCE.mapToModel(mediaRepository.getMediaById(id));
    }

    @Override
    public Integer deleteMediaById(Integer id) {
        return mediaRepository.deleteMediaById(id);
    }

    @Override
    public Integer updateMediaById(Integer id, Media data) {
        return mediaRepository.updateMedia(
                id,
                data.getTitre(),
                data.getType().getId(),
                data.getGenre().getId(),
                data.getAnneepublicationsortie(),
                data.getImgmedia()
        );
    }

    @Override
    public Integer computeNewMedia(Media media) {
        return mediaRepository.insertMedia(
                media.getTitre(),
                media.getType().getId(),
                media.getGenre().getId(),
                media.getAnneepublicationsortie(),
                media.getImgmedia()
        );
    }

    @Override
    public List<Media> computeMediaWithLimits(Integer limit, Integer page) {
        Integer offset = (page - 1) * limit;
        return MediaMapper.INSTANCE.mapToModel(mediaRepository.getMediaLimitAndPage(limit, offset));
    }
}
