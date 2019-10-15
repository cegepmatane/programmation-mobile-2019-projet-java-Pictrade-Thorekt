package ca.qc.cgmatane.pictrade.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.controleur.ControleurRecherche;
import ca.qc.cgmatane.pictrade.modele.Commerce;

public class Recherche extends AppCompatActivity implements VueRecherche, SearchView.OnQueryTextListener {
    protected ListView vueListeCommerces;
    protected List<HashMap<String, String>> listeCommercePourAdaptateur;
    protected ControleurRecherche controleurRecherche = new ControleurRecherche(this);
    private List<Commerce> listeCommerce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_recherche_commerce);

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
                new String[]{"nom", "adresse"},
                new int[]{android.R.id.text1, android.R.id.text2});

        vueListeCommerces.setAdapter(adapteurVueListeCommerce);
        Log.d("tet",""+adapteurVueListeCommerce);
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
}
