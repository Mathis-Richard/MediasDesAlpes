package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.controller;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.AuteursApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.GenresApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.MediasApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.TypesApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.*;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.AuteurMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.MediaMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.GenreMedia;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Media;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.TypeMedia;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl.MediaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin(origins = "null", originPatterns = {"http://web:[*]", "http://postgres:[*]", "http://localhost:[*]"}, allowedHeaders = "*", allowCredentials = "true")
public class MediaController implements MediasApi, GenresApi, TypesApi, AuteursApi {

    private MediaServiceImpl mediaServiceImpl;

    @Autowired
    public MediaController(MediaServiceImpl mediaServiceImpl) {
        this.mediaServiceImpl = mediaServiceImpl;
    }

    @Override
    public ResponseEntity<Void> deleteMediaById(Integer idMedia) {
        Integer i = mediaServiceImpl.deleteMediaById(idMedia);
        return i > 0 ? ResponseEntity.ok(null) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<MediaJson>> getMedia() {
        return ResponseEntity.ok(MediaMapper.INSTANCE.mapToJson(mediaServiceImpl.getMedia()));
    }

    @Override
    public ResponseEntity<MediaJson> getMediaById(Integer idMedia) {
        Media m = mediaServiceImpl.getMediaById(idMedia);
        return m != null ? ResponseEntity.ok(MediaMapper.INSTANCE.mapToJson(m)) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<MediaJson>> getMediasClassement() {
        return ResponseEntity.ok(MediaMapper.INSTANCE.mapToJson(mediaServiceImpl.getMediasClassement()));
    }

    @Override
    public ResponseEntity<Void> patchMediaById(Integer idMedia, MediaJson mediaJson) {
        Media m = MediaMapper.INSTANCE.mapToModel(mediaJson);
        m.setGenre(new GenreMedia(mediaJson.getIdType()));
        m.setType(new TypeMedia(mediaJson.getIdGenre()));
        Integer i = mediaServiceImpl.updateMediaById(idMedia,m);
        return i > 0 ? ResponseEntity.ok(null) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<MediaJson>> postMediasWithLimits(PostMediasWithLimitsRequestJson postMediasWithLimitsRequestJson) {
        List<MediaJson> l = MediaMapper.INSTANCE.mapToJson(mediaServiceImpl.computeMediaWithLimits(postMediasWithLimitsRequestJson.getLimit(),postMediasWithLimitsRequestJson.getPage()));
        return !l.isEmpty() ? ResponseEntity.ok(l) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Void> postNewMedia(MediaJson mediaJson) {
        Media m = MediaMapper.INSTANCE.mapToModel(mediaJson);
        m.setGenre(new GenreMedia(mediaJson.getIdType()));
        m.setType(new TypeMedia(mediaJson.getIdGenre()));
        Integer i = mediaServiceImpl.computeNewMedia(m);
        return i > 0 ? ResponseEntity.ok(null) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<GenreMediaJson>> getGenres() {
        return ResponseEntity.ok(MediaMapper.INSTANCE.mapGenreToJson(mediaServiceImpl.getGenres()));
    }

    @Override
    public ResponseEntity<List<TypeMediaJson>> getTypes() {
        return ResponseEntity.ok(MediaMapper.INSTANCE.mapTypeToJson(mediaServiceImpl.getTypes()));
    }

    @Override
    public ResponseEntity<Void> deleteAuteurById(String idAuteur) {
        mediaServiceImpl.deleteAuteurById(Integer.valueOf(idAuteur));
        return null;
    }

    @Override
    public ResponseEntity<Void> getAuteurById(String idAuteur) {
        mediaServiceImpl.getAuteurById(Integer.valueOf(idAuteur));
        return null;
    }

    @Override
    public ResponseEntity<List<AuteurJson>> getAuteurs() {
        return ResponseEntity.ok(AuteurMapper.INSTANCE.mapToJson(mediaServiceImpl.getAuteurs()));
    }

    @Override
    public ResponseEntity<List<AuteurJson>> getAuteursMediasIdMedia(String idMedia) {
        return ResponseEntity.ok(AuteurMapper.INSTANCE.mapToJson(mediaServiceImpl.getAuteursByMediaId(Integer.valueOf(idMedia))));
    }

    @Override
    public ResponseEntity<Void> patchAuteurById(String idAuteur, AuteurJson auteurJson) {
        mediaServiceImpl.updateAuteurById(Integer.valueOf(idAuteur),AuteurMapper.INSTANCE.mapToModel(auteurJson));
        return null;
    }

    @Override
    public ResponseEntity<Void> postNewAuteur(AuteurJson auteurJson) {
        mediaServiceImpl.updateAuteurById(auteurJson.getId(), AuteurMapper.INSTANCE.mapToModel(auteurJson));
        return null;
    }
}
