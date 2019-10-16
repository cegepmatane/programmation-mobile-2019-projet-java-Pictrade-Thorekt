package ca.qc.cgmatane.pictrade.modele;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import ca.qc.cgmatane.pictrade.donnee.Dictionnaire;

public class Commerce implements Dictionnaire {
    public int id;
    public String placeID;
    public String nom;
    public float longitude;
    public float latitude;
    public Time horaireOuverture;
    public Time horaireFermeture;
    public String adresse;
    public String contact;

    public Commerce() {
    }

    public Commerce(int id, String placeID, String nom, float longitude, float latitude, Time horaireOuverture, Time horaireFermeture, String adresse, String contact) {
        this.id = id;
        this.placeID = placeID;
        this.nom = nom;
        this.longitude = longitude;
        this.latitude = latitude;
        this.horaireOuverture = horaireOuverture;
        this.horaireFermeture = horaireFermeture;
        this.adresse = adresse;
        this.contact = contact;
    }

    public Commerce(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public Time getHoraireOuverture() {
        return horaireOuverture;
    }

    public void setHoraireOuverture(Time horaireOuverture) {
        this.horaireOuverture = horaireOuverture;
    }

    public Time getHoraireFermeture() {
        return horaireFermeture;
    }

    public void setHoraireFermeture(Time horaireFermeture) {
        this.horaireFermeture = horaireFermeture;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commerce commerce = (Commerce) o;
        return id == commerce.id;
    }

    @Override
    public String toString() {
        return CLE_COMMERCE+"{" +
                CLE_ID_COMMERCE+"=" + id +
                ", "+CLE_PLACEID_COMMERCE+"='" + placeID + '\'' +
                ", "+CLE_NOM_COMMERCE+"='" + nom + '\'' +
                ", "+CLE_LONGITUDE_COMMERCE+"='" + longitude +
                ", "+CLE_LATITUDE_COMMERCE+"='" + latitude +
                ", "+CLE_HORAIRE_OUVERTURE_COMMERCE+"='" + horaireOuverture +
                ", "+CLE_HORAIRE_FERMETURE_COMMERCE+"='" + horaireFermeture +
                ", "+CLE_ADRESSE_COMMERCE+"='" + adresse + '\'' +
                ", "+CLE_CONTACT_COMMERCE+"='" + contact + '\'' +
                '}';
    }

    public HashMap<String, String> obtenirCommercePourAdapteur() {
        HashMap<String, String> commercePourAdapteur = new HashMap<String, String>();
        commercePourAdapteur.put(CLE_ID_COMMERCE, "" + this.id);
        commercePourAdapteur.put(CLE_NOM_COMMERCE, this.nom);
        commercePourAdapteur.put(CLE_ADRESSE_COMMERCE, this.adresse);
        return commercePourAdapteur;
    }

    public HashMap<String, String> obtenirCommerceHashMap() {
        HashMap<String, String> commerceHashMap = new HashMap<String, String>();
        commerceHashMap.put(CLE_ID_COMMERCE, "" + this.id);
        commerceHashMap.put(CLE_NOM_COMMERCE, this.nom);
        commerceHashMap.put(CLE_PLACEID_COMMERCE, this.placeID);
        commerceHashMap.put(CLE_LONGITUDE_COMMERCE, this.longitude + "");
        commerceHashMap.put(CLE_LATITUDE_COMMERCE, this.latitude + "");
        if (horaireOuverture != null) {
            commerceHashMap.put(CLE_HORAIRE_OUVERTURE_COMMERCE, this.horaireOuverture.toString());
        }else{
            commerceHashMap.put(CLE_HORAIRE_OUVERTURE_COMMERCE, "");
        }
        if (horaireFermeture != null) {
            commerceHashMap.put(CLE_HORAIRE_FERMETURE_COMMERCE, this.horaireFermeture.toString());
        }else{
            commerceHashMap.put(CLE_HORAIRE_FERMETURE_COMMERCE, "");
        }
        commerceHashMap.put(CLE_ADRESSE_COMMERCE, this.adresse);
        commerceHashMap.put(CLE_CONTACT_COMMERCE, this.contact);

        return commerceHashMap;
    }
    public static Time formaterTemps(String tempsString){

        DateFormat formatTemps = new SimpleDateFormat("hh:mm:ss");
        Time temps;
        Date date = null;
        if(tempsString!=""){
            try {
                date = formatTemps.parse(tempsString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            try {
                date = formatTemps.parse("00:00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        temps = new Time(date.getTime());

        return temps;
    }

}