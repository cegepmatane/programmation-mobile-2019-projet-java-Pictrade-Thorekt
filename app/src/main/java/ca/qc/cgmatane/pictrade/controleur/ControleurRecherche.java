package ca.qc.cgmatane.pictrade.controleur;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.pictrade.donnee.CommerceDAO;
import ca.qc.cgmatane.pictrade.donnee.FavoriDAO;
import ca.qc.cgmatane.pictrade.modele.Commerce;
import ca.qc.cgmatane.pictrade.modele.Favori;
import ca.qc.cgmatane.pictrade.vue.VueRecherche;

public class ControleurRecherche implements Controleur {

    private List<Commerce> listeCommerce;
    private VueRecherche vue;
    private CommerceDAO accesseurCommerce;
    private FavoriDAO accesseurFavori;

    public ControleurRecherche(VueRecherche vue) {
        this.vue = vue;
    }

    @Override
    public void onCreate(Context applicationContext) {
        accesseurCommerce = CommerceDAO.getInstance();
        accesseurFavori = FavoriDAO.getInstance();
        AsyncTask<String, String, List<HashMap<String, String>>> recupererListeCommerce = new RecupererListeCommerce();
        recupererListeCommerce.execute();

    }

    public void actionNaviguerAfficherCommerce(int id) {
        vue.naviguerAfficherCommerce(id);
    }

    public void effectuerUneRecherche(String requete) {
        vue.demarrerLeFiltrage(requete);
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

    private class RecupererListeCommerce extends AsyncTask<String, String, List<HashMap<String, String>>> {

        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            List<HashMap<String, String>> listeCommerce = accesseurCommerce.recupererListeCommercePourAdapteur();

            vue.setListeCommercePourAdapteur(accesseurCommerce.recupererListeCommercePourAdapteur()); ///fix temporaire
            return listeCommerce;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> listeCommerceRecuperer) {
            super.onPostExecute(listeCommerceRecuperer);
            vue.setListeCommercePourAdapteur(listeCommerceRecuperer);
            listeCommerce=accesseurCommerce.getListeCommerces();
            Log.d("DEBUG", "onPostExecute listeCommerce: " + listeCommerce.toString());
            vue.setListeCommerce(listeCommerce);
            listerNomCommerce();
            listerFavori();
            vue.afficherLesCommerces();
        }
    }

    public void listerNomCommerce() {
        ArrayList<Commerce> nomCommerce = new ArrayList<>();
        for (int i = 0; i < listeCommerce.size(); i++) {
            nomCommerce.add(listeCommerce.get(i));
        }

        vue.setNomCommerce(nomCommerce);
    }

    public void listerFavori(){
        ArrayList<Favori> listeFav = accesseurFavori.listerUniquementFavori();
        ArrayList<Commerce> listeFavori = new ArrayList<>();
        listeFavori.addAll(listeCommerce);
        listeFavori.retainAll(listeFav);

        vue.setListeFavori(listeFavori);
    }
}
