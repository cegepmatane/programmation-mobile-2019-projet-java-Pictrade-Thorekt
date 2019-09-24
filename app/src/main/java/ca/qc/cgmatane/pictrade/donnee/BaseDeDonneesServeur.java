package ca.qc.cgmatane.pictrade.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonneesServeur extends SQLiteOpenHelper {

    private static BaseDeDonneesServeur instance = null;

    public static BaseDeDonneesServeur getInstance(Context contexte) {
        instance = new BaseDeDonneesServeur(contexte);
        return instance;
    }

    public static BaseDeDonneesServeur getInstance() {
        return instance;
    }

    /*public BaseDeDonneesServeur(Context contexte) {

    }

    public BaseDeDonneesServeur(Context contexte, String name, SQLiteDatabase.CursorFactory factory, int version) {

    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

    }

}
