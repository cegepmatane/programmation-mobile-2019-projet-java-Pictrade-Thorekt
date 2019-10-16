package ca.qc.cgmatane.pictrade.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.controleur.ControleurRecherche;
import ca.qc.cgmatane.pictrade.donnee.Dictionnaire;
import ca.qc.cgmatane.pictrade.helper.SearchableActivity;
import ca.qc.cgmatane.pictrade.modele.Commerce;

public class Recherche extends AppCompatActivity implements
        VueRecherche, SearchView.OnQueryTextListener, Dictionnaire {

    protected ListView vueListeCommerces;
    protected List<HashMap<String, String>> listeCommercePourAdaptateur;
    protected ControleurRecherche controleurRecherche = new ControleurRecherche(this);
    private List<Commerce> listeCommerce;
    private Intent intentionNaviguerAfficherCommerce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_recherche_commerce);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) findViewById(R.id.vue_recherche_rechercher);;
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        controleurRecherche.onCreate(getApplicationContext());
    }

    @Override
    public void afficherLesCommerces() {
        afficherListeCommerces();
        afficherListeCommercesFavoris();
    }

    public void afficherListeCommerces(){
        vueListeCommerces = (ListView) findViewById(R.id.vue_recherche_liste_commerce);
        SimpleAdapter adapteurVueListeCommerce = new SimpleAdapter(this,
                listeCommercePourAdaptateur,
                android.R.layout.two_line_list_item,
                new String[]{CLE_NOM_COMMERCE, CLE_ADRESSE_COMMERCE},
                new int[]{android.R.id.text1, android.R.id.text2});

        vueListeCommerces.setAdapter(adapteurVueListeCommerce);

        vueListeCommerces.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View vue,
                                            int positionDansAdapteur,
                                            long positionItem) {
                        Log.d("Recherche", "onItemClick");
                        ListView vueRechercheListeCommerceOnClick = (ListView)vue.getParent();

                        HashMap<String,String> commerce =
                                (HashMap<String, String>)
                                        vueRechercheListeCommerceOnClick.getItemAtPosition((int)positionItem);

                        controleurRecherche.actionNaviguerAfficherCommerce(commerce.get(Commerce.CLE_ID_COMMERCE));
                    }
                }
        );
    }

    public void afficherListeCommercesFavoris(){

    }


    @Override
    public void listeCommerceEnAttente() {

    }

    @Override
    public void setListeCommerce(List<Commerce> listeCommerce) {
        this.listeCommerce = listeCommerce;
    }

    @Override
    public void setListeCommercePourAdapteur(List<HashMap<String, String>> listeCommercePourAdapteur) {
        this.listeCommercePourAdaptateur = listeCommercePourAdapteur;
    }

    @Override
    public void naviguerAfficherCommerce(String idCommerce) {

        intentionNaviguerAfficherCommerce = new Intent(
                Recherche.this,
                AfficherCommerce.class
        );
        intentionNaviguerAfficherCommerce.putExtra(Commerce.CLE_ID_COMMERCE, idCommerce);

        startActivity(intentionNaviguerAfficherCommerce);

    }

    // TODO : Faire ces méthodes correctement

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        //adapter.filter(text);
        return false;
    }

    public void chercher(Intent intentionRecherche) {
        startActivity(intentionRecherche);
    }
}
