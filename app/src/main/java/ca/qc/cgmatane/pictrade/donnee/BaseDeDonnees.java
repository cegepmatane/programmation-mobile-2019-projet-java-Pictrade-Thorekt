package ca.qc.cgmatane.pictrade.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper {

    private static BaseDeDonnees instance = null;

    public static BaseDeDonnees getInstance(Context contexte) {
        instance = new BaseDeDonnees(contexte);
        return instance;
    }

    public static BaseDeDonnees getInstance() {
        return instance;
    }

    /*public BaseDeDonnees(Context contexte) {

    }

    public BaseDeDonnees(Context contexte, String name, SQLiteDatabase.CursorFactory factory, int version) {

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
