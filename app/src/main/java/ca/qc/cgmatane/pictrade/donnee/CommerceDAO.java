package ca.qc.cgmatane.pictrade.donnee;

import android.util.Log;

import org.xml.sax.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ca.qc.cgmatane.pictrade.Trieurs.TriParNom;
import ca.qc.cgmatane.pictrade.modele.Commerce;

public class CommerceDAO implements Dictionnaire {


    private static CommerceDAO instance = null;
    private BaseDeDonneesServeur accesseurBaseDeDonneesServeur;

    protected List<Commerce> listeCommerces;

    private CommerceHandlerXML commerceHandlerXML;

    public static CommerceDAO getInstance() {
        if (instance == null) {
            instance = new CommerceDAO();
        }
        return instance;
    }

    private CommerceDAO(){
        accesseurBaseDeDonneesServeur = BaseDeDonneesServeur.getInstance();
        listeCommerces = new ArrayList<>();
        commerceHandlerXML = new CommerceHandlerXML();
    }

    public Commerce recupererCommerce( HashMap<String,String> parametresPost ){
        Log.d("DEBUG", "in");
        Log.d("DEBUG", "parametresPost: "+parametresPost.toString());
        Commerce commerce= new Commerce();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            String xml = accesseurBaseDeDonneesServeur.recupererXML(PAGE_RECUPERER_COMMERCE,parametresPost);
            Log.d("DEBUG", "xml: "+xml);
            saxParser.parse(new InputSource(new StringReader(xml)), commerceHandlerXML);
            commerce = commerceHandlerXML.getCommerce();

        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG", "commerce: "+commerce.toString());
        return commerce;
    }

    public String modifierCommerce( HashMap<String,String> parametresPost ){
        Log.d("DEBUG", "in");
        Log.d("DEBUG", "parametresPost: "+parametresPost.toString());
        String resultat = null;
        try {
            resultat = accesseurBaseDeDonneesServeur.recupererXML(PAGE_MODIFIER_COMMERCE,parametresPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG", resultat);
        return resultat;
    }

    public List<Commerce> listerCommerce() {

        Log.d("DEBUG", "in");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            String xml = accesseurBaseDeDonneesServeur.recupererXML(PAGE_LISTER_COMMERCE);
            Log.d("DEBUG", "xml: "+xml);
            saxParser.parse(new InputSource(new StringReader(xml)), commerceHandlerXML);
            listeCommerces=commerceHandlerXML.getListeCommerce();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        Collections.sort(listeCommerces, new TriParNom());
        Log.d("DEBUG", "listeCommerces: "+listeCommerces.toString());
        return listeCommerces;
    }

    public List<HashMap<String, String>> recupererListeCommercePourAdapteur() {
        listeCommerces.clear();
        List<HashMap<String, String>> listeCommercePourAdapteur = new ArrayList<HashMap<String, String>>();

        listerCommerce();

        for (Commerce commerce : listeCommerces) {
            listeCommercePourAdapteur.add(commerce.obtenirCommercePourAdapteur());
        }

        return listeCommercePourAdapteur;
    }

    public Commerce chercherCommerceParId(int id_commerce){
        if(listeCommerces.contains(new Commerce(id_commerce))){
            return listeCommerces.get(listeCommerces.indexOf(new Commerce((id_commerce))));
        }
        else{
            return null;
        }
    }




    public List<Commerce> getListeCommerces() {
        return listeCommerces;
    }


}
