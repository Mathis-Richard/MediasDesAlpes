package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity(name = "Emprunt")
@Table(name = "emprunt")
public class EmpruntEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idemprunt", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idutilisateur", nullable = false)
    private UtilisateurEntity idutilisateur;

    @Column(name = "datereservation")
    private LocalDate datereservation;

    @NotNull
    @Column(name = "dateemprunt", nullable = false)
    private LocalDate dateemprunt;

    @NotNull
    @Column(name = "dateretourprevue", nullable = false)
    private LocalDate dateretourprevue;

    @Column(name = "dateretourreelle")
    private LocalDate dateretourreelle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statutemprunt")
    private StatutEmpruntEntity statutEmpruntEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idexemplaire", nullable = false)
    private ExemplaireMediaEntity idexemplaire;

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

    public LocalDate getDatereservation() {
        return datereservation;
    }

    public void setDatereservation(LocalDate datereservation) {
        this.datereservation = datereservation;
    }

    public LocalDate getDateemprunt() {
        return dateemprunt;
    }

    public void setDateemprunt(LocalDate dateemprunt) {
        this.dateemprunt = dateemprunt;
    }

    public LocalDate getDateretourprevue() {
        return dateretourprevue;
    }

    public void setDateretourprevue(LocalDate dateretourprevue) {
        this.dateretourprevue = dateretourprevue;
    }

    public LocalDate getDateretourreelle() {
        return dateretourreelle;
    }

    public void setDateretourreelle(LocalDate dateretourreelle) {
        this.dateretourreelle = dateretourreelle;
    }

    public StatutEmpruntEntity getStatutemprunt() {
        return statutEmpruntEntity;
    }

    public void setStatutemprunt(StatutEmpruntEntity statutEmpruntEntity) {
        this.statutEmpruntEntity = statutEmpruntEntity;
    }

    public ExemplaireMediaEntity getIdexemplaire() {
        return idexemplaire;
    }

    public void setIdexemplaire(ExemplaireMediaEntity idexemplaire) {
        this.idexemplaire = idexemplaire;
    }

}