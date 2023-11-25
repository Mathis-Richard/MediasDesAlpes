package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "Typemedia")
@Table(name = "typemedia")
public class TypeMediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "idtype")
    private Set<MediaEntity> media = new LinkedHashSet<>();

    public Set<MediaEntity> getMedia() {
        return media;
    }

    public void setMedia(Set<MediaEntity> media) {
        this.media = media;
    }

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