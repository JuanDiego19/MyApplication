package com.example.worldskills.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Chronometer crono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crono = findViewById(R.id.crono);
        crono.start();
    }


    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "" + crono.getContentDescription(), Toast.LENGTH_SHORT).show();
        Intent miIntent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(miIntent);
    }
}
