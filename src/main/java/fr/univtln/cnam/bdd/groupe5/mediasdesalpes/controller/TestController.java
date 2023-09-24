package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.controller;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@CrossOrigin(origins = "null", originPatterns = {"http://localhost:[*]"}, allowedHeaders = "*", allowCredentials = "true")
@EnableJpaRepositories(basePackages = "fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository")
@RequestMapping("/api")
public class TestController {

    private TestService testService;

    @Autowired
    public TestController(TestService testService){
        this.testService = testService;
    }

    @GetMapping("/first")
    public String premierTest() throws SQLException {
        return testService.getVersion();
    }

    @GetMapping("/version")
    public String versionTest() throws SQLException {
        return testService.getVersion();
    }
}
