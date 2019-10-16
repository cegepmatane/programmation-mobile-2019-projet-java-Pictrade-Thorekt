package ca.qc.cgmatane.pictrade.donnee;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.pictrade.modele.Commerce;

public class CommerceHandlerXML extends DefaultHandler {


    private List<Commerce> listeCommerce = null;
    private StringBuilder donnee = null;
    private Commerce commerce = null;

    boolean isPlaceID;
    boolean isNom;
    boolean isLongitude;
    boolean isLatitude;
    boolean isHoraireOuverture;
    boolean isHoraireFermeture;
    boolean isAdresse;
    boolean isContact;

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

        if (qName.equalsIgnoreCase("commerce")) {
            // create a new Employee and put it in Map
            String id = attributes.getValue("id");
            // initialize Employee object and set id attribute
            commerce = new Commerce();
            commerce.setId(Integer.parseInt(id));
            // initialize list
            if (listeCommerce == null)
                listeCommerce = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("placeID")) {
            isPlaceID = true;
        } else if (qName.equalsIgnoreCase("nom")) {
            isNom = true;
        } else if (qName.equalsIgnoreCase("longitude")) {
            isLongitude = true;
        } else if (qName.equalsIgnoreCase("latitude")) {
            isLatitude = true;
        } else if (qName.equalsIgnoreCase("horaire_ouverture")) {
            isHoraireOuverture = true;
        } else if (qName.equalsIgnoreCase("horaire_fermeture")) {
            isHoraireFermeture = true;
        } else if (qName.equalsIgnoreCase("adresse")) {
            isAdresse = true;
        } else if (qName.equalsIgnoreCase("contact")) {
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
      /*  } else if (isHoraireOuverture) {
            commerce.setHoraireOuverture(new Time(donnee.toString()));
            isHoraireOuverture = false;
        } else if (isHoraireFermeture) {
            commerce.setHoraireOuverture(new Time(donnee.toString()));
            isHoraireFermeture = false;*/
        } else if (isAdresse) {
            commerce.setAdresse(donnee.toString());
            isAdresse = false;
        } else if (isContact) {
            commerce.setContact(donnee.toString());
            isContact = false;
        }
        if (qName.equalsIgnoreCase("commerce")) {
            Log.d("endElement: ", commerce.toString());
            listeCommerce.add(commerce);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        donnee.append(new String(ch, start, length));
    }
}
