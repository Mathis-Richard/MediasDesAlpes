package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.controller;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.api.VersionApi;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl.VersionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "null", originPatterns = {"http://localhost:[*]"}, allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/api")
public class VersionController implements VersionApi {

    private VersionServiceImpl versionServiceImpl;

    @Autowired
    public VersionController(VersionServiceImpl versionServiceImpl){
        this.versionServiceImpl = versionServiceImpl;
    }

    @Override
    public ResponseEntity<String> getVersion() {
        return ResponseEntity.ok(versionServiceImpl.getVersion());
    }
}
