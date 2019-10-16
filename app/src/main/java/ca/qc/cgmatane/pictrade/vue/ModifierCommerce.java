package ca.qc.cgmatane.pictrade.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.controleur.ControleurModifierCommerce;
import ca.qc.cgmatane.pictrade.donnee.Dictionnaire;

public class ModifierCommerce extends AppCompatActivity implements VueModifierCommerce, Dictionnaire {
    private ControleurModifierCommerce controleurModifierCommerce
            = new ControleurModifierCommerce(this);
    private HashMap<String,String> commerceHashMap;
    private EditText vueModifierCommerceChampsAdresse;
    private EditText vueModifierCommerceChampsContact;
    private EditText vueModifierCommerceChampsHoraireOuverture;
    private EditText vueModifierCommerceChampsHoraireFermeture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_commerce);

        Bundle parametres = this.getIntent().getExtras();
        commerceHashMap = (HashMap<String,String>) parametres.get(CLE_COMMERCE);
        controleurModifierCommerce.onCreate(this);


    }

    @Override
    public void preRemplirChamps(){
        vueModifierCommerceChampsAdresse = (EditText) findViewById(R.id.vue_modifier_commerce_champs_addresse);
        vueModifierCommerceChampsContact = (EditText) findViewById(R.id.vue_modifier_commerce_champs_contact);
        vueModifierCommerceChampsHoraireOuverture = (EditText) findViewById(R.id.vue_modifier_commerce_champs_horaire_ouverture);
        vueModifierCommerceChampsHoraireFermeture = (EditText) findViewById(R.id.vue_modifier_commerce_champs_horaire_fermeture);

        vueModifierCommerceChampsAdresse.setText(commerceHashMap.get(CLE_ADRESSE_COMMERCE));
        vueModifierCommerceChampsContact.setText(commerceHashMap.get(CLE_CONTACT_COMMERCE));
        vueModifierCommerceChampsHoraireOuverture.setText(commerceHashMap.get(CLE_HORAIRE_OUVERTURE_COMMERCE));
        vueModifierCommerceChampsHoraireFermeture.setText(commerceHashMap.get(CLE_HORAIRE_FERMETURE_COMMERCE));

        Button vueModifierCommerceValiderModification =
                (Button) findViewById(R.id.vue_modifier_commerce_action_valider_modification);
        vueModifierCommerceValiderModification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enregisterModification();
            }
        });

        Button vueModifierCommerceAnnulerModification =
                (Button) findViewById(R.id.vue_modifier_commerce_action_annuler_modification);
        vueModifierCommerceAnnulerModification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controleurModifierCommerce.annulerModification();
            }
        });
    }

    @Override
    public void naviguerCommerce() {
        this.finish();
    }

    private void enregisterModification(){
        commerceHashMap.put(CLE_ADRESSE_COMMERCE,vueModifierCommerceChampsAdresse.getText().toString());
        commerceHashMap.put(CLE_CONTACT_COMMERCE,vueModifierCommerceChampsContact.getText().toString());
        commerceHashMap.put(CLE_HORAIRE_OUVERTURE_COMMERCE,vueModifierCommerceChampsHoraireOuverture.getText().toString());
        commerceHashMap.put(CLE_HORAIRE_FERMETURE_COMMERCE,vueModifierCommerceChampsHoraireFermeture.getText().toString());
        controleurModifierCommerce.validerModification();
    }

    @Override
    public HashMap<String, String> getCommerceHashMap() {
        return commerceHashMap;
    }






}
