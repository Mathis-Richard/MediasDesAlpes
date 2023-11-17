package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.controller;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.MediaApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.MediaJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.MediaMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl.MediaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin(origins = "null", originPatterns = {"http://postgres:[*]","http://localhost:[*]"}, allowedHeaders = "*", allowCredentials = "true")
public class MediaController implements MediaApi {

    private MediaServiceImpl mediaServiceImpl;

    @Autowired
    public MediaController(MediaServiceImpl mediaServiceImpl) {
        this.mediaServiceImpl = mediaServiceImpl;
    }

    @Override
    public ResponseEntity<List<MediaJson>> getMedia() {
        return ResponseEntity.ok(MediaMapper.INSTANCE.mapToJson(mediaServiceImpl.getMedia()));
    }

    @Override
    public ResponseEntity<MediaJson> getMediaById(Integer idMedia) {
        return ResponseEntity.ok(MediaMapper.INSTANCE.mapToJson(mediaServiceImpl.getMediaById(idMedia)));
    }

}
