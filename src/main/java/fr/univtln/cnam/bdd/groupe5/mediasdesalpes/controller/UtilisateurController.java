package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.controller;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.UtilisateursApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.PostMediasWithLimitsRequestJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.UtilisateurJson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin(origins = "null", originPatterns = {"http://postgres:[*]", "http://localhost:[*]"}, allowedHeaders = "*", allowCredentials = "true")
public class UtilisateurController implements UtilisateursApi {

    @Override
    public ResponseEntity<Void> deleteUtilisateurByEmail(String emailUtilisateur) {
        return null;
    }

    @Override
    public ResponseEntity<UtilisateurJson> getUtilisateurByEmail(String emailUtilisateur) {
        return null;
    }

    @Override
    public ResponseEntity<List<UtilisateurJson>> getUtilisateurs() {
        return null;
    }

    @Override
    public ResponseEntity<Void> patchUtilisateurByEmail(String emailUtilisateur, UtilisateurJson utilisateurJson) {
        return null;
    }

    @Override
    public ResponseEntity<Void> postNewUtilisateur() {
        return null;
    }

    @Override
    public ResponseEntity<List<UtilisateurJson>> postUtilisateursWithLimit(PostMediasWithLimitsRequestJson postMediasWithLimitsRequestJson) {
        return null;
    }

}

