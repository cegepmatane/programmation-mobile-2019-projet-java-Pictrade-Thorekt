package ca.qc.cgmatane.pictrade.controleur;

import android.content.Context;
import android.os.AsyncTask;

import ca.qc.cgmatane.pictrade.donnee.CommerceDAO;
import ca.qc.cgmatane.pictrade.modele.Commerce;
import ca.qc.cgmatane.pictrade.vue.VueAfficherCommerce;

public class ControleurAfficherCommerce implements Controleur {
    private VueAfficherCommerce vue;
    private CommerceDAO accesseurCommerce;
    private Commerce commerce;

    public ControleurAfficherCommerce(VueAfficherCommerce vue) {
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



    private class recupererCommerce extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
