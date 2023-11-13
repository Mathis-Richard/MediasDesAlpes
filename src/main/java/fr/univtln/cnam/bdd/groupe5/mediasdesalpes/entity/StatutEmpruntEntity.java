package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "Statutemprunt")
@Table(name = "statutemprunt")
public class StatutEmpruntEntity {
    @Id
    @Column(name = "idstatut", nullable = false)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(name = "designation", nullable = false, length = 30)
    private String designation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

}