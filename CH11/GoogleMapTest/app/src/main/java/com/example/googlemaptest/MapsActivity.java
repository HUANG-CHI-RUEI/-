package com.example.googlemaptest;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        String[] arylat = new String[2];
        String[] arylng = new String[2];
        String[] arypointtile = new String[2];

        arylat[0] = "24.147225";
        arylat[1] = "24.142509";

        arylng[0] = "120.643003";
        arylng[1] = "120.646425";

        arypointtile[0] = "IKEA台中店";
        arypointtile[1] = "星巴克向新店";
        // Add a marker in Sydney and move the camera
        for(int i = 0; i < arypointtile.length; i++) {
            double dbelat = 0;
            double dbelng = 0;

            dbelat = Double.parseDouble(arylat[i].trim());
            dbelng = Double.parseDouble(arylng[i].trim());

            LatLng objoption = new LatLng(dbelat, dbelng);

            mMap.addMarker(new MarkerOptions().position(objoption).title(arypointtile[i]));

            LatLng objstartpoint = new LatLng(24.145269, 120.645637);

            mMap.addMarker(new MarkerOptions().position(objstartpoint).title("文新公園"));

            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);

            mMap.getUiSettings().setMapToolbarEnabled(true);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(objstartpoint));

            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
    }
}