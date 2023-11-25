package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.AuteurMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.MediaMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Auteur;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.GenreMedia;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Media;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.TypeMedia;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IAuteurRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IGenreRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IMediaRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.ITypeRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MediaServiceImpl implements IMediaService {

    private final IMediaRepository mediaRepository;
    private final IAuteurRepository auteurRepository;

    private final IGenreRepository genreRepository;
    private final ITypeRepository typeRepository;

    public MediaServiceImpl(IMediaRepository mediaRepository, IAuteurRepository auteurRepository, IGenreRepository genreRepository, ITypeRepository typeRepository) {
        this.mediaRepository = mediaRepository;
        this.auteurRepository = auteurRepository;
        this.genreRepository = genreRepository;
        this.typeRepository = typeRepository;
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

    @Override
    public List<TypeMedia> getTypes() {
        return MediaMapper.INSTANCE.mapTypeToModel(typeRepository.getTypesMedia());
    }

    @Override
    public List<GenreMedia> getGenres() {
        return MediaMapper.INSTANCE.mapGenreToModel(genreRepository.getGenresMedia());
    }

    @Override
    public List<Auteur> getAuteurs() {
        return AuteurMapper.INSTANCE.mapToModel(auteurRepository.getAuteurs());
    }

    @Override
    public List<Auteur> getAuteursByMediaId(Integer id) {
        return AuteurMapper.INSTANCE.mapToModel(auteurRepository.getAuteursByMediaId(id));
    }

    @Override
    public Auteur getAuteurById(Integer id) {
        return AuteurMapper.INSTANCE.mapToModel(auteurRepository.getAuteurById(id));
    }

    @Override
    public Integer computeNewAuteur(Auteur a) {
        return auteurRepository.insertAuteur(a.getNomauteur(),a.getPrenomauteur(),a.getNationaliteauteur().getId());
    }

    @Override
    public Integer updateAuteurById(Integer id, Auteur data) {
        return auteurRepository.updateAuteur(id,data.getNomauteur(),data.getPrenomauteur(),data.getNationaliteauteur().getId());
    }

    @Override
    public Integer deleteAuteurById(Integer id) {
        return auteurRepository.deleteAuteur(id);
    }

}
