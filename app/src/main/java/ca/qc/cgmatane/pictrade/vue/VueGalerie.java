package ca.qc.cgmatane.pictrade.vue;

import android.os.Bundle;

import java.util.List;

import ca.qc.cgmatane.pictrade.modele.Photo;

public interface VueGalerie {
    public void afficherGalerie();
    public void setListePhoto(List<Photo> listePhoto);
    public void naviguerPrendrePhoto();
    public Bundle getParametres();
    public Bundle getExtras();
    public void naviguerCommerce();
}
