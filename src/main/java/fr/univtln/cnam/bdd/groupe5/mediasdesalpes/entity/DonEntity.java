package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity(name = "Don")
@Table(name = "don")
public class DonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddon", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idutilisateur", nullable = false)
    private UtilisateurEntity idutilisateur;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idmedia", nullable = false)
    private MediaEntity idmedia;

    @NotNull
    @Column(name = "datedon", nullable = false)
    private LocalDate datedon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UtilisateurEntity getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(UtilisateurEntity idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public MediaEntity getIdmedia() {
        return idmedia;
    }

    public void setIdmedia(MediaEntity idmedia) {
        this.idmedia = idmedia;
    }

    public LocalDate getDatedon() {
        return datedon;
    }

    public void setDatedon(LocalDate datedon) {
        this.datedon = datedon;
    }

}