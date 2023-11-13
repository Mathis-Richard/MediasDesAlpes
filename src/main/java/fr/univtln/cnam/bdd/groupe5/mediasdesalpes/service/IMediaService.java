package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Media;
import org.springframework.stereotype.Service;

@Service
public interface IMediaService {
    Media getMediaById(Integer id);
}
