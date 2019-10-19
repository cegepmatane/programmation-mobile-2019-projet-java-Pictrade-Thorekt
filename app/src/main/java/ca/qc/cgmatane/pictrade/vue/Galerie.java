package ca.qc.cgmatane.pictrade.vue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import ca.qc.cgmatane.pictrade.R;
import ca.qc.cgmatane.pictrade.controleur.ControleurGalerie;
import ca.qc.cgmatane.pictrade.modele.Photo;

public class Galerie extends AppCompatActivity implements VueGalerie {
    private List<Photo> listePhoto;
    private RecyclerView vueGalerieListePhoto;
    protected ControleurGalerie controleurGalerie = new ControleurGalerie(this);
    private Bundle parametres;
    Intent takePictureIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_galerie);

        parametres = this.getIntent().getExtras();


        vueGalerieListePhoto = (RecyclerView) findViewById(R.id.vue_galerie_liste_photo);
        vueGalerieListePhoto.setLayoutManager(new LinearLayoutManager(this));
        vueGalerieListePhoto.setItemAnimator(new DefaultItemAnimator());

        controleurGalerie.onCreate(getApplicationContext());

    }

    @Override
    public void afficherGalerie() {
        GalerieAdapteur galerieAdapteur = new GalerieAdapteur(R.layout.vue_ligne_galerie, listePhoto);
        vueGalerieListePhoto.setAdapter(galerieAdapteur);
    }

    @Override
    public void setListePhoto(List<Photo> listePhoto) {
        this.listePhoto = listePhoto;
    }

    @Override
    public void naviguerPrendrePhoto() {
        takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, controleurGalerie.ACTIVITE_PRENDRE_PHOTO);
        }
    }

    @Override
    public Bundle getParametres() {
        return parametres;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controleurGalerie.onActivityResult(requestCode);
    }

    private class GalerieAdapteur extends RecyclerView.Adapter<GalerieAdapteur.ViewHolder> {
        private int vueLigneGalerieLayout;
        private List<Photo> listePhoto;

        public GalerieAdapteur(int vueLigneGalerieLayout, List<Photo> listePhoto) {
            this.vueLigneGalerieLayout = vueLigneGalerieLayout;
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

            if (positionGauche <= listePhoto.size()) {
                holder.vueLigneGaleriePhotoGauche.setImageBitmap(listePhoto.get(positionGauche).getImage());
            }
            if (positionGauche <= listePhoto.size()) {
                holder.vueLigneGaleriePhotoGauche.setImageBitmap(listePhoto.get(positionMilieu).getImage());
            }
            if (positionGauche <= listePhoto.size()) {
                holder.vueLigneGaleriePhotoGauche.setImageBitmap(listePhoto.get(positionDroite).getImage());
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
}
