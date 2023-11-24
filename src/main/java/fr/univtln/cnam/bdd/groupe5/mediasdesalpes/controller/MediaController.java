package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.controller;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.MediasApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.MediaJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.PostMediasWithLimitsRequestJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.MediaMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl.MediaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin(origins = "null", originPatterns = {"http://postgres:[*]","http://localhost:[*]"}, allowedHeaders = "*", allowCredentials = "true")
public class MediaController implements MediasApi {

    private MediaServiceImpl mediaServiceImpl;

    @Autowired
    public MediaController(MediaServiceImpl mediaServiceImpl) {
        this.mediaServiceImpl = mediaServiceImpl;
    }

    @Override
    public ResponseEntity<Void> deleteMediaById(Integer idMedia) {
        mediaServiceImpl.deleteMediaById(idMedia);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<MediaJson>> getMedia() {
        return ResponseEntity.ok(MediaMapper.INSTANCE.mapToJson(mediaServiceImpl.getMedia()));
    }

    @Override
    public ResponseEntity<MediaJson> getMediaById(Integer idMedia) {
        return ResponseEntity.ok(MediaMapper.INSTANCE.mapToJson(mediaServiceImpl.getMediaById(idMedia)));
    }

    @Override
    public ResponseEntity<Void> patchMediaById(Integer idMedia, MediaJson mediaJson) {
        mediaServiceImpl.updateMediaById(idMedia,MediaMapper.INSTANCE.mapToModel(mediaJson));
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<MediaJson>> postMediasWithLimits(PostMediasWithLimitsRequestJson postMediasWithLimitsRequestJson) {
        return ResponseEntity.ok(MediaMapper.INSTANCE.mapToJson(mediaServiceImpl.computeMediaWithLimits(postMediasWithLimitsRequestJson.getLimit(),postMediasWithLimitsRequestJson.getPage())));
    }

    @Override
    public ResponseEntity<Void> postNewMedia(MediaJson mediaJson) {
        mediaServiceImpl.computeNewMedia(MediaMapper.INSTANCE.mapToModel(mediaJson));
        return ResponseEntity.ok(null);
    }
}
