package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity(name = "Nationaliteauteur")
@Table(name = "nationaliteauteur")
public class NationaliteAuteurEntity {
    @Id
    @Column(name = "idnationalite", nullable = false)
    private Integer id;

    @Size(max = 30)
    @Column(name = "designation", length = 30)
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