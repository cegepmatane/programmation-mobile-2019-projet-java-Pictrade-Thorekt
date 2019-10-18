package ca.qc.cgmatane.pictrade.donnee;

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




        return listeFavori;
    }
}
