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
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Spill extends AppCompatActivity {
    private int tall =1, valgttallet;
    TextView spørsmåltall, spørsmålstykke, tilbakemelding;
    EditText skrivsvar;
    Button sendsvar;
    public static final String SharedPref = "sharedpref";
    String valgt;
    ArrayList arraySpørsmål, arraySvar;
    int i;
    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);


        SharedPreferences preferences = getSharedPreferences(SharedPref, MODE_PRIVATE);
        valgt = Velgregnestykker.VALGT;
        valgttallet = preferences.getInt(valgt, 0);

        sendsvar = findViewById(R.id.sendSvar);


        spørsmåltall = findViewById(R.id.spørsmåltall);
        spørsmålstykke =  findViewById(R.id.spørsmålstykke);
        skrivsvar =  findViewById(R.id.skrivSvar);
        tilbakemelding =  findViewById(R.id.tilbakemelding);

        arraySpørsmål = new ArrayList(15);
        arraySvar = new ArrayList(15);


        // legger regnestykker og svar inn i arrays
        for(int i =0; i < 15; i++){
            for(int j =0; j < 15; j++) {

                // 0 - 100
                int tall1 = (int) Math.floor(Math.random() * 31);
                int tall2 = (int) Math.floor(Math.random() * 31);
                String tall1sting = String.valueOf(tall1);
                String tall2sting = String.valueOf(tall2);
                String spørsmål = tall1sting + " + " + tall2sting;
                int svar = tall1 + tall2;

                arraySpørsmål.add(spørsmål);
                arraySvar.add(svar);

                if(i > 0) {
                    if (arraySpørsmål.get(i) == arraySpørsmål.get(j)) {
                        arraySpørsmål.remove(i);
                    }
                }

            }

            if(arraySpørsmål.size() == 15){
                break;
            }
        }

        Log.d("hello", String.valueOf(arraySpørsmål));
        Log.d("hello2", String.valueOf(arraySvar));


        i = (int) Math.floor(Math.random() * arraySpørsmål.size());
        spørsmålstykke.setText(String.valueOf(arraySpørsmål.get(i)));

        Log.d("hei2", String.valueOf(arraySvar.get(i)));

        sendsvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String skrevetsvar = String.valueOf(skrivsvar.getText());
                String svar = String.valueOf(arraySvar.get(i));


                if(skrevetsvar.equals(svar)){
                    String riktigSvar = getResources().getString(R.string.riktigSvar);
                    tilbakemelding.setText(riktigSvar);
                    skrivsvar.setText("");

                    arraySpørsmål.remove(i);
                    arraySvar.remove(i);

                    Log.d("hei2", String.valueOf(arraySpørsmål));
                    i = (int) Math.floor(Math.random() * arraySpørsmål.size());
                    spørsmålstykke.setText(String.valueOf(arraySpørsmål.get(i)));
                    nestespørsmål();

                } else {
                    String tilbakemeldingDel1 = getResources().getString(R.string.tilbakemeldingdel1);
                    String tilbakemeldingDel2 = getResources().getString(R.string.tilbakemeldingdel2);
                    String tilbakemeldingDel3 = getResources().getString(R.string.tilbakemeldingdel3);

                    String[] regnestykke = String.valueOf(arraySpørsmål.get(i)).split(" ");
                    String tilbakemeldingString = tilbakemeldingDel1 + regnestykke[0] + tilbakemeldingDel2 + regnestykke[2] + tilbakemeldingDel3;
                    tilbakemelding.setText(tilbakemeldingString);
                }


                if(tall >= valgttallet){
                    alert();
                }
            }
        });


    }
    // metode for å vise en safeguard hvis spilleren trykker på tilbake knappen med et uhel
    @Override
    public void onBackPressed() {
        String ja = getResources().getString(R.string.jaAlert);
        String nei = getResources().getString(R.string.neiAlert);
        String safeguard = getResources().getString(R.string.safeguard);

        new AlertDialog.Builder(this)
                .setMessage(safeguard)
                .setCancelable(false)
                .setPositiveButton(ja, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton(nei, null)
                .show();


    }
    //metode for å vise fram hvilket spørsmål vi er på
    private void nestespørsmål(){
        tall++;
        spørsmåltall.setText(String.valueOf(tall));
    }


    // metode for å gi bedskjed at spillet er ferdig
    private void alert(){
        String ferdigAlert = getResources().getString(R.string.ferdigAlert);
        String tilbake = getResources().getString(R.string.tilbake);
        String enTil = getResources().getString(R.string.enTil);

        new AlertDialog.Builder(this)
                .setMessage(ferdigAlert)
                .setCancelable(false)
                .setPositiveButton(enTil, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                })
                .setNegativeButton(tilbake, new DialogInterface.OnClickListener() {
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