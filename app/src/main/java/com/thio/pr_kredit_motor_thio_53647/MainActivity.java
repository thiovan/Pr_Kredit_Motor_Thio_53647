package com.thio.pr_kredit_motor_thio_53647;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources(); // Resource object untuk mendapatkan gambar
        TabHost tabHost = getTabHost(); // activity TabHost
        TabHost.TabSpec spec; // menggunakan TabSpec untuk tab lain
        Intent intent; // Menggunakan Intent untuk tab yg lain

        // Membuat intent digunakan untuk menampilkan activity ke dalam Tab yg
        intent = new Intent().setClass(this, HomeActivity.class);

        // inisialisasi TabSpec untuk tab yg lain dan menambahkan ke TabHost
        spec = tabHost.newTabSpec("Home").setIndicator("Home" ).setContent(intent);
        tabHost.addTab(spec);

        // Melakukan kepada tab yang lain
        intent = new Intent().setClass(this, TransactionActivity.class);
        spec = tabHost.newTabSpec("Transaction").setIndicator("Transaction")
                .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, AboutActivity.class);
        spec = tabHost.newTabSpec("About").setIndicator("About")
                .setContent(intent);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(3);

    }
}