package com.example.mappe1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

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

    Button norskButton, tyskButton, tilbakeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprok);

        norskButton = findViewById(R.id.norsk);
        tyskButton = findViewById(R.id.tysk);

        tilbakeButton = findViewById(R.id.tilbake);

        norskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Sprok.this, "DATA SAVED", Toast.LENGTH_SHORT).show();
                LocaleListCompat appLocale = LocaleListCompat.forLanguageTags("");
                AppCompatDelegate.setApplicationLocales(appLocale);
                Toast.makeText(Sprok.this, "DATA SAVED", Toast.LENGTH_SHORT).show();
            }
        });

        tyskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocaleListCompat appLocale = LocaleListCompat.forLanguageTags("de-DE");
                AppCompatDelegate.setApplicationLocales(appLocale);
                Toast.makeText(Sprok.this, "DATA SAVED", Toast.LENGTH_SHORT).show();

            }
        });

        tilbakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPref();
            }
        });

    }

    public void openPref() {
        Intent intent = new Intent(Sprok.this, Preferanser.class);
        startActivity(intent);
    }
}