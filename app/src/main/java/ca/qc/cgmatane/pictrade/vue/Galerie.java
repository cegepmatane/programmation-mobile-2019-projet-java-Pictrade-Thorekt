package ca.qc.cgmatane.pictrade.vue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.controleur.ControleurGalerie;
import ca.qc.cgmatane.pictrade.modele.Photo;

public class Galerie extends AppCompatActivity implements VueGalerie, GestureDetector.OnGestureListener {
    private List<Photo> listePhoto;
    private RecyclerView vueGalerieListePhoto;
    protected ControleurGalerie controleurGalerie = new ControleurGalerie(this);
    private Bundle parametres;
    private Intent intentionPrendrePhoto;
    private Bundle extras;

    private GestureDetectorCompat mDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_galerie);

        parametres = this.getIntent().getExtras();


        controleurGalerie.onCreate(getApplicationContext());


        mDetector = new GestureDetectorCompat(this, this);

    }

    @Override
    public void afficherGalerie() {

        vueGalerieListePhoto = (RecyclerView) findViewById(R.id.vue_galerie_liste_photo);
        vueGalerieListePhoto.setLayoutManager(new LinearLayoutManager(this));
        vueGalerieListePhoto.setItemAnimator(new DefaultItemAnimator());

        GalerieAdapteur galerieAdapteur = new GalerieAdapteur(R.layout.vue_ligne_galerie, listePhoto);
        vueGalerieListePhoto.setAdapter(galerieAdapteur);

        Button vueGalerieActionNaviguerPrendrePhoto = (Button) findViewById(R.id.vue_galerie_action_naviguer_prendre_photo);

        vueGalerieActionNaviguerPrendrePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controleurGalerie.actionNaviguerPrendrePhoto();
            }
        });
    }

    @Override
    public void naviguerCommerce() {
        this.finish();
    }

    @Override
    public void setListePhoto(List<Photo> listePhoto) {


        this.listePhoto = listePhoto;
    }

    @Override
    public void naviguerPrendrePhoto() {
        intentionPrendrePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intentionPrendrePhoto.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intentionPrendrePhoto, controleurGalerie.ACTIVITE_PRENDRE_PHOTO);
        }
    }

    @Override
    public Bundle getParametres() {
        return parametres;
    }

    public Bundle getExtras() {
        return extras;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            extras = data.getExtras();
            controleurGalerie.onActivityResult(requestCode);
        }
    }

    private class GalerieAdapteur extends RecyclerView.Adapter<GalerieAdapteur.ViewHolder> {
        private int vueLigneGalerieLayout;
        private List<Photo> listePhoto;

        public GalerieAdapteur(int vueLigneGalerieLayout, List<Photo> listePhoto) {
            this.vueLigneGalerieLayout = vueLigneGalerieLayout;
            if (this.listePhoto != null) {
                this.listePhoto.clear();
            }
            this.listePhoto = listePhoto;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View vue = LayoutInflater.from(parent.getContext()).inflate(vueLigneGalerieLayout, parent, false);
            ViewHolder vueHolder = new ViewHolder(vue);
            return vueHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            int positionGauche = position * 3;
            int positionMilieu = position * 3 + 1;
            int positionDroite = position * 3 + 2;

            if (positionGauche < listePhoto.size()) {
                holder.vueLigneGaleriePhotoGauche.setImageBitmap(listePhoto.get(positionGauche).getImage());
            }
            if (positionMilieu < listePhoto.size()) {
                holder.vueLigneGaleriePhotoMilieu.setImageBitmap(listePhoto.get(positionMilieu).getImage());
            }
            if (positionDroite < listePhoto.size()) {
                holder.vueLigneGaleriePhotoDroite.setImageBitmap(listePhoto.get(positionDroite).getImage());
            }
        }

        @Override
        public int getItemCount() {
            return listePhoto == null ? 0 : listePhoto.size();
        }

        // Static inner class to initialize the views of rows
        private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public ImageView vueLigneGaleriePhotoGauche;
            public ImageView vueLigneGaleriePhotoMilieu;
            public ImageView vueLigneGaleriePhotoDroite;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                vueLigneGaleriePhotoGauche = (ImageView) itemView.findViewById(R.id.vue_ligne_galerie_photo_gauche);
                vueLigneGaleriePhotoMilieu = (ImageView) itemView.findViewById(R.id.vue_ligne_galerie_photo_milieu);
                vueLigneGaleriePhotoDroite = (ImageView) itemView.findViewById(R.id.vue_ligne_galerie_photo_droite);
            }

            @Override
            public void onClick(View v) {

            }
        }
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
        Log.d("OnFling", "onFling: " + movementDeDepart.toString() + MovementDeplacement.toString());
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
        controleurGalerie.annulerModification();
    }

    private void onSwipeRight() {
    }

}
