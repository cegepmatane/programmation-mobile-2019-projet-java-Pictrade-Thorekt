package ca.qc.cgmatane.pictrade.vue;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.IDNA;
import android.location.Location;
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
import ca.qc.cgmatane.pictrade.donnee.BaseDeDonneesClient;
import ca.qc.cgmatane.pictrade.donnee.Dictionnaire;

public class Carte extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnPoiClickListener,
        VueCarte,
        GoogleMap.OnMyLocationClickListener,
        GoogleMap.OnMyLocationButtonClickListener,
        Dictionnaire {


    private static final int MY_LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    protected FloatingActionMenu bouton_menu;
    protected FloatingActionButton bouton_menu_recherche;
    protected Intent intentionAfficherCommerce;
    protected ControleurCarte controleurCarte = new ControleurCarte(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_carte);

        BaseDeDonneesClient.getInstance(getApplicationContext());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //appel par le controlleur pour demander l'autorisation de géolocalisation
        controleurCarte.actionPermissionGeolocalisation();

        // intanciation du bouton du menu et appel au controleur pour afficher la page recherche
        bouton_menu = (FloatingActionMenu) findViewById(R.id.vue_carte_bouton_menu);
        bouton_menu_recherche = (FloatingActionButton) findViewById(R.id.vue_carte_bouton_recherche);
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

        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
//        LatLng matane = new LatLng(48.8526, -67.518);
//        mMap.addMarker(new MarkerOptions().position(matane).title("Matane"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(matane));
//        mMap.setPadding(0, 0, 300, 0);
//        Circle cercle = mMap.addCircle(new CircleOptions()
//                .center(matane)
//                .radius(2000.0)
//                .strokeColor(Color.BLACK)
//                .strokeWidth(1)
//                .fillColor(0x4500ff00));

    }

    @Override
    public void onPoiClick(PointOfInterest pointDInteret) {
        controleurCarte.actionNaviguerAfficherCommerce(pointDInteret);
    }


    @Override
    public void naviguerAfficherCommerce(PointOfInterest pointDInteret) {
        intentionAfficherCommerce = new Intent(Carte.this, AfficherCommerce.class);
        intentionAfficherCommerce.putExtra(POINT_D_INTERET, pointDInteret);
        startActivity(intentionAfficherCommerce);
    }

    //    @Override
    public void naviguerRecherche() {
        Intent intentionRechercheCommerce = new Intent(this, Recherche.class);
        startActivity(intentionRechercheCommerce);
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    public void permissionLocalisation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.d("Test de Permission", "Permission acceptée ");
            Toast.makeText(this, "Permission acceptée", Toast.LENGTH_SHORT).show();

        } else {

            // Show rationale and request permission.
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(this)
                        .setTitle("Permission needed")
                        .setMessage("This permission is needed because of this and that")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(Carte.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION_REQUEST_CODE);
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION_REQUEST_CODE);
            }
        }
    }
    @Override
        public void onRequestPermissionsResult ( int requestCode ,@NonNull String[] permissions, @NonNull int[] grantResults){
            if (requestCode == MY_LOCATION_REQUEST_CODE) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


