package ca.qc.cgmatane.pictrade.vue;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.PointOfInterest;

import ca.qc.cgmatane.pictrade.R;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

import ca.qc.cgmatane.pictrade.controleur.ControleurCarte;
import ca.qc.cgmatane.pictrade.controleur.ControleurRecherche;
import ca.qc.cgmatane.pictrade.donnee.CommerceDAO;

public class Carte extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnPoiClickListener, VueCarte {
    private GoogleMap mMap;
    protected FloatingActionMenu bouton_menu;
    protected FloatingActionButton bouton_menu_recherche;
    protected Intent intentionCommerce;
    protected ControleurCarte controleurCarte = new ControleurCarte(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_carte);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        intentionRechercherCommerce = new Intent(Carte.this,
//                Recherche.class);

        bouton_menu = (FloatingActionMenu) findViewById(R.id.bouton_menu);
        bouton_menu_recherche= (FloatingActionButton) findViewById(R.id.bouton_recherche);
        bouton_menu_recherche.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Toast.makeText(Carte.this, "action pour le bouton recherche", Toast.LENGTH_SHORT).show();
                controleurCarte.actionNaviguerMenuRechercheCommerce();
            }
        });

        controleurCarte.onCreate(getApplicationContext());
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnPoiClickListener(this);


    }

    @Override
    public void onPoiClick(PointOfInterest pointDInteret) {
        controleurCarte.actionNaviguerAfficherCommerce(pointDInteret);
    }


    @Override
    public void naviguerAfficherCommerce(PointOfInterest pointDInteret) {
        intentionCommerce = new Intent(Carte.this, AfficherCommerce.class);
        intentionCommerce.putExtra("pointDInteret", pointDInteret);
        startActivity(intentionCommerce);
    }

    //    @Override
    public void naviguerRecherche() {
        Intent intentionRechercheCommerce = new Intent(this, Recherche.class);
        startActivity(intentionRechercheCommerce);
    }
}
