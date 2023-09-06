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

    private int tall1;
    private int tall2;
    private int tall3;

    Button regn5;
    Button regn10;
    Button regn15;

    int bakgrunn5;

    int bakgrunn10;

    int bakgrunn15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velgregnestykker);


        Button tilbakeButton = findViewById(R.id.tilbake);
        regn5 = findViewById(R.id.tall5);
        regn10 = findViewById(R.id.tall10);
        regn15 = findViewById(R.id.tall15);

        regn5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                    bakgrunn5 = ResourcesCompat.getColor(getResources(), R.color.orange, null);
                    bakgrunn10 = ResourcesCompat.getColor(getResources(), R.color.defualt, null);
                    bakgrunn15 = ResourcesCompat.getColor(getResources(), R.color.defualt, null);

                    regn5.setBackgroundColor(bakgrunn5);
                    regn10.setBackgroundColor(bakgrunn10);
                    regn15.setBackgroundColor(bakgrunn15);

                    saveData();



            }
        });

        regn10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                    bakgrunn5 = ResourcesCompat.getColor(getResources(), R.color.defualt, null);
                    bakgrunn10 = ResourcesCompat.getColor(getResources(), R.color.orange, null);
                    bakgrunn15 = ResourcesCompat.getColor(getResources(), R.color.defualt, null);

                    regn5.setBackgroundColor(bakgrunn5);
                    regn10.setBackgroundColor(bakgrunn10);
                    regn15.setBackgroundColor(bakgrunn15);

                    saveData();


            }
        });

        regn15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                    bakgrunn5 = ResourcesCompat.getColor(getResources(), R.color.defualt, null);
                    bakgrunn10 = ResourcesCompat.getColor(getResources(), R.color.defualt, null);
                    bakgrunn15 = ResourcesCompat.getColor(getResources(), R.color.orange, null);

                    regn5.setBackgroundColor(bakgrunn5);
                    regn10.setBackgroundColor(bakgrunn10);
                    regn15.setBackgroundColor(bakgrunn15);

                    saveData();
            }



        });

        loadData();
        updateViews();



        tilbakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });


    }
    public void openHome() {
        Intent intent = new Intent(Velgregnestykker.this, Preferanser.class);
        startActivity(intent);
    }

    public void saveData() {
        SharedPreferences preferences = getSharedPreferences(SharedPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(TALL1, bakgrunn5);
        editor.putInt(TALL2, bakgrunn10);
        editor.putInt(TALL3, bakgrunn15);

        editor.apply();
        Toast.makeText(this, "DATA SAVED", Toast.LENGTH_SHORT).show();

    }

    public void loadData() {
        SharedPreferences preferences = getSharedPreferences(SharedPref, MODE_PRIVATE);
        tall1 = preferences.getInt(TALL1, 0);
        tall2 = preferences.getInt(TALL2, 0);
        tall3 = preferences.getInt(TALL3, 0);


    }

    public void updateViews() {
       regn5.setBackgroundColor(tall1);
       regn10.setBackgroundColor(tall2);
       regn15.setBackgroundColor(tall3);
    }





}