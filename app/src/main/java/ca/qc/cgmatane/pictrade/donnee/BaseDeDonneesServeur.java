package ca.qc.cgmatane.pictrade.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.util.Xml;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseDeDonneesServeur {

    private String serveurUrl;

    private static BaseDeDonneesServeur instance = null;

    public static BaseDeDonneesServeur getInstance() {
        if (instance == null) {
            instance = new BaseDeDonneesServeur();
        }
        return instance;
    }

    public BaseDeDonneesServeur() {
        this.serveurUrl = "http://51.91.96.142/";
    }

    public InputStream recupererXML(String page) throws IOException {
        InputStream in = null;
        URL url = new URL(serveurUrl + page + ".php");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        Log.d("http debug  ",urlConnection.getHeaderFields().toString());


        in = new BufferedInputStream(urlConnection.getInputStream());
        urlConnection.disconnect();



        return (in);

    }

}
