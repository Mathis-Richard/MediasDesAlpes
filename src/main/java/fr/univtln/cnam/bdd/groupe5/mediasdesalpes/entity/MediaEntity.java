package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "media")
public class MediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmedia", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "titre")
    private String titre;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idtype", nullable = false)
    private TypeMediaEntity idtype;

    @Column(name = "anneepublicationsortie")
    private Integer anneepublicationsortie;

    @Column(name = "notemedia")
    private Double notemedia;

    @Size(max = 500)
    @Column(name = "imgmedia", length = 500)
    private String imgmedia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public TypeMediaEntity getIdtype() {
        return idtype;
    }

    public void setIdtype(TypeMediaEntity idtype) {
        this.idtype = idtype;
    }

    public Integer getAnneepublicationsortie() {
        return anneepublicationsortie;
    }

    public void setAnneepublicationsortie(Integer anneepublicationsortie) {
        this.anneepublicationsortie = anneepublicationsortie;
    }

    public Double getNotemedia() {
        return notemedia;
    }

    public void setNotemedia(Double notemedia) {
        this.notemedia = notemedia;
    }

    public String getImgmedia() {
        return imgmedia;
    }

    public void setImgmedia(String imgmedia) {
        this.imgmedia = imgmedia;
    }

}