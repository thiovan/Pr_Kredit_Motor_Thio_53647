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
        spec = tabHost.newTabSpec("Home")
                .setIndicator("", res.getDrawable(R.drawable.ic_tab_home))
                .setContent(intent);
        tabHost.addTab(spec);

        // Melakukan kepada tab yang lain
        intent = new Intent().setClass(this, TransactionActivity.class);
        spec = tabHost.newTabSpec("Transaction")
                .setIndicator("", res.getDrawable(R.drawable.ic_tab_tansaksi))
                .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, AboutActivity.class);
        spec = tabHost.newTabSpec("About")
                .setIndicator("", res.getDrawable(R.drawable.ic_tab_about))
                .setContent(intent);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(3);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                changeTabBgColor(tabHost);
            }
        });

        changeTabBgColor(tabHost);

    }

    public void changeTabBgColor(TabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.red_700));
        }

        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(getResources().getColor(R.color.red_500));
    }
}