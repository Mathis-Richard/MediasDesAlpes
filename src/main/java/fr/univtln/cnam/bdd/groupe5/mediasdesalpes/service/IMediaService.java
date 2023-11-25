package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Auteur;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.GenreMedia;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Media;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.TypeMedia;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMediaService {

    List<Media> getMedia();
    List<Media> getMediasClassement();
    Media getMediaById(Integer id);
    Integer deleteMediaById(Integer id);
    Integer updateMediaById(Integer id, Media data);
    Integer computeNewMedia(Media media);
    List<Media> computeMediaWithLimits(Integer limit, Integer page);

    List<TypeMedia> getTypes();
    List<GenreMedia> getGenres();

    List<Auteur> getAuteurs();
    List<Auteur> getAuteursByMediaId(Integer id);
    Auteur getAuteurById(Integer id);
    Integer computeNewAuteur(Auteur a);
    Integer updateAuteurById(Integer id, Auteur data);
    Integer deleteAuteurById(Integer id);
}
