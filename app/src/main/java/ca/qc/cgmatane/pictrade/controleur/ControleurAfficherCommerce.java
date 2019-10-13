package ca.qc.cgmatane.pictrade.controleur;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.model.PointOfInterest;

import java.util.HashMap;

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
        Bundle parametres = vue.getParametres();
        HashMap<String,String> parametresPost = new HashMap<>();

        PointOfInterest pointDInteret = (PointOfInterest) parametres.get("pointDInteret");
        if (pointDInteret != null){
            parametresPost.put("placeID",pointDInteret.placeId);
            parametresPost.put("nom",pointDInteret.name);
            parametresPost.put("longitude",pointDInteret.latLng.longitude+"");
            parametresPost.put("latitude",pointDInteret.latLng.longitude+"");
        }else{
            int id = (Integer) parametres.get("id");
            parametresPost.put("id",id+"");
        }
        AsyncTask<HashMap<String,String>,String,String>  recupererCommerce= new RecupererCommerce();
        recupererCommerce.execute(parametresPost);


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



    private class RecupererCommerce extends AsyncTask<HashMap<String,String>,String,String> {
        Commerce commerceRecuperer;

        @Override
        protected String doInBackground(HashMap<String, String>... hashMaps) {
            commerceRecuperer =  accesseurCommerce.recupererCommerce(hashMaps[0]);
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
            commerce = commerceRecuperer;
            Log.d("onPostExecute: ", commerce.toString());
            super.onPostExecute(s);
        }
    }
}
