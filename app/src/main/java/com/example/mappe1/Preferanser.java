package com.example.mappe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Preferanser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferanser);
        // deklarerer knappene
        Button byttButton = findViewById(R.id.byttSprokButton);
        Button antallButton = findViewById(R.id.antallButton);
        // når antallButton er trykket så kaller vi på openAntall
        antallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAntall();
            }
        });
        // når byttButton er trykket så kaller vi på openSprok
        byttButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSprok();
            }
        });
    }

    public void openSprok() {
        // bytter aktivitet til Sprok
        Intent intent = new Intent(Preferanser.this, Sprok.class);
        startActivity(intent);
    }

    public void openAntall() {
        // bytter aktivitet til Velgregnestykker
        Intent intent = new Intent(Preferanser.this, Velgregnestykker.class);
        startActivity(intent);
    }

}