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

    private static BaseDeDonneesServeur instance = null;

    public static BaseDeDonneesServeur getInstance() {
        if (instance == null) {
            instance = new BaseDeDonneesServeur();
        }
        return instance;
    }

    public BaseDeDonneesServeur() {

    }

    public String recupererXML(String page) throws IOException {
        URL url = new URL(SERVEUR_URL + page + ".php");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoInput(true);



        int responseCode = urlConnection.getResponseCode();
        System.out.println("Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // connection ok
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            urlConnection.disconnect();
            return response.toString();
        } else {
            urlConnection.disconnect();
            return "";
        }
    }



}
