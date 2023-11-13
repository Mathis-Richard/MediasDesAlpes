package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model;

import java.util.List;

public class Media {

    private Integer id;

    private String titre;

    private TypeMedia type;

    private GenreMedia genre;

    private List<Auteur> auteurs;

    private Integer anneepublicationsortie;

    private Double notemedia;

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

    public TypeMedia getType() {
        return type;
    }

    public void setType(TypeMedia type) {
        this.type = type;
    }

    public GenreMedia getGenre() {
        return genre;
    }

    public void setGenre(GenreMedia genre) {
        this.genre = genre;
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

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }
}