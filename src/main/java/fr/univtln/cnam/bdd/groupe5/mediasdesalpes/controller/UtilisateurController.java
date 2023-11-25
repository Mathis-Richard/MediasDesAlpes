package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.controller;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.UtilisateursApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.PostMediasWithLimitsRequestJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.UtilisateurJson;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.UtilisateurMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model.Utilisateur;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl.UtilisateurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin(origins = "null", originPatterns = {"http://web:[*]", "http://postgres:[*]", "http://localhost:[*]"}, allowedHeaders = "*", allowCredentials = "true")
public class UtilisateurController implements UtilisateursApi {

    private final UtilisateurServiceImpl utilisateurService;

    private final PasswordEncoder passwordEncoder;
    public UtilisateurController(UtilisateurServiceImpl utilisateurService, PasswordEncoder passwordEncoder) {
        this.utilisateurService = utilisateurService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<Void> deleteUtilisateurByEmail(String emailUtilisateur) {
        Integer i = utilisateurService.deleteUtilisateurByEmail(emailUtilisateur);
        return i > 0 ? ResponseEntity.ok(null) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Void> deleteUtilisateurById(String idUtilisateur) {
        Integer i = utilisateurService.deleteUtilisateurById(Integer.valueOf(idUtilisateur));
        return i > 0 ? ResponseEntity.ok(null) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<UtilisateurJson> getUtilisateurByEmail(String emailUtilisateur) {
        return ResponseEntity.ok(UtilisateurMapper.INSTANCE.mapToJson(utilisateurService.getUserByEmail(emailUtilisateur)));
    }

    @Override
    public ResponseEntity<UtilisateurJson> getUtilisateurById(String idUtilisateur) {
        return ResponseEntity.ok(UtilisateurMapper.INSTANCE.mapToJson(utilisateurService.getUserById(Integer.valueOf(idUtilisateur))));
    }

    @Override
    public ResponseEntity<List<UtilisateurJson>> getUtilisateurs() {
        return ResponseEntity.ok(UtilisateurMapper.INSTANCE.mapToJson(utilisateurService.getUtilisateurs()));
    }

    @Override
    public ResponseEntity<Void> patchUtilisateurByEmail(String emailUtilisateur, UtilisateurJson utilisateurJson) {
        Utilisateur.TypeProfil tp = utilisateurJson.getTypeProfilUtilisateur().equals("ADMINISTRATEUR") ? Utilisateur.TypeProfil.ADMINISTRATEUR : Utilisateur.TypeProfil.UTILISATEUR;
        Utilisateur nouveau = UtilisateurMapper.INSTANCE.map(utilisateurJson);
        nouveau.setTypeprofil(tp);
        nouveau.setMdputilisateur(utilisateurService.getUserByEmail(emailUtilisateur).getMdputilisateur());
        Integer i = utilisateurService.patchUtilisateurByEmail(emailUtilisateur,nouveau);
        return i > 0 ? ResponseEntity.ok(null) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Void> patchUtilisateurById(String idUtilisateur, UtilisateurJson utilisateurJson) {
        Integer idUtilisateurInt = Integer.valueOf(idUtilisateur);
        Utilisateur.TypeProfil tp = utilisateurJson.getTypeProfilUtilisateur().equals("ADMINISTRATEUR") ? Utilisateur.TypeProfil.ADMINISTRATEUR : Utilisateur.TypeProfil.UTILISATEUR;
        Utilisateur nouveau = UtilisateurMapper.INSTANCE.map(utilisateurJson);
        nouveau.setTypeprofil(tp);
        nouveau.setMdputilisateur(utilisateurService.getUserById(idUtilisateurInt).getMdputilisateur());
        Integer i = utilisateurService.patchUtilisateurById(idUtilisateurInt,nouveau);
        return i > 0 ? ResponseEntity.ok(null) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Void> postNewUtilisateur(UtilisateurJson utilisateurJson) {
        Utilisateur.TypeProfil tp = utilisateurJson.getTypeProfilUtilisateur().equals("ADMINISTRATEUR") ? Utilisateur.TypeProfil.ADMINISTRATEUR : Utilisateur.TypeProfil.UTILISATEUR;
        Utilisateur nouveau = UtilisateurMapper.INSTANCE.map(utilisateurJson);
        nouveau.setMdputilisateur(passwordEncoder.encode(utilisateurJson.getMdpUtilisateur()));
        nouveau.setTypeprofil(tp);
        Integer i = utilisateurService.postNewUtilisateur(nouveau);
        return i > 0 ? ResponseEntity.ok(null) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UtilisateurJson>> postUtilisateursWithLimit(PostMediasWithLimitsRequestJson postMediasWithLimitsRequestJson) {
        List<UtilisateurJson> l = UtilisateurMapper.INSTANCE.mapToJson(utilisateurService.postUtilisateursWithLimit(postMediasWithLimitsRequestJson.getLimit(), postMediasWithLimitsRequestJson.getPage()));
        return !l.isEmpty() ? ResponseEntity.ok(l) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

