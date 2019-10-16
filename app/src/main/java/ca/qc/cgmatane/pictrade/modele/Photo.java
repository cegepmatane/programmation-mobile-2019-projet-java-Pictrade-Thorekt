package ca.qc.cgmatane.pictrade.modele;

import android.media.Image;

public class Photo {
    private int id;
    private int nom;
    private Image image;

    public Photo(int id, int nom, Image image) {
        this.id = id;
        this.nom = nom;
        this.image = image;
    }

    public Photo() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNom() {
        return nom;
    }

    public void setNom(int nom) {
        this.nom = nom;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


}
