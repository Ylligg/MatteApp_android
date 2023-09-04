package com.example.mappe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Velgregnestykker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velgregnestykker);


        Button regn5 = findViewById(R.id.StartSpill);
        Button regn10 = findViewById(R.id.Pref);
        Button regn15 = findViewById(R.id.OmSpill);
        Button tilbakeButton = findViewById(R.id.tilbake);



        regn5.setOnClickListener(new View.OnClickListener() {
            int antall;
            @Override
            public void onClick(View view) {
                antall=5;
            }
        });

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
}