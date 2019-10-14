package ca.qc.cgmatane.pictrade.controleur;

import android.content.Context;

import ca.qc.cgmatane.pictrade.donnee.CommerceDAO;
import ca.qc.cgmatane.pictrade.modele.Commerce;
import ca.qc.cgmatane.pictrade.vue.VueModifierCommerce;

public class ControleurModifierCommerce implements Controleur {
    private VueModifierCommerce vue;
    private CommerceDAO accesseurCommerce;
    private Commerce commerce;

    public ControleurModifierCommerce(VueModifierCommerce vue) {
        this.vue = vue;
        this.accesseurCommerce= CommerceDAO.getInstance();
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
}
