package ca.qc.cgmatane.pictrade.donnee;

import android.database.Cursor;

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

            int id_favori = curseur.getInt(indexIsFavori);
            int id_commerce = curseur.getInt(indexId_commerce);
            boolean isFavori = curseur.getInt(indexIsFavori) == 1;

            listeFavori.add(new Favori(id_favori, id_commerce, isFavori));
        }
        return listeFavori;
    }

    //lister favoris
    //is favori by id commerce
    //isInDataBase (id commerce)
    //ajouter favori
    //retirer
}
