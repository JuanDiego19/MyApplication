package com.example.worldskills.myapplication;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(4.595186099999999, -74.1116487);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Ubicacion"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        LatLng sydne2 = new LatLng(4.588545202924698, -74.1659642288933);
        mMap.addMarker(new MarkerOptions().position(sydne2).title("dos"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydne2));
        LatLng sydney3 = new LatLng(4.599187301317924, -74.06714571858816);
        mMap.addMarker(new MarkerOptions().position(sydney3).title("tres"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney3));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney3,10));
    }
}
