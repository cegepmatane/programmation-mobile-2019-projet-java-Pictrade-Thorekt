package ca.qc.cgmatane.pictrade.donnee;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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

    public String recupererXML(String page) throws IOException {
        URL url = new URL(serveurUrl + page + ".php");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoInput(true);

        urlConnection.disconnect();

        int responseCode = urlConnection.getResponseCode();
        System.out.println("Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // connection ok
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            return "";

        }

    }

}
