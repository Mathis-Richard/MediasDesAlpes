package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.controller;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.ConnexionApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.InscriptionApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.ConnexionFormJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.UtilisateurJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.UtilisateurMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Utilisateur;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl.ConnexionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "null", originPatterns = {"http://postgres:[*]", "http://localhost:[*]"}, allowedHeaders = "*", allowCredentials = "true")
public class ConnexionController implements ConnexionApi, InscriptionApi {

    private final PasswordEncoder passwordEncoder;

    private final ConnexionServiceImpl connexionService;

    @Autowired
    public ConnexionController(PasswordEncoder passwordEncoder, ConnexionServiceImpl connexionService) {
        this.passwordEncoder = passwordEncoder;
        this.connexionService = connexionService;
    }

    @Override
    public ResponseEntity<Void> postConnexion(ConnexionFormJson connexionFormJson) {
        return null;
    }

    @Override
    public ResponseEntity<Void> postInscription(UtilisateurJson utilisateurJson) {
        Utilisateur nouvelUtilisateur = UtilisateurMapper.INSTANCE.map(utilisateurJson);
        nouvelUtilisateur.setTypeprofil(Utilisateur.TypeProfil.UTILISATEUR);
        String hashedPwd = passwordEncoder.encode(utilisateurJson.getMdpUtilisateur());
        nouvelUtilisateur.setMdputilisateur(hashedPwd);
        connexionService.registerUser(nouvelUtilisateur);
        return null;
    }
}
