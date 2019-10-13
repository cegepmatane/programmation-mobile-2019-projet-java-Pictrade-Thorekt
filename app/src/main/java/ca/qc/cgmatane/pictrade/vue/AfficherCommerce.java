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

    private Bundle parametres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_afficher_commerce);

        parametres = this.getIntent().getExtras();

        controleurAfficherCommerce.onCreate(getApplicationContext());
    }


    @Override
    public Bundle getParametres() {
        return parametres;
    }
}

