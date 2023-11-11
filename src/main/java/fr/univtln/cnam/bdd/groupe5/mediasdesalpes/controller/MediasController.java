package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.controller;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.MediasApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.MediaJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.MediasMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl.MediasServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "null", originPatterns = {"http://localhost:[*]"}, allowedHeaders = "*", allowCredentials = "true")
public class MediasController implements MediasApi {

    private MediasServiceImpl mediasServiceImpl;

    @Autowired
    public MediasController(MediasServiceImpl mediasServiceImpl) {
        this.mediasServiceImpl = mediasServiceImpl;
    }

    @Override
    public ResponseEntity<MediaJson> getMediaById(Integer idMedia) {
        return ResponseEntity.ok(MediasMapper.INSTANCE.mapToJson(mediasServiceImpl.getMediaById(idMedia)));
    }
}
