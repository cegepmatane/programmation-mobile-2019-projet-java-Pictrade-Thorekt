package ca.qc.cgmatane.pictrade.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.controleur.ControleurModifierCommerce;
import ca.qc.cgmatane.pictrade.modele.Commerce;

public class ModifierCommerce extends AppCompatActivity implements VueModifierCommerce {
    private Commerce commerce;
    private ControleurModifierCommerce controleurModifierCommerce
            = new ControleurModifierCommerce(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_commerce);
    }

    @Override
    public void setCommerce(Commerce commerce) {
        this.commerce=commerce;
    }
}
