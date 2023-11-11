package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TypeMedia {

    private Integer id;
    private String designationtype;
    private String designationauteur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesignationtype() {
        return designationtype;
    }

    public void setDesignationtype(String designationtype) {
        this.designationtype = designationtype;
    }

    public String getDesignationauteur() {
        return designationauteur;
    }

    public void setDesignationauteur(String designationauteur) {
        this.designationauteur = designationauteur;
    }

}