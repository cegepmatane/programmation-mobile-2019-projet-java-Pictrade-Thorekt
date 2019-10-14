package ca.qc.cgmatane.pictrade.controleur;

import android.content.Context;
import android.os.AsyncTask;

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

    private class RecupererListeCommerce extends AsyncTask<String, String, List<Commerce>> {

        @Override
        protected List<Commerce> doInBackground(String... strings) {
            List<Commerce> listeCommerce = accesseurCommerce.listerCommerce();
            return listeCommerce;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Commerce> listeCommerceRecuperer) {
            super.onPostExecute(listeCommerceRecuperer);
            listeCommerce = listeCommerceRecuperer;
            vue.setListeCommerce(listeCommerce);
            vue.afficherLesCommerces();
        }
    }
}
