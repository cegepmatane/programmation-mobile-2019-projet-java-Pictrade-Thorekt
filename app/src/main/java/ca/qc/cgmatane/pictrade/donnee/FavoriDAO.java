package ca.qc.cgmatane.pictrade.donnee;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import ca.qc.cgmatane.pictrade.modele.Favori;

public class FavoriDAO {
    private static FavoriDAO instance;
    private BaseDeDonneesClient accesseurBaseDeDonneesClient;
    private ArrayList<Favori> listeFavori;

    public static FavoriDAO getInstance(){
        if(instance == null){
            instance = new FavoriDAO();
        }
        return instance;
    }

    public FavoriDAO(){
        accesseurBaseDeDonneesClient = BaseDeDonneesClient.getInstance();
        listeFavori = new ArrayList<>();
    }

    public ArrayList<Favori> listerFavori(){
        String LISTER_JEUX = "SELECT * FROM jeu";
        Cursor curseur = accesseurBaseDeDonneesClient.getReadableDatabase().rawQuery(LISTER_JEUX,
                null);
        this.listeFavori.clear();

        int indexId_favori = curseur.getColumnIndex("id_favori");
        int indexId_commerce = curseur.getColumnIndex("id_commerce");
        int indexIsFavori = curseur.getColumnIndex("isFavori");

        for(curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()){

            int id_favori = curseur.getInt(indexId_favori);
            int id_commerce = curseur.getInt(indexId_commerce);
            boolean isFavori = curseur.getInt(indexIsFavori) == 1;

            listeFavori.add(new Favori(id_favori, id_commerce, isFavori));
        }
        return listeFavori;
    }

    public boolean isFavoriByIdCommerce(int idCommerce){
        listerFavori();
        if(isInDb(idCommerce)){
            for (int i = 0; i < listeFavori.size(); i++) {
                if(listeFavori.get(i).getId_commerce() == idCommerce){
                    return listeFavori.get(i).isFavori();
                }
            }
        }
        return false;
    }
    public boolean isInDb(int idCommerce){
        return listeFavori.contains(new Favori(idCommerce));
    }

    public void ajouterFavori(Favori fav){
        if(isInDb(fav.getId_commerce()) && !isFavoriByIdCommerce(fav.getId_commerce())){
            SQLiteDatabase db = accesseurBaseDeDonneesClient.getWritableDatabase();
            SQLiteStatement query = db.compileStatement("UPDATE favori SET isFavori = ? where id_commerce = ?");

            int bool = 0;
            if(fav.isFavori()){
                bool = 1;
            }

            query.bindString(1, String.valueOf(bool));
            query.bindString(2, String.valueOf(fav.getId_commerce()));

            query.execute();
        }
        else if(!isInDb(fav.getId_commerce())){
            SQLiteDatabase db = accesseurBaseDeDonneesClient.getWritableDatabase();

            SQLiteStatement query = db.compileStatement("INSERT INTO favori(id_favori, id_commerce, isFavori) VALUES(null,?,?)");

            int bool = 0;
            if(fav.isFavori()){
                bool = 1;
            }

            query.bindString(1, String.valueOf(fav.getId_commerce()));
            query.bindString(2, String.valueOf(bool));

            query.execute();
        }
    }



    //retirer
}
