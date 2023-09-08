package com.example.mappe1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Spill extends AppCompatActivity {
    private int tall =1, valgttallet;
    TextView spørsmåltall;
    Button sendsvar;
    public static final String SharedPref = "sharedpref";
    String valgt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);


        SharedPreferences preferences = getSharedPreferences(SharedPref, MODE_PRIVATE);
        valgt = Velgregnestykker.VALGT;

        valgttallet = preferences.getInt(valgt, 0);

        sendsvar = findViewById(R.id.sendSvar);
        spørsmåltall = findViewById(R.id.spørsmåltall);

        sendsvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Mytag",String.valueOf(valgttallet));

                nestespørsmål();

                if(tall >= valgttallet){
                    alert();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {

        String safeguard = getResources().getString(R.string.safeguard);

        new AlertDialog.Builder(this)
                .setMessage(safeguard)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();


    }

    private void nestespørsmål(){
        tall++;
        Log.d("Mytag2",String.valueOf(tall));
        spørsmåltall.setText(String.valueOf(tall));


    }

    // metode for å gi bedskjed at spillet er ferdig
    private void alert(){
        new AlertDialog.Builder(this)
                .setMessage("Ferdig")
                .setCancelable(false)
                .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                })
                .setNegativeButton("gå tilbake", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                     finish();
                    }
                })
                .show();
    }





    // Kilde for å lagre spørsmål indikator når telefonen roteres
    // https://www.youtube.com/watch?v=TcTgbVudLyQ&t=118s
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tall", tall);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tall = savedInstanceState.getInt("tall");
        spørsmåltall.setText(String.valueOf(tall));
    }
}