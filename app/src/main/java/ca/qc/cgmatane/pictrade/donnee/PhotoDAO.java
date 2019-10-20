package ca.qc.cgmatane.pictrade.donnee;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ca.qc.cgmatane.pictrade.modele.Photo;

public class PhotoDAO implements Dictionnaire {

    private static PhotoDAO instance = null;
    private BaseDeDonneesServeur accesseurBaseDeDonneesServeur;

    private PhotoHandlerXML photoHandlerXML;

    public static PhotoDAO getInstance() {
        if (instance == null) {
            instance = new PhotoDAO();
        }
        return instance;
    }

    private PhotoDAO() {
        accesseurBaseDeDonneesServeur = BaseDeDonneesServeur.getInstance();
        photoHandlerXML = new PhotoHandlerXML();
    }

    public List<Photo> listerPhotoParIdCommerce(HashMap<String, String> parametresPost) {
        List<Photo> listePhoto = new ArrayList<>();

        Log.d("DEBUG", "in");
        Log.d("DEBUG", "parametresPost :"+ parametresPost );
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            String xml = accesseurBaseDeDonneesServeur.recupererXML(PAGE_LISTER_PHOTO, parametresPost);
            if(xml != null){

                Log.d("DEBUG", "xml: " + xml);
                saxParser.parse(new InputSource(new StringReader(xml)), photoHandlerXML);
                listePhoto = photoHandlerXML.getListePhoto();
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        //Log.d("DEBUG", "listeCommerces: " + listePhoto.toString());
        return listePhoto;
    }

    public String ajouterCommerce(HashMap<String, String> parametresPost) {
        Log.d("DEBUG", "in");
        Log.d("DEBUG", "parametresPost: " + parametresPost.toString());
        String resultat = null;
        try {
            resultat = accesseurBaseDeDonneesServeur.recupererXML(PAGE_AJOUTER_PHOTO, parametresPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG", resultat);
        return resultat;
    }
}
