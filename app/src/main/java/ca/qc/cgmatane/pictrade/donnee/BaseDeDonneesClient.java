package ca.qc.cgmatane.pictrade.donnee;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonneesClient extends SQLiteOpenHelper {

    private static BaseDeDonneesClient instance = null;

    public static BaseDeDonneesClient getInstance(Context contexte) {
        instance = new BaseDeDonneesClient(contexte);
        return instance;
    }

    public static BaseDeDonneesClient getInstance() {
        return instance;
    }

    public BaseDeDonneesClient(Context contexte) {
        super(contexte, "favori", null, 1);
    }

    public BaseDeDonneesClient(Context contexte, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexte, name, factory , version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String CREATE_TABLE = "create table favori(id_favori INTEGER PRIMARY KEY, id_commerce INTEGER, isFavori INTEGER)";
        db.execSQL(CREATE_TABLE);*/
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        String DELETE = "delete from favori where 1 = 1";
        db.execSQL(DELETE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        // TODO Auto-generated method stub
        //String DETRUIRE_TABLE = "drop table jeu";
        //db.execSQL(DETRUIRE_TABLE);
        //String CREER_TABLE = "create table jeu(id_jeu INTEGER PRIMARY KEY, titre TEXT, plateforme TEXT, dateLive TEXT, alarmeActivee INTEGER)";
        //db.execSQL(CREER_TABLE);
    }
}