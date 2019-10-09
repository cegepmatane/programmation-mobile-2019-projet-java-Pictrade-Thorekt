package ca.qc.cgmatane.pictrade.donnee;


import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class BaseDeDonneesServeur {

    private static final String SERVEUR_URL = "http://51.91.96.142/";
    private static final int DEMANDE_NON_EN_COURS = 0;
    private static final int DEMANDE_EN_COURS = 1;
    private static final int DEMANDE_ABOUTIE = 2;
    private static final int DEMANDE_ECHOUER = 3;
    private int progres;
    private int etatDemandeServeur;

    private static BaseDeDonneesServeur instance = null;

    public static BaseDeDonneesServeur getInstance() {
        if (instance == null) {
            instance = new BaseDeDonneesServeur();
        }
        return instance;
    }

    public BaseDeDonneesServeur() {
        etatDemandeServeur=DEMANDE_EN_COURS;
    }

    public String recupererXML(String page) throws IOException {
        URL url = new URL(SERVEUR_URL + page + ".php");
        etatDemandeServeur = DEMANDE_NON_EN_COURS;
        AsyncTask<URL, Integer, InputStream> getFromUrl = new GetFromUrl();
        getFromUrl.execute(url);

        while (etatDemandeServeur == DEMANDE_EN_COURS) {
            Log.d("progression ", progres+"%");
        }

        if (etatDemandeServeur == DEMANDE_ABOUTIE) { // connection ok
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(getFromUrl.get()));
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } else if(etatDemandeServeur == DEMANDE_ECHOUER){

            return "DEMANDE_ECHOUER";

        }else{
            return etatDemandeServeur+"";
        }

    }

    protected class GetFromUrl extends AsyncTask<URL, Integer, InputStream> {
        private HttpURLConnection urlConnection;
        private InputStream inputStream;
        private int responseCode;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            etatDemandeServeur = DEMANDE_EN_COURS;
        }

        @Override
        protected InputStream doInBackground(URL... urls) {
            publishProgress(0);
            try {
                urlConnection = (HttpURLConnection) urls[0].openConnection();
                publishProgress(25);
                urlConnection.setRequestMethod("GET");
                int responseCode = urlConnection.getResponseCode();
                publishProgress(50);
                urlConnection.setDoInput(true);
                publishProgress(75);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (responseCode == HttpURLConnection.HTTP_OK) {

                try {
                    inputStream = urlConnection.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                publishProgress(100);
                return inputStream;
            } else {
                publishProgress(100);
                return null;
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progres=values[0];
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            super.onPostExecute(inputStream);
            if (inputStream != null) {
                etatDemandeServeur = DEMANDE_ABOUTIE;
            } else {
                etatDemandeServeur = DEMANDE_ECHOUER;
            }
        }
    }


}
