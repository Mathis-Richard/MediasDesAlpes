package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "Exemplairemedia")
@Table(name = "exemplairemedia")
public class ExemplaireMediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idexemplaire", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idmedia", nullable = false)
    private MediaEntity idmedia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MediaEntity getIdmedia() {
        return idmedia;
    }

    public void setIdmedia(MediaEntity idmedia) {
        this.idmedia = idmedia;
    }

}