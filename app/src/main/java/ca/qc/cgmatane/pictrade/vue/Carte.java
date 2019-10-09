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
import android.widget.Button;

import ca.qc.cgmatane.pictrade.controleur.ControleurCarte;
import ca.qc.cgmatane.pictrade.donnee.CommerceDAO;

public class Carte extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnPoiClickListener, VueCarte {
    private GoogleMap mMap;
    private CommerceDAO accesseurCommerceDAO;
    protected Button bouton_menu;
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

//        bouton_menu.findViewById(R.id.bouton_acces_recherche);
//        bouton_menu.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View arg0) {

//                controleurCarte.actionMenuRechercheCommerce();
//                startActivity(intentionRechercherCommerce, ACTIVITE_RECHERCHER_COMMERCE);
//            }
//        });
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
    public void onPoiClick(PointOfInterest poi) {
        Log.d("Clicked: ",
                poi.name + "\nPlace ID:" + poi.placeId +
                        "\nLatitude:" + poi.latLng.latitude +
                        " Longitude:" + poi.latLng.longitude);
        intentionCommerce = new Intent(Carte.this, AfficherCommerce.class);
        intentionCommerce.putExtra("poi", poi);
        startActivity(intentionCommerce);
    }

//    @Override
//    public void naviguerRechercheCommerce() {
//        Intent intentionRechercheCommerce = new Intent(this, Recherche.class);
//        //startActivityForResult(intentionRechercheCommerce, ControleurRecherche);
//    }
}
