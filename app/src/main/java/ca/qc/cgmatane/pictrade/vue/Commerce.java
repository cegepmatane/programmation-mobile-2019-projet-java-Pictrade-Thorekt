package ca.qc.cgmatane.pictrade.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.donnee.CommerceDAO;

public class Commerce extends AppCompatActivity implements VueCommerce {
    protected CommerceDAO accesseurCommerce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_commerce);
    }

    protected void informationCommerces() {
        accesseurCommerce = CommerceDAO.getInstance();

       //vueCommerces = (ListView) findViewById(R.id.vue_liste_commerce);

        //listeDetailPourAdaptateur = accesseurCommerce.recupererListeCommercePourAdapteur();

        //SimpleAdapter adapteurVueDetailCommerce = new SimpleAdapter(this,
        //        listeDetailPourAdaptateur,
        //        android.R.layout.two_line_list_item,
        //        new int[]{android.R.id.text1, android.R.id.text2});

        //adapteurVueDetailCommerce.setAdapter(adapteurVueDetailCommerce);
    }
}

