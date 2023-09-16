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

    Button norskButton, tyskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprok);
        // definerer knappene
        norskButton = findViewById(R.id.norsk);
        tyskButton = findViewById(R.id.tysk);

        norskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // endrer språket til defualt verdien fra strings.xml
                LocaleListCompat appLocale = LocaleListCompat.forLanguageTags("");
                AppCompatDelegate.setApplicationLocales(appLocale);
                toast();
            }
        });

        tyskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // endrer språket til tysk ved å bruke landskode (de) fra strings.xml
                LocaleListCompat appLocale = LocaleListCompat.forLanguageTags("de-DE");
                AppCompatDelegate.setApplicationLocales(appLocale);
                toast();
            }
        });
    }

    public void toast() {
        // hvis norsk er valgt så skrives det at data er lagret til brukeren
        String dataSaved = getResources().getString(R.string.dataLagret);
        Toast.makeText(Sprok.this, dataSaved, Toast.LENGTH_SHORT).show();
    }




}