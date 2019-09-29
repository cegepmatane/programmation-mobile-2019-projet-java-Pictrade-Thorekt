package ca.qc.cgmatane.pictrade.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseDeDonneesServeur {

    private static BaseDeDonneesServeur instance = null;

    public static BaseDeDonneesServeur getInstance() {
        if (instance != null) {
            instance = new BaseDeDonneesServeur();
        }
        return instance;
    }

    public InputStream recupererXML(String page) {

        URL url = null;
        try {
            url = new URL("http://51.91.96.142/"+page+".php");
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = null;
                try {
                    in = new BufferedInputStream(urlConnection.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                    return (in);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
