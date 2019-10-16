package ca.qc.cgmatane.pictrade.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ca.qc.cgmatane.pictrade.R;

public class Galerie extends AppCompatActivity implements VueGalerie{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_galerie);
    }
}
