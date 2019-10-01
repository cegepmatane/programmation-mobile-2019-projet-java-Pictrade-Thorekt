package ca.qc.cgmatane.pictrade.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.donnee.CommerceDAO;

public class Commerce extends AppCompatActivity implements VueCommerce {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_commerce);
    }

    protected void informationCommerce() {
        
    }
}

