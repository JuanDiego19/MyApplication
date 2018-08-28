package com.example.worldskills.myapplication;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Chronometer crono;
    Conexion conn;
    SQLiteDatabase bd;
    EditText campo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crono = findViewById(R.id.crono);
        crono.start();
        conn = new Conexion(getApplicationContext(), "bd", null, 1);

        campo = findViewById(R.id.numero);

    }


    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "" + crono.getContentDescription(), Toast.LENGTH_SHORT).show();
        Intent miIntent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(miIntent);
     // bd=conn.getReadableDatabase();

       // Cursor cursor=bd.rawQuery("SELECT * FROM "+Utilidades.NOMBRE,null);
    }

    public void onClic(View view) {
        bd = conn.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(Utilidades.ID,Integer.parseInt(campo.getText().toString()));

        bd.insert(Utilidades.NOMBRE,null,contentValues);
    }
}
