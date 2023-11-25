package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.EmpruntEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.ExemplaireMediaEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.UtilisateurEntity;

import java.time.LocalDate;

public class Emprunt {

    private Integer id;
    private UtilisateurEntity idutilisateur;
    private LocalDate datereservation;
    private LocalDate dateemprunt;
    private LocalDate dateretourprevue;
    private LocalDate dateretourreelle;
    private ExemplaireMediaEntity idexemplaire;

    public enum StatutEmprunt {
        R_ATTENTE, R_ANNULE, E_ENCOURS, E_TERMINE, R_RETARD
    }

    private EmpruntEntity.StatutEmprunt statut;

    public EmpruntEntity.StatutEmprunt getStatut() {
        return statut;
    }

    public void setStatut(EmpruntEntity.StatutEmprunt statut) {
        this.statut = statut;
    }


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

    public ExemplaireMediaEntity getIdexemplaire() {
        return idexemplaire;
    }

    public void setIdexemplaire(ExemplaireMediaEntity idexemplaire) {
        this.idexemplaire = idexemplaire;
    }

}

