package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.controller;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.UtilisateurApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.VersionApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl.UtilisateurServiceImpl;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl.VersionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "null", originPatterns = {"http://localhost:[*]"}, allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/api")
public class UtilisateurController implements UtilisateurApi {

    private UtilisateurServiceImpl utilisateurServiceImpl;

    @Autowired
    public UtilisateurController(UtilisateurServiceImpl utilisateurServiceImpl) {
        this.utilisateurServiceImpl = utilisateurServiceImpl;
    }

    @Override
    public ResponseEntity<List<String>> getUtilisateurListe() {
        return ResponseEntity.ok(utilisateurServiceImpl.getList());
    }
}
