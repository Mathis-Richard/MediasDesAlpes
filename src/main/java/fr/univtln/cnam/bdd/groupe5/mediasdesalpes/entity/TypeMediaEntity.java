package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "Typemedia")
@Table(name = "typemedia")
public class TypeMediaEntity {
    @Id
    @Column(name = "idtype", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "designationtype", nullable = false)
    private String designationtype;

    @Size(max = 255)
    @NotNull
    @Column(name = "designationauteur", nullable = false)
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