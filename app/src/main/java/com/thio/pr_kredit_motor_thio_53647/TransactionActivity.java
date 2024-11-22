package com.thio.pr_kredit_motor_thio_53647;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class TransactionActivity extends AppCompatActivity {

    Button btPengajuanKredit, btPembayaranAngsuran, btDataPengajuanKredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        btPengajuanKredit = (Button) findViewById(R.id.btPengajuanKredit);
        btPembayaranAngsuran = (Button) findViewById(R.id.btPembayaranAngsuran);
        btDataPengajuanKredit = (Button) findViewById(R.id.btDataPengajuanKredit);

        btPengajuanKredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KlikbtPengajuanKredit();
            }
        });

        btPembayaranAngsuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KlikbtPembayaranAngsuran();
            }
        });

        btDataPengajuanKredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KlikbtDataPengajuanKredit();
            }
        });

    }

    public void KlikbtPengajuanKredit() {
        Intent i = new Intent(getApplicationContext(), PengajuanKreditActivity.class);//target = nama class
        startActivity(i);
    }

    public void KlikbtPembayaranAngsuran() {
        Intent i = new Intent(getApplicationContext(),
                PembayaranAngsuranActivity.class);//target = nama class
        startActivity(i);
    }

    public void KlikbtDataPengajuanKredit() {
        Intent i = new Intent(getApplicationContext(),
                DataPengajuanKreditActivity.class);//target = nama class
        startActivity(i);
    }


}