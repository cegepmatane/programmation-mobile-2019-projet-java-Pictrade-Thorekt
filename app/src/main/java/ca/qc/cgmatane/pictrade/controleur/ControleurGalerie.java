package ca.qc.cgmatane.pictrade.controleur;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.pictrade.donnee.Dictionnaire;
import ca.qc.cgmatane.pictrade.donnee.PhotoDAO;
import ca.qc.cgmatane.pictrade.modele.Photo;
import ca.qc.cgmatane.pictrade.vue.VueGalerie;

public class ControleurGalerie implements Controleur, Dictionnaire {

    static final public int ACTIVITE_PRENDRE_PHOTO = 1;
    private VueGalerie vue;
    private PhotoDAO accesseurPhoto = PhotoDAO.getInstance();
    private int id_commerce;

    public ControleurGalerie(VueGalerie vue) {
        this.vue = vue;
    }

    @Override
    public void onCreate(Context applicationContext) {
        Bundle parametres = vue.getParametres();

        HashMap<String, String> parametresPost;
        parametresPost = new HashMap<>();
        id_commerce = -1;
        id_commerce = (Integer) parametres.get(CLE_ID_COMMERCE);
        if (id_commerce != -1) {
            parametresPost.put(CLE_ID_COMMERCE, id_commerce + "");
            lancerTacheRecupererListePhoto(parametresPost);
        }
    }

    private void lancerTacheRecupererListePhoto(HashMap<String, String> parametresPost) {
        AsyncTask<HashMap<String, String>, String, List<Photo>> recupererListePhoto =
                new RecupererListePhoto();
        recupererListePhoto.execute(parametresPost);
    }

    private void lancerTacheAjouterPhoto(HashMap<String, String> parametresPost) {
        AsyncTask<HashMap<String, String>, String, String> ajouterPhoto =
                new AjouterPhoto();
        ajouterPhoto.execute(parametresPost);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onActivityResult(int activite) {
        if (activite == ACTIVITE_PRENDRE_PHOTO) {
            HashMap<String, String> parametresPost;
            parametresPost = new HashMap<>();
            Bundle extras = vue.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            parametresPost.put(CLE_ID_COMMERCE, id_commerce + "");
            parametresPost.put(CLE_IMAGE_PHOTO, Photo.BitMapToString(imageBitmap));
            lancerTacheAjouterPhoto(parametresPost);
        }
    }

    public void actionNaviguerPrendrePhoto() {
        vue.naviguerPrendrePhoto();
    }

    private class RecupererListePhoto extends AsyncTask<HashMap<String, String>, String, List<Photo>> {

        @Override
        protected List<Photo> doInBackground(HashMap<String, String>... hashMaps) {
            List<Photo> listePhoto = accesseurPhoto.listerPhotoParIdCommerce(hashMaps[0]);
            return listePhoto;
        }

        @Override
        protected void onPostExecute(List<Photo> listePhoto) {
            super.onPostExecute(listePhoto);
            vue.setListePhoto(listePhoto);
            vue.afficherGalerie();
        }
    }

    private class AjouterPhoto extends AsyncTask<HashMap<String, String>, String, String> {


        @Override
        protected String doInBackground(HashMap<String, String>... hashMaps) {
            String resultat = accesseurPhoto.ajouterCommerce(hashMaps[0]);
            return resultat;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Bundle parametres = vue.getParametres();

            HashMap<String, String> parametresPost;
            parametresPost = new HashMap<>();

            parametresPost.put(CLE_ID_COMMERCE, id_commerce + "");
            lancerTacheRecupererListePhoto(parametresPost);

            lancerTacheRecupererListePhoto(parametresPost);
        }
    }
}
