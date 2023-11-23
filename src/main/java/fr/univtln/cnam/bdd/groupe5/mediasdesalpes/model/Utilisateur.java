package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model;

import java.sql.Date;

public class Utilisateur {

    public enum TypeProfil {
        UTILISATEUR, ADMINISTRATEUR
    }

    private TypeProfil typeprofil;
    private Integer id;
    private String emailutilisateur;
    private String nomutilisateur;
    private String prenomutilisateur;
    private String adresseutilisateur;
    private String numtelephoneutilisateur;
    private String mdputilisateur;

    public Date getDatenaissanceutilisateur() {
        return datenaissanceutilisateur;
    }

    public void setDatenaissanceutilisateur(Date datenaissanceutilisateur) {
        this.datenaissanceutilisateur = datenaissanceutilisateur;
    }

    private Date datenaissanceutilisateur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailutilisateur() {
        return emailutilisateur;
    }

    public void setEmailutilisateur(String emailutilisateur) {
        this.emailutilisateur = emailutilisateur;
    }

    public String getNomutilisateur() {
        return nomutilisateur;
    }

    public void setNomutilisateur(String nomutilisateur) {
        this.nomutilisateur = nomutilisateur;
    }

    public String getPrenomutilisateur() {
        return prenomutilisateur;
    }

    public void setPrenomutilisateur(String prenomutilisateur) {
        this.prenomutilisateur = prenomutilisateur;
    }

    public String getAdresseutilisateur() {
        return adresseutilisateur;
    }

    public void setAdresseutilisateur(String adresseutilisateur) {
        this.adresseutilisateur = adresseutilisateur;
    }

    public String getNumtelephoneutilisateur() {
        return numtelephoneutilisateur;
    }

    public void setNumtelephoneutilisateur(String numtelephoneutilisateur) {
        this.numtelephoneutilisateur = numtelephoneutilisateur;
    }

    public String getMdputilisateur() {
        return mdputilisateur;
    }

    public void setMdputilisateur(String mdputilisateur) {
        this.mdputilisateur = mdputilisateur;
    }

    public TypeProfil getTypeprofil() {
        return typeprofil;
    }

    public void setTypeprofil(TypeProfil typeprofil) {
        this.typeprofil = typeprofil;
    }

}