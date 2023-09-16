package com.example.mappe1;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.security.AccessControlContext;

public class Velgregnestykker extends AppCompatActivity {

    public static final String SharedPref = "sharedpref";
    public static final String TALL1 = "tall1";
    public static final String TALL2 = "tall2";
    public static final String TALL3 = "tall3";

    public static final String VALGT = "valgt";

    private int tall1, tall2, tall3;

    public int valgt =5;

    Button regn5,regn10, regn15;

    int bakgrunn5, bakgrunn10, bakgrunn15 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velgregnestykker);

        // knapper for valg av antall
        regn5 = findViewById(R.id.tall5);
        regn10 = findViewById(R.id.tall10);
        regn15 = findViewById(R.id.tall15);

        // defualt farger når appen starter
        bakgrunn5 = ResourcesCompat.getColor(getResources(), R.color.orange, null);
        bakgrunn10 = ResourcesCompat.getColor(getResources(), R.color.defualt, null);
        bakgrunn15 = ResourcesCompat.getColor(getResources(), R.color.defualt, null);


        // når 5 blir valgt
        regn5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // farger blir reset om det ble valgt noe annet tidligere (10 eller 15)
                bakgrunn5 = ResourcesCompat.getColor(getResources(), R.color.orange, null);
                bakgrunn10 = ResourcesCompat.getColor(getResources(), R.color.defualt, null);
                bakgrunn15 = ResourcesCompat.getColor(getResources(), R.color.defualt, null);
                // endrer fargen på knappen
                regn5.setBackgroundColor(bakgrunn5);
                regn10.setBackgroundColor(bakgrunn10);
                regn15.setBackgroundColor(bakgrunn15);
                // oppdaterer valgt variablen
                valgt=5;
                //lagrer dataene
                lagreData();

            }
        });

        regn10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // farger blir reset om det ble valgt noe annet tidligere (5 eller 15)
                bakgrunn5 = ResourcesCompat.getColor(getResources(), R.color.defualt, null);
                bakgrunn10 = ResourcesCompat.getColor(getResources(), R.color.orange, null);
                bakgrunn15 = ResourcesCompat.getColor(getResources(), R.color.defualt, null);
                // endrer fargen på knappen
                regn5.setBackgroundColor(bakgrunn5);
                regn10.setBackgroundColor(bakgrunn10);
                regn15.setBackgroundColor(bakgrunn15);
                // oppdaterer valgt variablen
                valgt=10;
                //lagrer dataene
                lagreData();

            }
        });

        regn15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // farger blir reset om det ble valgt noe annet tidligere (5 eller 10)
                bakgrunn5 = ResourcesCompat.getColor(getResources(), R.color.defualt, null);
                bakgrunn10 = ResourcesCompat.getColor(getResources(), R.color.defualt, null);
                bakgrunn15 = ResourcesCompat.getColor(getResources(), R.color.orange, null);
                // endrer fargen på knappen
                regn5.setBackgroundColor(bakgrunn5);
                regn10.setBackgroundColor(bakgrunn10);
                regn15.setBackgroundColor(bakgrunn15);
                // oppdaterer valgt variablen
                valgt=15;
                //lagrer dataene
                lagreData();

            }
        });
        // kaller på funksjonen for å vise endring når man går og kommer tilbake til siden
        visData();

    }

    // Lagrer dataene via sharedprefernces
    public void lagreData() {
        // lager editor for å lagre variabler
        SharedPreferences preferences = getSharedPreferences(SharedPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        // lagrer det vi vil ha (farge på knapper og hvilket antall som er valgt)
        editor.putInt(TALL1, bakgrunn5);
        editor.putInt(TALL2, bakgrunn10);
        editor.putInt(TALL3, bakgrunn15);
        editor.putInt(VALGT, valgt);

        editor.apply();
        // sender brukeren en melding at dataen er lagret på alle språk
        String dataSaved = getResources().getString(R.string.dataLagret);
        Toast.makeText(this, dataSaved, Toast.LENGTH_SHORT).show();

    }

    public void visData() {
        // henter dataene som ble lagret og setter dem inn i varabler
        SharedPreferences preferences = getSharedPreferences(SharedPref, MODE_PRIVATE);
        tall1 = preferences.getInt(TALL1, 0);
        tall2 = preferences.getInt(TALL2, 0);
        tall3 = preferences.getInt(TALL3, 0);

        regn5.setBackgroundColor(tall1);
        regn10.setBackgroundColor(tall2);
        regn15.setBackgroundColor(tall3);

    }
}