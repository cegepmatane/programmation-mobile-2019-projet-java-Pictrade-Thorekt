package ca.qc.cgmatane.pictrade.controleur;

import android.content.Context;

interface Controleur {
    void onCreate(Context applicationContext);
    void onPause();
    void onResume();
    void onDestroy();
    void onActivityResult(int activite);
}
