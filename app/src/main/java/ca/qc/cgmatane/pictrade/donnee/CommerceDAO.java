package ca.qc.cgmatane.pictrade.donnee;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.pictrade.modele.Commerce;

public class CommerceDAO {
    private static CommerceDAO instance = null;
    protected BaseDeDonnees accesseurBaseDeDonnees;

    protected List<Commerce> listeCommerces;

    public static CommerceDAO getInstance() {
        if (instance == null) {
            instance = new CommerceDAO();
        }
        return instance;
    }

    public CommerceDAO(){
        accesseurBaseDeDonnees = BaseDeDonnees.getInstance();
        listeCommerces = new ArrayList<>();
    }

    public List<Commerce> ListerCommerce(){
        String LISTER_COMMERCE = "SELECT id, nom, longitude, latitude, horaire, adresse, contact FROM commerce";
        Cursor curseur = accesseurBaseDeDonnees.getReadableDatabase().rawQuery(LISTER_COMMERCE,
                null);
        this.listeCommerces.clear();

        int indexId = curseur.getColumnIndex("id");
        int indexNom = curseur.getColumnIndex("nom");
        int indexLongitude = curseur.getColumnIndex("longitude");
        int indexLatitude = curseur.getColumnIndex("latitude");
        int indexHoraire = curseur.getColumnIndex("horaire");
        int indexAdresse = curseur.getColumnIndex("adresse");
        int indexContact = curseur.getColumnIndex("contact");

        for(curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()){
            int id = curseur.getInt(indexId);
            String nom = curseur.getString(indexNom);
            float longitude = curseur.getFloat(indexLongitude);
            float latitude = curseur.getFloat(indexLatitude);
            String horaire = curseur.getString(indexHoraire);
            String adresse = curseur.getString(indexAdresse);
            String contact = curseur.getString(indexContact);

            listeCommerces.add(new Commerce(id, nom, longitude, latitude, horaire, adresse, contact));
        }
        return listeCommerces;
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
