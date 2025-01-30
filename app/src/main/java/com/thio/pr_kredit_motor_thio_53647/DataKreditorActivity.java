package com.thio.pr_kredit_motor_thio_53647;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataKreditorActivity extends AppCompatActivity implements OnClickListener {

    Kreditor kreditor = new Kreditor();
    TableLayout tbKreditor;
    Button btTambahKreditor, btRefreshDataKreditor;
    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();
    JSONArray arrayKreditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kreditor);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Pemberian Nama komponen
        tbKreditor = (TableLayout) findViewById(R.id.tbKreditor);
        btTambahKreditor = (Button) findViewById(R.id.btTambahKreditor);
        btRefreshDataKreditor = (Button) findViewById(R.id.btRefreshDataKreditor);
        tampildataKreditor();
        
    }

    private void tampildataKreditor() {
        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.BLACK);

        //Memberi ID Header Tabel
        TextView viewHeaderId = new TextView(this);
        TextView viewHeaderNama = new TextView(this);
        TextView viewHeaderPekerjaan = new TextView(this);
        TextView viewHeaderTelp = new TextView(this);
        TextView viewHeaderAlamat = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        //Memberi Nama kolom HEADER
        viewHeaderId.setText("Id");
        viewHeaderNama.setText("Nama");
        viewHeaderPekerjaan.setText("Pekerjaan");
        viewHeaderTelp.setText("Telepon");
        viewHeaderAlamat.setText("Alamat");
        viewHeaderAction.setText("Action");
        
        viewHeaderNama.setTextColor(Color.WHITE);
        viewHeaderPekerjaan.setTextColor(Color.WHITE);
        viewHeaderTelp.setTextColor(Color.WHITE);
        viewHeaderAlamat.setTextColor(Color.WHITE);
        viewHeaderAction.setTextColor(Color.WHITE);
        
        viewHeaderNama.setPadding(5, 1, 5, 1);
        viewHeaderPekerjaan.setPadding(5, 1, 5, 1);
        viewHeaderTelp.setPadding(5, 1, 5, 1);
        viewHeaderAlamat.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);
        
        barisTabel.addView(viewHeaderNama);
        barisTabel.addView(viewHeaderPekerjaan);
        barisTabel.addView(viewHeaderTelp);
        barisTabel.addView(viewHeaderAlamat);
        barisTabel.addView(viewHeaderAction);

        tbKreditor.addView(barisTabel, new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        try {
            arrayKreditor = new JSONArray(kreditor.tampilKreditor());
            //Menampilkan Header Kolom
            for (int i = 0; i < arrayKreditor.length(); i++) {
                JSONObject jsonChildNode = arrayKreditor.getJSONObject(i);
                //ambil data dari nama tabel databse
                String idkreditor = jsonChildNode.optString("idkreditor");
                String nama = jsonChildNode.optString("nama");
                String pekerjaan = jsonChildNode.optString("pekerjaan");
                String telp = jsonChildNode.optString("telp");
                String alamat = jsonChildNode.optString("alamat");

                System.out.println("idkreditor :" + idkreditor);
                System.out.println("nama :" + nama);
                System.out.println("pekerjaan :" + pekerjaan);
                System.out.println("telp :" + telp);
                System.out.println("alamat :" + alamat);

                barisTabel = new TableRow(this);

                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }

                TextView viewNama = new TextView(this);
                viewNama.setText(nama);
                viewNama.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewNama);

                TextView viewPekerjaan = new TextView(this);
                viewPekerjaan.setText(pekerjaan);
                viewPekerjaan.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewPekerjaan);

                TextView viewTelp = new TextView(this);
                viewTelp.setText(telp);
                viewTelp.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewTelp);

                TextView viewAlamat = new TextView(this);
                viewAlamat.setText(alamat);
                viewAlamat.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewAlamat);

                //Membuat Button Edit pada Baris
                buttonEdit.add(i, new MaterialButton(this));
                buttonEdit.get(i).setId(Integer.parseInt(idkreditor));
                buttonEdit.get(i).setTag("Edit");
                buttonEdit.get(i).setText("Edit");
                buttonEdit.get(i).setOnClickListener(this);
                buttonEdit.get(i).setBackgroundColor(getColor(R.color.green_700));
                barisTabel.addView(buttonEdit.get(i));

                //Membuat Button Delete pada Baris
                buttonDelete.add(i, new MaterialButton(this));
                buttonDelete.get(i).setId(Integer.parseInt(idkreditor));
                buttonDelete.get(i).setTag("Delete");
                buttonDelete.get(i).setText("Delete");
                buttonDelete.get(i).setOnClickListener(this);
                buttonDelete.get(i).setBackgroundColor(getColor(R.color.red_700));
                barisTabel.addView(buttonDelete.get(i));

                tbKreditor.addView(barisTabel, new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void tambahPetugas() {
        /* layout akan ditampilkan pada AlertDialog */
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setPadding(56, 56,56,56);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editNama = new EditText(this);
        editNama.setCompoundDrawablesWithIntrinsicBounds(R.drawable.data_petugas,0,0,0);
        editNama.setCompoundDrawablePadding(16);
        editNama.setHint("Nama");
        layoutInput.addView(editNama);

        final EditText editPekerjaan = new EditText(this);
        editPekerjaan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.jabatan,0,0,0);
        editPekerjaan.setCompoundDrawablePadding(16);
        editPekerjaan.setHint("Pekerjaan");
        layoutInput.addView(editPekerjaan);

        final EditText editTelp = new EditText(this);
        editTelp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.number,0,0,0);
        editTelp.setCompoundDrawablePadding(16);
        editTelp.setHint("Telepon");
        layoutInput.addView(editTelp);

        final EditText editAlamat = new EditText(this);
        editAlamat.setCompoundDrawablesWithIntrinsicBounds(R.drawable.data_petugas,0,0,0);
        editAlamat.setCompoundDrawablePadding(16);
        editAlamat.setHint("Alamat");
        layoutInput.addView(editAlamat);
        
        AlertDialog.Builder builderInsertKreditor = new AlertDialog.Builder(this);
        builderInsertKreditor.setIcon(R.drawable.add_data);
        builderInsertKreditor.setTitle("TAMBAH KREDITOR");
        builderInsertKreditor.setView(layoutInput);

        builderInsertKreditor.setPositiveButton("Insert", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nama = editNama.getText().toString();
                        String pekerjaan = editPekerjaan.getText().toString();
                        String telp = editTelp.getText().toString();
                        String alamat = editAlamat.getText().toString();

                        System.out.println("Nama : " + nama + " Pekerjaan : " + pekerjaan + " Telepon : " + telp + " Alamat : " + alamat);
                        String laporan = kreditor.insertKreditor(nama, pekerjaan, telp, alamat);
                        Toast.makeText(DataKreditorActivity.this, laporan, Toast.LENGTH_SHORT).show();

                        /* restart acrtivity */
                        finish();
                        startActivity(getIntent());
                    }
                });

        builderInsertKreditor.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertKreditor.show();
    }

    //Ambil data kreditor bersdasarkan Id
    public void getKreditorByIdKreditor(int idkreditor) {
        String idkreditorEdit = null;
        String namaEdit = null;
        String pekerjaanEdit = null;
        String telpEdit = null;
        String alamatEdit = null;

        JSONArray arrayPersonal;

        try {

            arrayPersonal = new JSONArray(kreditor.getKreditorByIdKreditor(idkreditor));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                idkreditorEdit = jsonChildNode.optString("idkreditor");
                namaEdit = jsonChildNode.optString("nama");
                pekerjaanEdit = jsonChildNode.optString("pekerjaan");
                telpEdit = jsonChildNode.optString("telp");
                alamatEdit = jsonChildNode.optString("alamat");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setPadding(56, 56,56,56);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        // buat id tersembunyi di alertbuilder
        final TextView viewIdKreditor = new TextView(this);
        viewIdKreditor.setText(String.valueOf(idkreditor));
        viewIdKreditor.setBackgroundColor(Color.TRANSPARENT);
        viewIdKreditor.setTextColor(Color.WHITE);
        viewIdKreditor.setTextSize(20);
        viewIdKreditor.setVisibility(View.GONE);
        layoutInput.addView(viewIdKreditor);

        //membuat edit text di Allert builder
        final EditText editNama = new EditText(this);
        editNama.setCompoundDrawablesWithIntrinsicBounds(R.drawable.data_petugas,0,0,0);
        editNama.setCompoundDrawablePadding(16);
        editNama.setText(namaEdit);
        layoutInput.addView(editNama);

        //membuat edit text di Allert builder
        final EditText editPekerjaan = new EditText(this);
        editPekerjaan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.jabatan,0,0,0);
        editPekerjaan.setCompoundDrawablePadding(16);
        editPekerjaan.setText(pekerjaanEdit);
        layoutInput.addView(editPekerjaan);

        //membuat edit text di Allert builder
        final EditText editTelp = new EditText(this);
        editTelp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.number,0,0,0);
        editTelp.setCompoundDrawablePadding(16);
        editTelp.setText(telpEdit);
        layoutInput.addView(editTelp);

        //membuat edit text di Allert builder
        final EditText editAlamat = new EditText(this);
        editAlamat.setCompoundDrawablesWithIntrinsicBounds(R.drawable.data_petugas,0,0,0);
        editAlamat.setCompoundDrawablePadding(16);
        editAlamat.setText(alamatEdit);
        layoutInput.addView(editAlamat);

        AlertDialog.Builder builderEditKreditor = new AlertDialog.Builder(this);
        builderEditKreditor.setIcon(R.drawable.edit_data);
        builderEditKreditor.setTitle("EDIT KREDITOR");
        builderEditKreditor.setView(layoutInput);
        builderEditKreditor.setPositiveButton("Update", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String idkreditor = viewIdKreditor.getText().toString();
                String nama = editNama.getText().toString();
                String pekerjaan = editPekerjaan.getText().toString();
                String telp = editTelp.getText().toString();
                String alamat = editAlamat.getText().toString();

                System.out.println("IdKreditor : " + idkreditor + " Nama :" + nama + " Pekerjaan: " + pekerjaan + " Telp :" + telp + " Alamat :" + alamat);

                String laporan = kreditor.updateKreditor(idkreditor, nama, pekerjaan, telp, alamat);
                Toast.makeText(DataKreditorActivity.this, laporan, Toast.LENGTH_SHORT).show();

                /* restart acrtivity */
                finish();
                startActivity(getIntent());
            }

        });

        //membuat Button Cancel pada builder
        builderEditKreditor.setNegativeButton("Cancel", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builderEditKreditor.show();
    }

    //Hapus Kreditor bersadarkan ID
    public void deleteKreditor(int idkreditor) {
        kreditor.deleteKreditor(idkreditor);

        /* restart acrtivity */
        finish();
        startActivity(getIntent());
    }

    public void KlikbtTambahKreditor(View view) {
        tambahPetugas();
    }

    public void KlikbtRefreshDataKreditor(View view) {
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < buttonEdit.size(); i++) {
            /* jika yang diklik adalah button edit */
            if (view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
                int idkreditor = buttonEdit.get(i).getId();
                getKreditorByIdKreditor(idkreditor);
            } /* jika yang diklik adalah button delete */ else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
                int idkreditor = buttonDelete.get(i).getId();
                deleteKreditor(idkreditor);

            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}