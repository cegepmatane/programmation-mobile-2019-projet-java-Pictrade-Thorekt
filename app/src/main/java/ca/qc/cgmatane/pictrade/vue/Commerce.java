package ca.qc.cgmatane.pictrade.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.donnee.CommerceDAO;

public class Commerce extends AppCompatActivity implements VueCommerce {
    protected CommerceDAO accesseurCommerce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_commerce);
    }

    protected void informationCommerce() {
        accesseurCommerce = CommerceDAO.getInstance();

        //vueListeCommerces = (ListView) findViewById(R.id.vue_detail_commerce)
    }
}

