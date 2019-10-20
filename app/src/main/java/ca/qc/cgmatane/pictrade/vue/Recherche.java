package ca.qc.cgmatane.pictrade.vue;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.controleur.ControleurRecherche;
import ca.qc.cgmatane.pictrade.donnee.Dictionnaire;
import ca.qc.cgmatane.pictrade.helper.ListeFavoriAddapter;
import ca.qc.cgmatane.pictrade.modele.Commerce;

public class Recherche extends AppCompatActivity implements
        VueRecherche, SearchView.OnQueryTextListener, Dictionnaire {

    protected ListView vueRechercheListeCommerce;
    protected ListView vueRechercheListeFavori;
    protected List<HashMap<String, String>> listeCommercePourAdaptateur;
    protected ArrayList<Commerce> listeFavori;
    protected List<Commerce> listeCommerce;
    protected ArrayList<Commerce> nomCommerce;

    protected ListeFavoriAddapter adapterCommerce;
    protected ListeFavoriAddapter adapterFavori;

    protected  SearchView vueRechercheRechercher;


    protected ControleurRecherche controleurRecherche = new ControleurRecherche(this);

    private Intent intentionNaviguerAfficherCommerce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_recherche);
        controleurRecherche.onCreate(getApplicationContext());
    }

    @Override
    public void afficherLesCommerces() {
        this.vueRechercheRechercher = (SearchView) findViewById(R.id.vue_recherche_rechercher);
        this.vueRechercheRechercher.setOnQueryTextListener(this);

        afficherListeCommerces();
        afficherListeCommercesFavoris();
    }


    public void afficherListeCommerces() {
        //On localise la la liste dans notre vue
        vueRechercheListeCommerce = (ListView) findViewById(R.id.vue_recherche_liste_commerce);

        //On passe le tout à notre adapterCommerce
        adapterCommerce = new ListeFavoriAddapter(this, listeCommerce);

        vueRechercheListeCommerce.setAdapter(adapterCommerce);

        vueRechercheListeCommerce.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View vue,
                                            int positionDansAdapteur,
                                            long positionItem) {

                        Log.d("Recherche", "onItemClick");
                        ListView vueRechercheListeCommerceOnClick = (ListView) vue.getParent();

                        Commerce commerce =
                                (Commerce)
                                        vueRechercheListeCommerceOnClick.getItemAtPosition((int) positionItem);
                        HashMap<String, String> commerceNavigation = commerce.obtenirCommercePourAdapteur();
                        controleurRecherche.actionNaviguerAfficherCommerce(Integer.parseInt(commerceNavigation.get(Commerce.CLE_ID_COMMERCE)));
                    }
                }
        );
    }

    public void afficherListeCommercesFavoris() {
        //On localise la la liste dans notre vue
        vueRechercheListeFavori = (ListView) findViewById(R.id.vue_recherche_liste_favoris);

        //On passe le tout à notre adapterCommerce
        adapterFavori = new ListeFavoriAddapter(this, listeFavori);

        vueRechercheListeFavori.setAdapter(adapterFavori);

        vueRechercheListeFavori.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View vue,
                                            int positionDansAdapteur,
                                            long positionItem) {

                        Log.d("Recherche", "onItemClick");
                        ListView vueRechercheListeFavoriOnClick = (ListView) vue.getParent();

                        Commerce commerce =
                                (Commerce)
                                        vueRechercheListeFavoriOnClick.getItemAtPosition((int) positionItem);
                        HashMap<String, String> commerceNavigation = commerce.obtenirCommercePourAdapteur();
                        controleurRecherche.actionNaviguerAfficherCommerce(Integer.parseInt(commerceNavigation.get(Commerce.CLE_ID_COMMERCE)));
                    }
                }
        );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controleurRecherche.onActivityResult(requestCode);
    }

    @Override
    public void listeCommerceEnAttente() {
    }

    @Override
    public void naviguerAfficherCommerce(int id) {
        intentionNaviguerAfficherCommerce = new Intent(
                Recherche.this,
                AfficherCommerce.class
        );
        intentionNaviguerAfficherCommerce.putExtra(Commerce.CLE_ID_COMMERCE, id);
        startActivityForResult(intentionNaviguerAfficherCommerce,controleurRecherche.ACTIVITE_AFFICHER_COMMERCE);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null) {
            newText = " ";
        }
        String text = newText;
        controleurRecherche.effectuerUneRecherche(text);
        return false;
    }

    public boolean demarrerLeFiltrage(String text){
        adapterFavori.filter(text);
        adapterCommerce.filter(text);

        return false;
    }

    @Override
    public void setListeCommerce(List<Commerce> listeCommerce) {
        this.listeCommerce = listeCommerce;
    }

    @Override
    public void setListeCommercePourAdapteur(List<HashMap<String, String>> listeCommercePourAdapteur) {
        this.listeCommercePourAdaptateur = listeCommercePourAdapteur;
    }

    public ArrayList<Commerce> getNomCommerce() {
        return nomCommerce;
    }

    public void setNomCommerce(ArrayList<Commerce> nomCommerce) {
        this.nomCommerce = nomCommerce;
    }

    public ArrayList<Commerce> getListeFavori() {
        return listeFavori;
    }

    public void setListeFavori(ArrayList<Commerce> listeFavori) {
        this.listeFavori = listeFavori;
    }
}
