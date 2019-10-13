package ca.qc.cgmatane.pictrade.donnee;

import android.database.Cursor;
import android.os.Debug;
import android.util.Log;
import android.util.Xml;

import com.google.android.gms.maps.model.PointOfInterest;

import org.xml.sax.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ca.qc.cgmatane.pictrade.modele.Commerce;

public class CommerceDAO {
    private static final String LISTER_COMMERCE = "lister_commerce";

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

    public Commerce recupererCommerce(int id){
        return null;
    }

    public Commerce recupererCommerce(PointOfInterest pointDInteret){
        return null;
    }

    public List<Commerce> listerCommerce() { //a faire dans une asynctask
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            String xml = accesseurBaseDeDonneesServeur.recupererXML(LISTER_COMMERCE);
            saxParser.parse(new InputSource(new StringReader(xml)), commerceHandlerXML);
            listeCommerces=commerceHandlerXML.getListeCommerce();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return listeCommerces;
    }

    public List<HashMap<String, String>> recupererListeCommercePourAdapteur() {
        List<HashMap<String, String>> listeFilmPourAdapteur = new ArrayList<HashMap<String, String>>();

        listerCommerce();

        for (Commerce commerce : listeCommerces) {
            listeFilmPourAdapteur.add(commerce.obtenirCommercePourAdapteur());
        }

        return listeFilmPourAdapteur;
    }

    public Commerce chercherCommerceParId(int id_commerce){
        if(listeCommerces.contains(new Commerce(id_commerce))){
            return listeCommerces.get(listeCommerces.indexOf(new Commerce((id_commerce))));
        }
        else{
            return null;
        }
    }


}
