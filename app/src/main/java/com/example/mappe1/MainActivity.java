package com.example.mappe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button prefButton = findViewById(R.id.prefbutton);

        Button omSpillButton = findViewById(R.id.omSpill);

        prefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPref();
            }
        });

        omSpillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOmSpill();
            }
        });
    }

    public void openPref() {
        Intent intent = new Intent(MainActivity.this, Preferanser.class);
        startActivity(intent);
    }

    public void openOmSpill() {
        Intent intent = new Intent(MainActivity.this, OmSpillet.class);
        startActivity(intent);
    }
}