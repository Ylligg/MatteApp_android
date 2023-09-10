package com.example.mappe1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Spill extends AppCompatActivity {
    private int tall =1, valgttallet, i;
    TextView spørsmåltall, spørsmålstykke, tilbakemelding;
    EditText skrivsvar;
    Button sendsvar;
    public static final String SharedPref = "sharedpref";
    String valgt;
    ArrayList arraySpørsmål, arraySvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);

        // henter hvilket antall som ble valgt fra preferanser (5, 10, 15)
        SharedPreferences preferences = getSharedPreferences(SharedPref, MODE_PRIVATE);
        valgt = Velgregnestykker.VALGT;
        valgttallet = preferences.getInt(valgt, 0);

        sendsvar = findViewById(R.id.sendSvar);
        spørsmåltall = findViewById(R.id.spørsmåltall);
        spørsmålstykke =  findViewById(R.id.spørsmålstykke);
        skrivsvar =  findViewById(R.id.skrivSvar);
        tilbakemelding =  findViewById(R.id.tilbakemelding);

        spørsmåltall.setText("1");
        arraySpørsmål = new ArrayList(15);
        arraySvar = new ArrayList(15);

        // legger regnestykker og svar inn i arrays
        for(int i =0; i < 15; i++){
            for(int j =0; j < 15; j++) {

                // 0 - 100
                int tall1 = (int) Math.floor(Math.random() * 31);
                int tall2 = (int) Math.floor(Math.random() * 31);

                //gjør de om til string
                String tall1sting = String.valueOf(tall1);
                String tall2sting = String.valueOf(tall2);

                //lager en kombinert string eks: 2 + 2
                String spørsmål = tall1sting + " + " + tall2sting;
                int svar = tall1 + tall2;

                // adder variablene inn i arrays
                arraySpørsmål.add(spørsmål);
                arraySvar.add(svar);

                // fjerner duplikater hvis det har blitt lagt til
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

        i = (int) Math.floor(Math.random() * arraySpørsmål.size());
        spørsmålstykke.setText(String.valueOf(arraySpørsmål.get(i)));

        sendsvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String skrevetsvar = String.valueOf(skrivsvar.getText());
                String svar = String.valueOf(arraySvar.get(i));

                // hvis det du har skrevet er det samme som svaret så forstetter spillet
                if(skrevetsvar.equals(svar)){
                    String riktigSvar = getResources().getString(R.string.riktigSvar);

                    tilbakemelding.setText(riktigSvar);
                    skrivsvar.setText("");

                    arraySpørsmål.remove(i);
                    arraySvar.remove(i);

                    // hvis vi har nådd maks antall spørsmål (5,10 eller 15) så er spillet ferdigm, ellers så forsetter spillet
                    if(tall >= valgttallet){
                        alert();

                    } else{

                        i = (int) Math.floor(Math.random() * arraySpørsmål.size());
                        spørsmålstykke.setText(String.valueOf(arraySpørsmål.get(i)));
                        nestespørsmål();
                    }


                } else {
                    // hvis vi får feil så blir det skrevet ut en tilbakemelding
                    String tilbakemeldingDel1 = getResources().getString(R.string.tilbakemeldingdel1);
                    String tilbakemeldingDel2 = getResources().getString(R.string.tilbakemeldingdel2);
                    String tilbakemeldingDel3 = getResources().getString(R.string.tilbakemeldingdel3);

                    String[] regnestykke = String.valueOf(arraySpørsmål.get(i)).split(" ");
                    String tilbakemeldingString = tilbakemeldingDel1 + regnestykke[0] + tilbakemeldingDel2 + regnestykke[2] + tilbakemeldingDel3;
                    tilbakemelding.setText(tilbakemeldingString);
                }
            }
        });

    }

    // metode for å vise en safeguard hvis spilleren trykker på tilbake knappen med et uhel
    @Override
    public void onBackPressed() {
        // henter fra values/stings.xml for at teksten kan bli endret hvis et annet språk er benyttet når Alert boksen kommer opp
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
        // henter fra values/stings.xml for at teksten kan bli endret hvis et annet språk er benyttet når Alert boksen kommer opp
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


    // Kilde for å lagre spørsmål indikator, regnestykkene, svarene og den valgte regnestykkets om vises når telefonen roteres
    // https://www.youtube.com/watch?v=TcTgbVudLyQ&t=118s
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("tall", tall);
        outState.putStringArrayList("arrayspørsmål", arraySpørsmål);
        outState.putIntegerArrayList("arraysvar", arraySvar);
        outState.putInt("posisjon", i);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // viser samme nr på spørsmålet når mobil roteres
        tall = savedInstanceState.getInt("tall");
        spørsmåltall.setText(String.valueOf(tall));

        // listen av spørsmålet, svarene og posisjonen av spørsmål og svar lagres
        arraySpørsmål = savedInstanceState.getStringArrayList("arrayspørsmål");
        arraySvar = savedInstanceState.getStringArrayList("arraysvar");
        i  = savedInstanceState.getInt("posisjon");

        // stykket blir vist
        spørsmålstykke.setText(String.valueOf(arraySpørsmål.get(i)));

    }
}