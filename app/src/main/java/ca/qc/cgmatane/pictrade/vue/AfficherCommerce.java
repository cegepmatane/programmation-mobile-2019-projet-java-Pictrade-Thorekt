package ca.qc.cgmatane.pictrade.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.controleur.ControleurAfficherCommerce;
import ca.qc.cgmatane.pictrade.modele.Commerce;

public class AfficherCommerce extends AppCompatActivity implements VueAfficherCommerce {
    private Commerce commerce;
    private ControleurAfficherCommerce controleurAfficherCommerce
            = new ControleurAfficherCommerce(this);

    private Bundle parametres;

    private ProgressBar vueAfficherCommerceEnAttente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_afficher_commerce);

        parametres = this.getIntent().getExtras();
        vueAfficherCommerceEnAttente =
                (ProgressBar) findViewById(R.id.vue_afficher_commerce_en_attente);
        controleurAfficherCommerce.onCreate(getApplicationContext());
    }


    @Override
    public Bundle getParametres() {
        return parametres;
    }

    @Override
    public void setCommerce(Commerce commerce) {
        this.commerce = commerce;
    }


    @Override
    public void commerceEnAttente() {
        vueAfficherCommerceEnAttente.setVisibility(View.VISIBLE);
    }

    @Override
    public void afficherCommerce() {
        vueAfficherCommerceEnAttente.setVisibility(View.INVISIBLE);

        TextView vueAfficherNomCommerce = (TextView) findViewById(R.id.vue_afficher_nom_commerce);
        TextView vueAfficherContactCommerce =
                (TextView) findViewById(R.id.vue_afficher_contact_commerce);
        TextView vueAfficherAdresseCommerce =
                (TextView) findViewById(R.id.vue_afficher_adresse_commerce);
        TextView vueAfficherHoraireOuvertureCommerce =
                (TextView) findViewById(R.id.vue_afficher_horaire_ouverture_commerce);
        TextView vueAfficherHoraireFermetureCommerce =
                (TextView) findViewById(R.id.vue_afficher_horaire_fermeture_commerce);

        vueAfficherNomCommerce.setText(commerce.getNom());
        vueAfficherContactCommerce.setText(commerce.getContact());
        vueAfficherAdresseCommerce.setText(commerce.getAdresse());
        vueAfficherHoraireOuvertureCommerce.setText(commerce.getHoraire());
        vueAfficherHoraireFermetureCommerce.setText(commerce.getHoraire());
    }
}

