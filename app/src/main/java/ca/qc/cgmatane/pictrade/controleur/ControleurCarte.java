package ca.qc.cgmatane.pictrade.controleur;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.google.android.gms.maps.model.PointOfInterest;

import ca.qc.cgmatane.pictrade.vue.VueCarte;

public class ControleurCarte implements Controleur {

    private VueCarte vue;
    private Intent intentionNaviguerRecherche;

    public ControleurCarte(VueCarte vue) {

        this.vue = vue;
    }

    @Override
    public void onCreate(Context applicationContext) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onActivityResult(int activite) {

    }

//    public void actionNaviguerAfficherRecherche(View vue) {
//        vue.naviguerRecherche(vue);
//    }

    public void actionNaviguerAfficherCommerce(PointOfInterest pointDInteret) {
        vue.naviguerAfficherCommerce(pointDInteret);
    }
}
