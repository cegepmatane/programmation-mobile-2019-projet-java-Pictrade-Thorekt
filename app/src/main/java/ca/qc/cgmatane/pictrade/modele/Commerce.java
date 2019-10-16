package ca.qc.cgmatane.pictrade.modele;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class Commerce {
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
        return "Commerce{" +
                "id=" + id +
                ", placeID='" + placeID + '\'' +
                ", nom='" + nom + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", horaireOuverture=" + horaireOuverture +
                ", horaireFermeture=" + horaireFermeture +
                ", adresse='" + adresse + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    public HashMap<String, String> obtenirCommercePourAdapteur() {
        HashMap<String, String> commercePourAdapteur = new HashMap<String, String>();
        commercePourAdapteur.put("id", "" + this.id);
        commercePourAdapteur.put("nom", this.nom);
        commercePourAdapteur.put("adresse", this.adresse);
        return commercePourAdapteur;
    }

    public HashMap<String, String> obtenirCommerceHashMap() {
        HashMap<String, String> commerceHashMap = new HashMap<String, String>();
        commerceHashMap.put("id", "" + this.id);
        commerceHashMap.put("nom", this.nom);
        commerceHashMap.put("placeID", this.placeID);
        commerceHashMap.put("longitude", this.longitude + "");
        commerceHashMap.put("latitude", this.latitude + "");
        if (horaireOuverture != null) {
            commerceHashMap.put("horaireOuverture", this.horaireOuverture.toString());
        }else{
            commerceHashMap.put("horaireOuverture", "");
        }
        if (horaireFermeture != null) {
            commerceHashMap.put("horaireFermeture", this.horaireFermeture.toString());
        }else{
            commerceHashMap.put("horaireFermeture", "");
        }
        commerceHashMap.put("adresse", this.adresse);
        commerceHashMap.put("contact", this.contact);

        return commerceHashMap;
    }

    public static Time formaterTemps(String string){
        DateFormat formatTemps = new SimpleDateFormat("hh:mm a");
        Time temps;
        Date date = null;
        try {
            date = formatTemps.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        temps = new Time(date != null ? date.getTime() : 0);
        return temps;
    }

}