package com.example.mappe1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class Sprok extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprok);


        Button norskButton = findViewById(R.id.norsk);
        Button tyskButton = findViewById(R.id.tysk);

        Button tilbakeButton = findViewById(R.id.tilbake);


        norskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String landskode = "no";
                setLocal(Sprok.this,landskode);
                finish();
                startActivity(getIntent());
            }
        });

        tyskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String landskode = "de";
                setLocal(Sprok.this,landskode);
                finish();
                startActivity(getIntent());
            }
        });

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

}