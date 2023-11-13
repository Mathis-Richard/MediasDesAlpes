package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "MediaEntity")
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
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idtype", nullable = false)
    private TypeMediaEntity idtype;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idgenre", nullable = false)
    private GenreMediaEntity idgenre;

    @Column(name = "anneepublicationsortie")
    private Integer anneepublicationsortie;

    @Column(name = "notemedia")
    private Double notemedia;

    @Size(max = 1000)
    @Column(name = "imgmedia", length = 1000)
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

    public GenreMediaEntity getIdgenre() {
        return idgenre;
    }

    public void setIdgenre(GenreMediaEntity idgenre) {
        this.idgenre = idgenre;
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