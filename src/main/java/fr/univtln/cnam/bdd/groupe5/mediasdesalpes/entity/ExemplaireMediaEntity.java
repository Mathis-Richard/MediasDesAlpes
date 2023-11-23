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
    @Column(name = "idmedia", nullable = false)
    private Integer idmedia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdmedia() {
        return idmedia;
    }

    public void setIdmedia(Integer idmedia) {
        this.idmedia = idmedia;
    }

}