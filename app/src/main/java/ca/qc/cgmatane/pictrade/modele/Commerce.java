package ca.qc.cgmatane.pictrade.modele;

import java.util.HashMap;
import java.util.Objects;

public class Commerce {
    public int id;
    public String nom;
    public float longitude;
    public float latitude;
    public String horaire;
    public String adresse;
    public String contact;

    public Commerce() {
    }

    public Commerce(int id, String nom, float longitude, float latitude, String horaire, String adresse, String contact) {
        this.id = id;
        this.nom = nom;
        this.longitude = longitude;
        this.latitude = latitude;
        this.horaire = horaire;
        this.adresse = adresse;
        this.contact = contact;
    }

    public Commerce(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
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
                ", nom='" + nom + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", horaire='" + horaire + '\'' +
                ", adresse='" + adresse + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    public HashMap<String, String> obtenirCommercePourAdapteur(){
        HashMap<String, String> commercePourAdapteur = new HashMap<String, String>();
        commercePourAdapteur.put("nom", this.nom);
        commercePourAdapteur.put("adresse", this.adresse);
        commercePourAdapteur.put("id", "" + this.id);
        return commercePourAdapteur;
    }
}