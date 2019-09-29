package ca.qc.cgmatane.pictrade.donnee;

import android.database.Cursor;
import android.os.Debug;
import android.util.Log;
import android.util.Xml;

import org.xml.sax.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import ca.qc.cgmatane.pictrade.modele.Commerce;

public class CommerceDAO {
    private static final String LISTER_COMMERCE = "lister_commerce";

    private static CommerceDAO instance = null;
    protected BaseDeDonneesServeur accesseurBaseDeDonneesServeur;

    protected List<Commerce> listeCommerces;

    public static CommerceDAO getInstance() {
        if (instance == null) {
            instance = new CommerceDAO();
        }
        return instance;
    }

    public CommerceDAO(){
        accesseurBaseDeDonneesServeur = BaseDeDonneesServeur.getInstance();
        listeCommerces = new ArrayList<>();
    }

    public List<Commerce> listerCommerce() {
        InputStream xml;
        try {
            xml = accesseurBaseDeDonneesServeur.recupererXML(LISTER_COMMERCE);
        } catch (IOException e) {
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
