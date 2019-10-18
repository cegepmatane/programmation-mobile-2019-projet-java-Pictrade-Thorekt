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

            int id_favori = curseur.getInt(indexId_favori);
            int id_commerce = curseur.getInt(indexId_commerce);
            boolean isFavori = curseur.getInt(indexIsFavori) == 1;

            listeFavori.add(new Favori(id_favori, id_commerce, isFavori));
        }
        return listeFavori;
    }

    public boolean isFavoriByIdCommerce(int idCommerce){
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

    public boolean ajouterFavori(Favori fav){
        if(isFavoriByIdCommerce(fav.getId_commerce())){

        }


        return false;
    }
    //ajouter favori
    //retirer
}
