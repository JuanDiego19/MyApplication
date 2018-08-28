package com.example.worldskills.myapplication;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    JsonObjectRequest objectRequest;
    RequestQueue request;


    String lactitudI = "4.596616112679607";
    String longitudI = "-74.07291412353516";

    String lactitudF = "-0.3534564348305434";
    String longitudF = "-78.43953308300695";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        request = Volley.newRequestQueue(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        cargarWebservices(lactitudI, longitudI, longitudF, lactitudF);
    }

    private void cargarWebservices(String lactitudI, String longitudI, String longitudF, String lactitudF) {
        String url="https://maps.googleapis.com/maps/api/directions/json?origin=4.547195915737696,-75.66195130348206&destination=4.540984,-75.668126";
        //String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + lactitudI + "," + longitudI + "&destination=" + lactitudF + "," + longitudF;
        objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                cargarPuntos(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
    }


    List cargarPuntos(JSONObject json) {
        List lita = new ArrayList();
        JSONArray jRoutes, jLegs, jSteps;
        String polyline = "";

        try {
            jRoutes = json.getJSONArray("routes");
            for (int i = 0; i<jRoutes.length();i++) {
                jLegs = ((JSONObject) (jRoutes.get(i))).getJSONArray("legs");
                for (int j = 0; j < jLegs.length(); j++) {
                    jSteps = ((JSONObject)(jLegs.get(i))).getJSONArray("steps");
                    for (int k = 0; k <jSteps.length(); k ++){
                        polyline = ""+((JSONObject)((JSONObject) jSteps.get(i)).get("polyline")).get("points");
                        List<LatLng> list = PolyUtil.decode(polyline);
                        mMap.addPolyline(new PolylineOptions().addAll(list).color(Color.GREEN));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lita;
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
