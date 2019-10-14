package ca.qc.cgmatane.pictrade.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.controleur.Controleur;
import ca.qc.cgmatane.pictrade.controleur.ControleurRecherche;
import ca.qc.cgmatane.pictrade.donnee.CommerceDAO;
import ca.qc.cgmatane.pictrade.modele.Commerce;

public class Recherche extends AppCompatActivity implements VueRecherche {
    private  List<Commerce> listeCommerce;
    protected ListView vueListeCommerces;
    protected List<HashMap<String, String>> listeCommercePourAdaptateur;
    protected ControleurRecherche controleurRecherche = new ControleurRecherche(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_recherche_commerce);

        controleurRecherche.onCreate(getApplicationContext());

        afficherLesCommerces();
    }

    @Override
    public void afficherLesCommerces() {

       vueListeCommerces = (ListView) findViewById(R.id.vue_recherche_liste_commerce);

     //   listeCommercePourAdaptateur = accesseurCommerce.recupererListeCommercePourAdapteur();

        SimpleAdapter adapteurVueListeCommerce = new SimpleAdapter(this,
                listeCommercePourAdaptateur,
                android.R.layout.two_line_list_item,
                new String[]{"Nom", "Adresse"},
                new int[]{android.R.id.text1, android.R.id.text2});

        vueListeCommerces.setAdapter(adapteurVueListeCommerce);

    }


    @Override
    public void listeCommerceEnAttente() {

    }

    @Override
    public void setListeCommerce(List<Commerce> listeCommerce) {
        this.listeCommerce=listeCommerce;
    }
}
