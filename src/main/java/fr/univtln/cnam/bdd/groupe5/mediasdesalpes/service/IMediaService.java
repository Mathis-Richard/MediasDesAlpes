package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Media;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMediaService {

    List<Media> getMedia();
    Media getMediaById(Integer id);
}
