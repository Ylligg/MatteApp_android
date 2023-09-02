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


        Button button = findViewById(R.id.byttSprokButton);

        Button tilbakeButton = findViewById(R.id.tilbake);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSprok();
            }
        });

        tilbakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });
    }

    public void openSprok() {
        Intent intent = new Intent(Preferanser.this, Sprok.class);
        startActivity(intent);
    }

    public void openHome() {
        Intent intent = new Intent(Preferanser.this, MainActivity.class);
        startActivity(intent);
    }
}