package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Auteur {

    private Integer id;
    private String nomauteur;
    private String prenomauteur;
    private NationaliteAuteur nationaliteauteur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomauteur() {
        return nomauteur;
    }

    public void setNomauteur(String nomauteur) {
        this.nomauteur = nomauteur;
    }

    public String getPrenomauteur() {
        return prenomauteur;
    }

    public void setPrenomauteur(String prenomauteur) {
        this.prenomauteur = prenomauteur;
    }

    public NationaliteAuteur getNationaliteauteur() {
        return nationaliteauteur;
    }

    public void setNationaliteauteur(NationaliteAuteur nationaliteauteur) {
        this.nationaliteauteur = nationaliteauteur;
    }
}