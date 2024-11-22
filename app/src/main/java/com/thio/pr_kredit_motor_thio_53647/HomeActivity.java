package com.thio.pr_kredit_motor_thio_53647;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button btDataPetugasHome, btDataMotorHome, btDataKreditorHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btDataPetugasHome = (Button) findViewById(R.id.btDataPetugasHome);
        btDataMotorHome = (Button) findViewById(R.id.btDataMotorHome);
        btDataKreditorHome = (Button) findViewById(R.id.btDataKreditorHome);

        btDataPetugasHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KlikbtDataPetugasHome();
            }
        });

        btDataMotorHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KlikbtDataMotorHome();
            }
        });

        btDataKreditorHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KlikbtDataKreditorHome();
            }
        });

    }

    public void KlikbtDataPetugasHome() {
        Intent i = new Intent(getApplicationContext(),
                DataPetugasActivity.class); //target = nama class
        startActivity(i);
    }

    public void KlikbtDataMotorHome() {
        Intent i = new Intent(getApplicationContext(),
                DataMotorActivity.class); //target = nama class
        startActivity(i);
    }

    public void KlikbtDataKreditorHome() {
        Intent i = new Intent(getApplicationContext(),
                DataKreditorActivity.class); //target = nama class
        startActivity(i);
    }

}