package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "null", originPatterns = {"http://localhost:[*]"}, allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/first")
    public String premierTest(){
        return "It works";
    }
}
