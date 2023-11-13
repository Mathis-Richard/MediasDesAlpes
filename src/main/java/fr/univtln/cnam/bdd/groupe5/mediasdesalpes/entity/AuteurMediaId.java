package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AuteurMediaId implements Serializable {
    private static final long serialVersionUID = -3562956130074263492L;
    @NotNull
    @Column(name = "idauteur", nullable = false)
    private Integer idauteur;

    @NotNull
    @Column(name = "idmedia", nullable = false)
    private Integer idmedia;

    public Integer getIdauteur() {
        return idauteur;
    }

    public void setIdauteur(Integer idauteur) {
        this.idauteur = idauteur;
    }

    public Integer getIdmedia() {
        return idmedia;
    }

    public void setIdmedia(Integer idmedia) {
        this.idmedia = idmedia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AuteurMediaId entity = (AuteurMediaId) o;
        return Objects.equals(this.idauteur, entity.idauteur) &&
                Objects.equals(this.idmedia, entity.idmedia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idauteur, idmedia);
    }

}