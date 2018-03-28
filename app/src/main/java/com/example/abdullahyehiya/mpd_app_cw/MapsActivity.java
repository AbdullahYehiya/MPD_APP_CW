package com.example.abdullahyehiya.mpd_app_cw;

/**
 * S1512605
 * Abdullah Yehiya
 */

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String coord;
    Double latD,longD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        coord = intent.getStringExtra("coordinates");
        String[] coordinatesValues=coord.split(" ");
        String lati=coordinatesValues[0];
        String longi=coordinatesValues[1];
        latD = Double.valueOf(lati) ;
        longD=Double.valueOf(longi) ;
        //   Toast.makeText(MapsActivity.this, lati, Toast.LENGTH_SHORT).show();
        // Toast.makeText(MapsActivity.this, longi, Toast.LENGTH_SHORT).show();
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

        // Add a marker in current location and move the camera
        LatLng currentloc = new LatLng(latD, longD);
        mMap.addMarker(new MarkerOptions().position(currentloc).title("Current Event Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentloc, 12));
    }
}
