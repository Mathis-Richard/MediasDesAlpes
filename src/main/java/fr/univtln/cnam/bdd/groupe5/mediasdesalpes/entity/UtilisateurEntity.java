package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Date;

@Entity(name = "Utilisateur")
@Table(name = "utilisateur")
public class UtilisateurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idutilisateur", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "emailutilisateur", nullable = false)
    private String emailutilisateur;

    @Size(max = 255)
    @NotNull
    @Column(name = "nomutilisateur", nullable = false)
    private String nomutilisateur;

    @Size(max = 255)
    @NotNull
    @Column(name = "prenomutilisateur", nullable = false)
    private String prenomutilisateur;

    @Size(max = 255)
    @NotNull
    @Column(name = "adresseutilisateur", nullable = false)
    private String adresseutilisateur;

    @Size(max = 20)
    @NotNull
    @Column(name = "numtelephoneutilisateur", nullable = false, length = 20)
    private String numtelephoneutilisateur;

    @Size(max = 255)
    @NotNull
    @Column(name = "mdputilisateur", nullable = false)
    private String mdputilisateur;

    @Column(name = "datenaissanceutilisateur", nullable = false)
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

    public Date getDatenaissanceutilisateur() {
        return datenaissanceutilisateur;
    }

    public void setDatenaissanceutilisateur(Date datenaissanceutilisateur) {
        this.datenaissanceutilisateur = datenaissanceutilisateur;
    }

    public enum TypeProfil {
        UTILISATEUR,ADMINISTRATEUR
    }

    @Column(name = "typeprofil")
    @Enumerated(EnumType.STRING)
    private TypeProfil typeprofil;

    public TypeProfil getTypeprofil() {
        return typeprofil;
    }

    public void setTypeprofil(TypeProfil typeprofil) {
        this.typeprofil = typeprofil;
    }

}