package com.example.mappe1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class Sprok extends AppCompatActivity {

    public static final String SharedPref = "sharedpref";

    public static final String TEXT = "text";

     String text;

    String landskode;

    Button norskButton, tyskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprok);

        norskButton = findViewById(R.id.norsk);
        tyskButton = findViewById(R.id.tysk);

        Button tilbakeButton = findViewById(R.id.tilbake);

        norskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                landskode = "no";
                setLocal(Sprok.this,landskode);
                finish();
                startActivity(getIntent());
                saveData();
            }
        });

        tyskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                landskode = "de";
                setLocal(Sprok.this,landskode);
                finish();
                startActivity(getIntent());
                saveData();

            }
        });
        loadData();

        tilbakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPref();
            }
        });



    }
        @SuppressWarnings("deprecation")
        public void setLocal(Activity Sprok, String landskode) {
            Locale locale = new Locale(landskode);
            locale.setDefault(locale);

            Resources resource = Sprok.getResources();
            DisplayMetrics metrics = resource.getDisplayMetrics();
            Configuration config = resource.getConfiguration();
            config.setLocale(locale);
            resource.updateConfiguration(config,metrics);

        }

    public void openPref() {
        Intent intent = new Intent(Sprok.this, Preferanser.class);
        startActivity(intent);
    }

    public void saveData() {
        SharedPreferences preferences = getSharedPreferences(SharedPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TEXT, landskode);
        editor.apply();

        Toast.makeText(this, "DATA SAVED", Toast.LENGTH_SHORT).show();

    }

    public void loadData() {
        SharedPreferences preferences = getSharedPreferences(SharedPref, MODE_PRIVATE);
        text = preferences.getString(TEXT, "");

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("landskode", text);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        text = savedInstanceState.getString("landskode");
        setLocal(Sprok.this,text);
        finish();
        startActivity(getIntent());
    }
}