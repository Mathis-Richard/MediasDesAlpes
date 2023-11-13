package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity;

import jakarta.persistence.*;

@Entity(name = "Auteurmedia")
@Table(name = "auteurmedia")
public class AuteurMediaEntity {
    @EmbeddedId
    private AuteurMediaId id;

    @MapsId("idauteur")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idauteur", nullable = false)
    private AuteurEntity idauteur;

    @MapsId("idmedia")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idmedia", nullable = false)
    private MediaEntity idmedia;

    public AuteurMediaId getId() {
        return id;
    }

    public void setId(AuteurMediaId id) {
        this.id = id;
    }

    public AuteurEntity getIdauteur() {
        return idauteur;
    }

    public void setIdauteur(AuteurEntity idauteur) {
        this.idauteur = idauteur;
    }

    public MediaEntity getIdmedia() {
        return idmedia;
    }

    public void setIdmedia(MediaEntity idmedia) {
        this.idmedia = idmedia;
    }

}