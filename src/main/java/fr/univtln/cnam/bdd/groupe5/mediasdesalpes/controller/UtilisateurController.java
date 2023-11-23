package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.controller;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.UtilisateursApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.ConnexionFormJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.JwtResponseJson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "null", originPatterns = {"http://postgres:[*]", "http://localhost:[*]"}, allowedHeaders = "*", allowCredentials = "true")
public class UtilisateurController implements UtilisateursApi {


    @Override
    public ResponseEntity<JwtResponseJson> postUserLogin(ConnexionFormJson connexionFormJson) {
        return null;
    }
}

