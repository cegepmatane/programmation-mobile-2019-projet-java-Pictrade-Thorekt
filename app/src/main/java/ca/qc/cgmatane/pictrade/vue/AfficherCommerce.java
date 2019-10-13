package ca.qc.cgmatane.pictrade.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.model.PointOfInterest;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.controleur.ControleurAfficherCommerce;
import ca.qc.cgmatane.pictrade.donnee.CommerceDAO;

public class AfficherCommerce extends AppCompatActivity implements VueAfficherCommerce {
    private AfficherCommerce commerce;
    private TextView vueAfficherNomCommerce;
    private ControleurAfficherCommerce controleurAfficherCommerce
            = new ControleurAfficherCommerce(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_afficher_commerce);

        Bundle parametres = this.getIntent().getExtras();
        PointOfInterest poi = (PointOfInterest) parametres.get("poi");


        vueAfficherNomCommerce= (TextView) findViewById(R.id.vue_afficher_nom_commerce);


        controleurAfficherCommerce.onCreate(getApplicationContext());
    }
}

