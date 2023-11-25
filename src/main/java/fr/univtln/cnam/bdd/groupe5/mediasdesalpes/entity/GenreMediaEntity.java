package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "Genremedia")
@Table(name = "genremedia")
public class GenreMediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgenre", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "designation", nullable = false)
    private String designation;

    @OneToMany(mappedBy = "idgenre")
    private Set<MediaEntity> media = new LinkedHashSet<>();

    public GenreMediaEntity(Integer id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    public GenreMediaEntity() {

    }

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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

}