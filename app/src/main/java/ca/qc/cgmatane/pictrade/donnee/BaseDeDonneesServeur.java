package ca.qc.cgmatane.pictrade.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonneesServeur {

    private static BaseDeDonneesServeur instance = null;

    public static BaseDeDonneesServeur getInstance() {
        if (instance != null) {
            instance = new BaseDeDonneesServeur();
        }
        return instance;
    }


}
