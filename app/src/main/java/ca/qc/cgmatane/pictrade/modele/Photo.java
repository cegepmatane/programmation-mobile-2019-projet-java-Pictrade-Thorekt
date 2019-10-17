package ca.qc.cgmatane.pictrade.modele;

import android.graphics.Bitmap;

public class Photo {
    private int id;
    private int nom;
    private Bitmap image;

    public Photo(int id, int nom, Bitmap image) {
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }


}
