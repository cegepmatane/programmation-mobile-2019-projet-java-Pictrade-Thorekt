package ca.qc.cgmatane.pictrade.donnee;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.pictrade.modele.Commerce;

public class CommerceHandlerXML extends DefaultHandler implements Dictionnaire{


    private List<Commerce> listeCommerce = null;
    private StringBuilder donnee = null;
    private Commerce commerce = null;



    private boolean isPlaceID;
    private boolean isNom;
    private boolean isLongitude;
    private boolean isLatitude;
    private boolean isHoraireOuverture;
    private boolean isHoraireFermeture;
    private boolean isAdresse;
    private boolean isContact;

    public CommerceHandlerXML() {
        super();
    }


    public Commerce getCommerce() {
        return commerce;
    }

    public List<Commerce> getListeCommerce() {
        return listeCommerce;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase(CLE_COMMERCE)) {
            // create a new Employee and put it in Map
            String id = attributes.getValue(CLE_ID_COMMERCE);
            // initialize Employee object and set id attribute
            commerce = new Commerce();
            commerce.setId(Integer.parseInt(id));
            // initialize list
            if (listeCommerce == null)
                listeCommerce = new ArrayList<>();
        } else if (qName.equalsIgnoreCase(CLE_PLACEID_COMMERCE)) {
            isPlaceID = true;
        } else if (qName.equalsIgnoreCase(CLE_NOM_COMMERCE)) {
            isNom = true;
        } else if (qName.equalsIgnoreCase(CLE_LONGITUDE_COMMERCE)) {
            isLongitude = true;
        } else if (qName.equalsIgnoreCase(CLE_LATITUDE_COMMERCE)) {
            isLatitude = true;
        } else if (qName.equalsIgnoreCase(CLE_HORAIRE_OUVERTURE_COMMERCE)) {
            isHoraireOuverture = true;
        } else if (qName.equalsIgnoreCase(CLE_HORAIRE_FERMETURE_COMMERCE)) {
            isHoraireFermeture = true;
        } else if (qName.equalsIgnoreCase(CLE_ADRESSE_COMMERCE)) {
            isAdresse = true;
        } else if (qName.equalsIgnoreCase(CLE_CONTACT_COMMERCE)) {
            isContact = true;
        }

        donnee = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (isPlaceID) {
            commerce.setPlaceID(donnee.toString());
            isPlaceID = false;
        } else if (isNom) {
            commerce.setNom(donnee.toString());
            isNom = false;
        } else if (isLongitude) {
            commerce.setLongitude(Float.valueOf(donnee.toString()));
            isLongitude = false;
        } else if (isLatitude) {
            commerce.setLatitude(Float.valueOf(donnee.toString()));
            isLatitude = false;
        } else if (isHoraireOuverture) {
            commerce.setHoraireOuverture(Commerce.formaterTemps(donnee.toString()));
            isHoraireOuverture = false;
        } else if (isHoraireFermeture) {
            commerce.setHoraireOuverture(Commerce.formaterTemps(donnee.toString()));
            isHoraireFermeture = false;
        } else if (isAdresse) {
            commerce.setAdresse(donnee.toString());
            isAdresse = false;
        } else if (isContact) {
            commerce.setContact(donnee.toString());
            isContact = false;
        }
        if (qName.equalsIgnoreCase(CLE_COMMERCE)) {
            Log.d("endElement: ", commerce.toString());
            listeCommerce.add(commerce);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        donnee.append(new String(ch, start, length));
    }
}
