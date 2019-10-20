package ca.qc.cgmatane.pictrade.vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;


import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.controleur.ControleurModifierCommerce;
import ca.qc.cgmatane.pictrade.donnee.Dictionnaire;

public class ModifierCommerce extends AppCompatActivity implements VueModifierCommerce, Dictionnaire, GestureDetector.OnGestureListener {
    private ControleurModifierCommerce controleurModifierCommerce
            = new ControleurModifierCommerce(this);
    private HashMap<String, String> commerceHashMap;
    private TextView vueModifierCommerceNom;
    private EditText vueModifierCommerceChampsAdresse;
    private EditText vueModifierCommerceChampsContact;
    private EditText vueModifierCommerceChampsHoraireOuverture;
    private EditText vueModifierCommerceChampsHoraireFermeture;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_commerce);

        Bundle parametres = this.getIntent().getExtras();
        commerceHashMap = (HashMap<String, String>) parametres.get(CLE_COMMERCE);
        controleurModifierCommerce.onCreate(this);
        mDetector = new GestureDetectorCompat(this, this);

    }

    @Override
    public void preRemplirChamps() {

        vueModifierCommerceNom = (TextView) findViewById(R.id.vue_modifier_commerce_nom);

        vueModifierCommerceChampsAdresse = (EditText) findViewById(R.id.vue_modifier_commerce_champs_addresse);
        vueModifierCommerceChampsContact = (EditText) findViewById(R.id.vue_modifier_commerce_champs_contact);
        vueModifierCommerceChampsHoraireOuverture = (EditText) findViewById(R.id.vue_modifier_commerce_champs_horaire_ouverture);
        vueModifierCommerceChampsHoraireFermeture = (EditText) findViewById(R.id.vue_modifier_commerce_champs_horaire_fermeture);


        vueModifierCommerceNom.setText(commerceHashMap.get(CLE_NOM_COMMERCE));
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

    private void enregisterModification() {
        commerceHashMap.put(CLE_ADRESSE_COMMERCE, vueModifierCommerceChampsAdresse.getText().toString());
        commerceHashMap.put(CLE_CONTACT_COMMERCE, vueModifierCommerceChampsContact.getText().toString());
        commerceHashMap.put(CLE_HORAIRE_OUVERTURE_COMMERCE, vueModifierCommerceChampsHoraireOuverture.getText().toString());
        commerceHashMap.put(CLE_HORAIRE_FERMETURE_COMMERCE, vueModifierCommerceChampsHoraireFermeture.getText().toString());
        controleurModifierCommerce.validerModification();
    }

    @Override
    public HashMap<String, String> getCommerceHashMap() {
        return commerceHashMap;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent movementDeDepart, MotionEvent MovementDeplacement,
                           float velocityX, float velocityY) {
        float diffY = MovementDeplacement.getY() - movementDeDepart.getY();
        float diffX = MovementDeplacement.getX() - movementDeDepart.getX();
        if (Math.abs(diffX) > Math.abs(diffY)) {
            //swipe droite ou gauche
            if (Math.abs(diffX) > 100 && Math.abs(velocityX) > 100) {
                if (diffX > 0) {
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
            }


        }
        return true;
    }

    private void onSwipeLeft() {
        //Toast.makeText(this, "SwipLeft", Toast.LENGTH_SHORT).show();

    }

    private void onSwipeRight() {
        Toast.makeText(this, "Swip vers gauche pour annuler", Toast.LENGTH_SHORT).show();
        controleurModifierCommerce.annulerModification();
    }

}
