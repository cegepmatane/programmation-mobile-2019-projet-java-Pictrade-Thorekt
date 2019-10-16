package ca.qc.cgmatane.pictrade.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.controleur.ControleurModifierCommerce;

public class ModifierCommerce extends AppCompatActivity implements VueModifierCommerce {
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
        commerceHashMap = (HashMap<String,String>) parametres.get("commerce");
        controleurModifierCommerce.onCreate(this);


    }

    @Override
    public void preRemplirChamps(){
        vueModifierCommerceChampsAdresse = (EditText) findViewById(R.id.vue_modifier_commerce_champs_addresse);
        vueModifierCommerceChampsContact = (EditText) findViewById(R.id.vue_modifier_commerce_champs_contact);
        vueModifierCommerceChampsHoraireOuverture = (EditText) findViewById(R.id.vue_modifier_commerce_champs_horaire_ouverture);
        vueModifierCommerceChampsHoraireFermeture = (EditText) findViewById(R.id.vue_modifier_commerce_champs_horaire_fermeture);

        vueModifierCommerceChampsAdresse.setText(commerceHashMap.get("adresse"));
        vueModifierCommerceChampsContact.setText(commerceHashMap.get("contact"));
        vueModifierCommerceChampsHoraireOuverture.setText(commerceHashMap.get("horaireOuverture"));
        vueModifierCommerceChampsHoraireFermeture.setText(commerceHashMap.get("horaireFermeture"));

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

    private void enregisterModification(){
        commerceHashMap.put("adresse",vueModifierCommerceChampsAdresse.getText().toString());
        commerceHashMap.put("contact",vueModifierCommerceChampsContact.getText().toString());
        commerceHashMap.put("horaireOuverture",vueModifierCommerceChampsHoraireOuverture.getText().toString());
        commerceHashMap.put("horaireFermeture",vueModifierCommerceChampsHoraireFermeture.getText().toString());
        controleurModifierCommerce.validerModification();
    }

    @Override
    public HashMap<String, String> getCommerceHashMap() {
        return commerceHashMap;
    }






}
