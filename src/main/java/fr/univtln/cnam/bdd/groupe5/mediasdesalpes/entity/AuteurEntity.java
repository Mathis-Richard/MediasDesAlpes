package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "Auteur")
@Table(name = "auteur")
public class AuteurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idauteur", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nomauteur", nullable = false)
    private String nomauteur;

    @Size(max = 255)
    @Column(name = "prenomauteur")
    private String prenomauteur;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "nationaliteauteur", nullable = false)
    private NationaliteAuteurEntity nationaliteauteur;

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

    public NationaliteAuteurEntity getNationaliteauteur() {
        return nationaliteauteur;
    }

    public void setNationaliteauteur(NationaliteAuteurEntity nationaliteauteur) {
        this.nationaliteauteur = nationaliteauteur;
    }

}