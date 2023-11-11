package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model;

public class Media {

    private Integer id;

    private String titre;

    private TypeMedia idtype;

    private GenreMedia idgenre;

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

    public TypeMedia getIdtype() {
        return idtype;
    }

    public void setIdtype(TypeMedia idtype) {
        this.idtype = idtype;
    }

    public GenreMedia getIdgenre() {
        return idgenre;
    }

    public void setIdgenre(GenreMedia idgenre) {
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