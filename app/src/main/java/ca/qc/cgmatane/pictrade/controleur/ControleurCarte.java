package ca.qc.cgmatane.pictrade.controleur;

import android.content.Context;

import com.google.android.gms.maps.model.PointOfInterest;

import ca.qc.cgmatane.pictrade.donnee.BaseDeDonneesClient;
import ca.qc.cgmatane.pictrade.vue.VueCarte;

public class ControleurCarte implements Controleur {

    private final VueCarte vue;

    public ControleurCarte(VueCarte vue) {
        this.vue = vue;
    }

    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonneesClient.getInstance(applicationContext);
        actionPermissionGeolocalisation();

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

    public void actionNaviguerMenuRechercheCommerce() {
        vue.naviguerRecherche();
    }

    public void actionNaviguerAfficherCommerce(PointOfInterest pointDInteret) {
        vue.naviguerAfficherCommerce(pointDInteret);
    }
    private void actionPermissionGeolocalisation(){

        vue.permissionLocalisation();


    }

    public void actionAfficherCarte(){
        vue.afficherCarte();
    }
}