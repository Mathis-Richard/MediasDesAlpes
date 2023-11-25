package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.controller;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.EmpruntsApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.json.*;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.mapper.EmpruntMapper;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl.EmpruntServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin(origins = "null", originPatterns = {"http://web:[*]", "http://postgres:[*]", "http://localhost:[*]"}, allowedHeaders = "*", allowCredentials = "true")
public class EmpruntController implements EmpruntsApi {

    private EmpruntServiceImpl empruntServiceImpl;

    @Autowired
    public EmpruntController(EmpruntServiceImpl empruntServiceImpl) {
        this.empruntServiceImpl = empruntServiceImpl;
    }


    @Override
    public ResponseEntity<List<EmpruntJson>> getEmprunts() {
        return ResponseEntity.ok(EmpruntMapper.INSTANCE.mapToJson(empruntServiceImpl.getEmprunts()));
    }

    @Override
    public ResponseEntity<List<EmpruntJson>> getEmpruntsAnnule() {
        return ResponseEntity.ok(EmpruntMapper.INSTANCE.mapToJson(empruntServiceImpl.getEmpruntsAnnule()));
    }

    @Override
    public ResponseEntity<List<EmpruntJson>> getEmpruntsAttente() {
        return ResponseEntity.ok(EmpruntMapper.INSTANCE.mapToJson(empruntServiceImpl.getEmpruntsAttente()));
    }

    @Override
    public ResponseEntity<List<EmpruntJson>> getEmpruntsEncours() {
        return ResponseEntity.ok(EmpruntMapper.INSTANCE.mapToJson(empruntServiceImpl.getEmpruntsEncours()));
    }

    @Override
    public ResponseEntity<List<EmpruntJson>> getEmpruntsTermine() {
        return ResponseEntity.ok(EmpruntMapper.INSTANCE.mapToJson(empruntServiceImpl.getEmpruntsTermine()));
    }

    @Override
    public ResponseEntity<Void> postEmpruntCancel(String idExemplaire, PostEmpruntStartRequestJson postEmpruntStartRequestJson) {
        return ResponseEntity.ok(empruntServiceImpl.postEmpruntCancel(idExemplaire, postEmpruntStartRequestJson));
    }

    @Override
    public ResponseEntity<Void> postEmpruntReturn(String idExemplaire, PostEmpruntStartRequestJson postEmpruntStartRequestJson) {
        return ResponseEntity.ok(empruntServiceImpl.postEmpruntReturn(idExemplaire, postEmpruntStartRequestJson));
    }

    @Override
    public ResponseEntity<Void> postEmpruntStart(String idExemplaire, PostEmpruntStartRequestJson postEmpruntStartRequestJson) {
        return ResponseEntity.ok(empruntServiceImpl.postEmpruntStart(idExemplaire, postEmpruntStartRequestJson));
    }
}
