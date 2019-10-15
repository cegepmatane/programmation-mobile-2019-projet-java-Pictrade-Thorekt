package ca.qc.cgmatane.pictrade.controleur;

import android.content.Context;
import android.os.AsyncTask;

import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.pictrade.donnee.CommerceDAO;
import ca.qc.cgmatane.pictrade.modele.Commerce;
import ca.qc.cgmatane.pictrade.vue.VueRecherche;

public class ControleurRecherche implements Controleur {
    private List<Commerce> listeCommerce;
    private VueRecherche vue;
    private CommerceDAO accesseurCommerce;

    public ControleurRecherche(VueRecherche vue) {
        this.vue = vue;
    }

    @Override
    public void onCreate(Context applicationContext) {
        accesseurCommerce = CommerceDAO.getInstance();
        AsyncTask<String, String, List<HashMap<String,String>>> recupererListeCommerce = new RecupererListeCommerce();
        recupererListeCommerce.execute();

    }

    public void suggererRecherche(String requete){

    }

    public void effectuerUneRecherche(String requete){

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

    }

    private class RecupererListeCommerce extends AsyncTask<String, String, List<HashMap<String,String>>> {

        @Override
        protected List<HashMap<String,String>> doInBackground(String... strings) {
            List<HashMap<String,String>> listeCommerce = accesseurCommerce.recupererListeCommercePourAdapteur();

            vue.setListeCommercePourAdapteur(accesseurCommerce.recupererListeCommercePourAdapteur()); ///fix temporaire
            return listeCommerce;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<HashMap<String,String>> listeCommerceRecuperer) {
            super.onPostExecute(listeCommerceRecuperer);
            vue.setListeCommercePourAdapteur(listeCommerceRecuperer);
            vue.setListeCommerce(listeCommerce);
            vue.afficherLesCommerces();
        }
    }
}
