package ca.qc.cgmatane.pictrade.donnee;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.pictrade.modele.Commerce;

public class FavoriDAO {

    private static FavoriDAO instance;
    private BaseDeDonneesClient accesseurBaseDeDonneesClient;

    protected ArrayList<Commerce> listeFavori;

    public static FavoriDAO getInstance() {
        if (instance == null) {
            instance = new FavoriDAO();
        }
        return instance;
    }

    private FavoriDAO(){
        accesseurBaseDeDonneesClient = BaseDeDonneesClient.getInstance();
        listeFavori = new ArrayList<>();
    }

    public List<Commerce> listerFavori(){
        String LISTER_FAVORIS = "SELECT * FROM favori";
        Cursor curseur = accesseurBaseDeDonneesClient.getReadableDatabase().rawQuery(LISTER_FAVORIS,
                null);
        this.listeFavori.clear();

        int indexId_commerce = curseur.getColumnIndex("id_commerce");
        int indexIsFavori = curseur.getColumnIndex("isFavori");

        for(curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()){
            int id_commerce = curseur.getInt(indexId_commerce);
            boolean isFavori = (curseur.getInt(indexIsFavori) == 1);
            listeFavori.add(new Commerce(id_commerce));
        }

        return listeFavori;
    }

    public List<HashMap<String, String>> recupererListeFavoriPourAdapteur(){
        List<HashMap<String, String>> listeFavoriPourAdapteur = new ArrayList<>();

        listerFavori();

        for(Commerce fav : listeFavori){
            listeFavoriPourAdapteur.add(fav.obtenirCommercePourAdapteur());
        }
        return listeFavoriPourAdapteur;
    }

    public void ajouterFavori(Commerce favori){
        listerFavori();
        if(chercherFavoriParIdCommerce(favori.getId()) != null) {
            SQLiteDatabase db = accesseurBaseDeDonneesClient.getWritableDatabase();
            SQLiteStatement query = db.compileStatement("INSERT INTO favori(id_favori, id_commerce, isFavori) VALUES(null,?,?)");
            query.bindString(1, "" + favori.getId());
            query.bindString(2, "" + 1);
            query.execute();
        }
        else{
            SQLiteDatabase db = accesseurBaseDeDonneesClient.getWritableDatabase();
            SQLiteStatement query = db.compileStatement("UPDATE favori SET isFavori = ? where id_commerce = ?");
            query.bindString(1, String.valueOf(1));
            query.bindString(2, String.valueOf(favori.getId()));

            query.execute();
        }
    }

    public Commerce chercherFavoriParIdFavori(int id_favori){
        listerFavori();
        for(Commerce commerceRecherche : this.listeFavori){
            if(commerceRecherche.getId() == id_favori) return commerceRecherche;
        }
        return null;
    }

    public Commerce chercherFavoriParIdCommerce(int id_commerce){
        listerFavori();
        for(Commerce commerceRecherche : this.listerFavori()){
            if(commerceRecherche.getId() == id_commerce) return commerceRecherche;
        }
        return null;
    }

    public void retirerFavori(Commerce fav){
        SQLiteDatabase db = accesseurBaseDeDonneesClient.getWritableDatabase();
        SQLiteStatement query = db.compileStatement("UPDATE favori SET isFavori = ? where id_commerce = ?");
        query.bindString(1, String.valueOf(0));
        query.bindString(2, String.valueOf(fav.getId()));

        query.execute();
    }

    public ArrayList<Commerce> matchFavori(ArrayList<Commerce> listeCommerce){
        listerFavori();

        listeCommerce.retainAll(listeFavori);

        return listeCommerce;
    }
}
