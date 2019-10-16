package ca.qc.cgmatane.pictrade.controleur;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

import ca.qc.cgmatane.pictrade.donnee.CommerceDAO;
import ca.qc.cgmatane.pictrade.modele.Commerce;
import ca.qc.cgmatane.pictrade.vue.VueModifierCommerce;

public class ControleurModifierCommerce implements Controleur {
    private VueModifierCommerce vue;
    private CommerceDAO accesseurCommerce;

    public ControleurModifierCommerce(VueModifierCommerce vue) {
        this.vue = vue;
        this.accesseurCommerce= CommerceDAO.getInstance();
    }

    @Override
    public void onCreate(Context applicationContext) {
        vue.preRemplirChamps();
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

    public void validerModification() {
        HashMap<String,String> commerce = vue.getCommerceHashMap();
        AsyncTask<HashMap<String,String>,String,String> modifierCommerce = new ModifierCommerce();
        modifierCommerce.execute(commerce);
    }

    public void annulerModification() {
        vue.naviguerCommerce();
    }

    private class ModifierCommerce extends AsyncTask<HashMap<String,String>,String,String>{

        @Override
        protected String doInBackground(HashMap<String, String>... hashMaps) {
            String resultat = accesseurCommerce.modifierCommerce(hashMaps[0]);
            return resultat;
        }

        @Override
        protected void onPostExecute(String resultat) {
            super.onPostExecute(resultat);
            Log.d("onPostExecute: ", resultat);
            vue.naviguerCommerce();
        }
    }
}
