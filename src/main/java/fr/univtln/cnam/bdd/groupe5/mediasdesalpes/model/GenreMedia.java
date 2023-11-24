package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model;


public class GenreMedia {

    private Integer id;

    private String designation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public GenreMedia(Integer id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    public GenreMedia(Integer id) {
        this.id = id;
        this.designation = "";
    }

    public GenreMedia() {
    }
}