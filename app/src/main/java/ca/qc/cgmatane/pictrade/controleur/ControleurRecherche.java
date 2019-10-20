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

    static final public int ACTIVITE_AFFICHER_COMMERCE = 1;
    private List<Commerce> listeCommerce;
    private final VueRecherche vue;
    private CommerceDAO accesseurCommerce;
    private FavoriDAO accesseurFavori;

    public ControleurRecherche(VueRecherche vue) {
        this.vue = vue;
    }

    @Override
    public void onCreate(Context applicationContext) {
        accesseurCommerce = CommerceDAO.getInstance();
        accesseurFavori = FavoriDAO.getInstance();
        lancerTacheRecupererListeCommerce();

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
        if (activite == ACTIVITE_AFFICHER_COMMERCE)
        {
            lancerTacheRecupererListeCommerce();
        }
    }

    private void lancerTacheRecupererListeCommerce() {

        AsyncTask<String, String, List<HashMap<String, String>>> recupererListeCommerce = new RecupererListeCommerce();
        recupererListeCommerce.execute();
    }

    private class RecupererListeCommerce extends AsyncTask<String, String, List<HashMap<String, String>>> {

        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            List<HashMap<String, String>> listeCommerce = accesseurCommerce.recupererListeCommercePourAdapteur();

            vue.setListeCommercePourAdapteur(accesseurCommerce.recupererListeCommercePourAdapteur()); ///fix temporaire
            return listeCommerce;
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

    private void listerNomCommerce() {
        ArrayList<Commerce> nomCommerce = new ArrayList<>(listeCommerce);

        vue.setNomCommerce(nomCommerce);
    }

    private void listerFavori(){
        ArrayList<Favori> listeFav = accesseurFavori.listerUniquementFavori();
        System.out.println("liste des uniquement favori" + listeFav);
        ArrayList<Commerce> listeFavori = new ArrayList<>(listeCommerce);

        ArrayList<Commerce>listeCommerceFavori = new ArrayList<>();

        for (int i = 0; i < listeFav.size(); i++) {
            listeCommerceFavori.add(new Commerce(listeFav.get(i).getId_commerce()));
        }

        listeFavori.retainAll(listeCommerceFavori);
        System.out.println("liste des comemrce favoris" + listeFavori);

        vue.setListeFavori(listeFavori);
    }
}
