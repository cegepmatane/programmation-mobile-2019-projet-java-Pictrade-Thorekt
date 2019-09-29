package ca.qc.cgmatane.pictrade.controleur;

import android.content.Context;

public interface controleur {
    void onCreate(Context applicationContext);
    void onPause();
    void onResume();
    void onDestroy();
    void onActivityResult(int activite);
}
