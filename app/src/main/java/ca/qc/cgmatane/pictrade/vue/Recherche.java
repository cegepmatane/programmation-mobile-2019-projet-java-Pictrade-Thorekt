package ca.qc.cgmatane.pictrade.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.donnee.CommerceDAO;

public class Recherche extends AppCompatActivity {
    protected CommerceDAO accesseurCommerce ;
    protected ListView vueListeCommerces;
    protected List<HashMap<String, String>> listeCommercePourAdaptateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_recherche_commerce);
        afficherLesCommerces();
    }

    protected void afficherLesCommerces() {
        accesseurCommerce = CommerceDAO.getInstance();

       vueListeCommerces = (ListView) findViewById(R.id.vue_liste_commerce);

        listeCommercePourAdaptateur = accesseurCommerce.recupererListeCommercePourAdapteur();

        SimpleAdapter adapteurVueListeCommerce = new SimpleAdapter(this,
                listeCommercePourAdaptateur,
                android.R.layout.two_line_list_item,
                new String[]{"Nom", "Adresse"},
                new int[]{android.R.id.text1, android.R.id.text2});

        vueListeCommerces.setAdapter(adapteurVueListeCommerce);
    }


}
