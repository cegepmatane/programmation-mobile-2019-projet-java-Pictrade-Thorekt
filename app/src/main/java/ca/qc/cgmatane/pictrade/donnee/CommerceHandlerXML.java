package ca.qc.cgmatane.pictrade.donnee;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.pictrade.modele.Commerce;

public class CommerceHandlerXML extends DefaultHandler {


    private List<Commerce> listeCommerce = null;
    private StringBuilder donnee = null;
    private Commerce commerce = null;

    boolean aNom;
    boolean aLongitude;
    boolean aLatitude;
    boolean aHoraire;
    boolean aAdresse;
    boolean AContact;

    public CommerceHandlerXML() {
        super();
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
        } else if (qName.equalsIgnoreCase("nom")) {
            aNom = true;
        } else if (qName.equalsIgnoreCase("longitude")) {
            aLongitude = true;
        } else if (qName.equalsIgnoreCase("latitude")) {
            aLatitude = true;
        } else if (qName.equalsIgnoreCase("horaire")) {
            aHoraire = true;
        } else if (qName.equalsIgnoreCase("adresse")) {
            aAdresse = true;
        } else if (qName.equalsIgnoreCase("contact")) {
            AContact = true;
        }

        donnee = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (aNom) {
            commerce.setNom(donnee.toString());
            aNom = false;
        } else if (aLongitude) {
            commerce.setLongitude(Float.valueOf(donnee.toString()));
            aLongitude = false;
        } else if (aLatitude) {
            commerce.setLatitude(Float.valueOf(donnee.toString()));
            aLatitude = false;
        } else if (aHoraire) {
            commerce.setHoraire(donnee.toString());
            aHoraire = false;
        } else if (aAdresse) {
            commerce.setAdresse(donnee.toString());
            aAdresse = false;
        } else if (AContact) {
            commerce.setContact(donnee.toString());
            AContact = false;
        }
        if (qName.equalsIgnoreCase("commerce")) {
            listeCommerce.add(commerce);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        donnee.append(new String(ch, start, length));
    }
}
