package com.thio.pr_kredit_motor_thio_53647;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class DataMotorActivity extends AppCompatActivity implements OnClickListener {
    //Inisialisasi Objek + Variabel + Class
    Motor motor = new Motor();
    TableLayout tbMotor;
    Button btTambahMotor, btRefreshDataMotor;
    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();
    JSONArray arrayMotor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_motor);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Pemberian Nama komponen
        tbMotor = (TableLayout) findViewById(R.id.tbMotor);
        btTambahMotor = (Button) findViewById(R.id.btTambahMotor);
        btRefreshDataMotor = (Button) findViewById(R.id.btRefreshDataMotor);

        tampildataMotor();

    }

    //Metode KlikbtTambahMotor
    public void KlikbtTambahMotor(View v) {
        tambahMotor();
    }

    //Refresh Data
    public void KlikbtRefreshDataMotor(View v) {
        /* restart acrtivity */
        finish();
        startActivity(getIntent());
    }

    //Tampil data data motor
    public void tampildataMotor() {
        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.BLACK);
        //Memberi ID Header Tabel
        TextView viewHeaderId = new TextView(this);
        TextView viewHeaderKdMotor = new TextView(this);
        TextView viewHeaderNama = new TextView(this);
        TextView viewHeaderHarga = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        //Memberi Nama kolom HEADER
        viewHeaderId.setText("Id");
        viewHeaderKdMotor.setText("KdMotor");
        viewHeaderNama.setText("Nama");
        viewHeaderHarga.setText("Harga");
        viewHeaderAction.setText("Action");


        viewHeaderKdMotor.setTextColor(Color.WHITE);
        viewHeaderNama.setTextColor(Color.WHITE);
        viewHeaderHarga.setTextColor(Color.WHITE);
        viewHeaderAction.setTextColor(Color.WHITE);

        viewHeaderKdMotor.setPadding(5, 1, 5, 1);
        viewHeaderNama.setPadding(5, 1, 5, 1);
        viewHeaderHarga.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        barisTabel.addView(viewHeaderKdMotor);
        barisTabel.addView(viewHeaderNama);
        barisTabel.addView(viewHeaderHarga);
        barisTabel.addView(viewHeaderAction);

        tbMotor.addView(barisTabel, new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        try {
            arrayMotor = new JSONArray(motor.tampilMotor());

            //Menampilkan Header Kolom
            for (int i = 0; i < arrayMotor.length(); i++) {
                JSONObject jsonChildNode = arrayMotor.getJSONObject(i);
                //ambil data dari nama tabel databse
                String idmotor = jsonChildNode.optString("idmotor");
                String kdmotor = jsonChildNode.optString("kdmotor");
                String nama = jsonChildNode.optString("nama");
                String harga = jsonChildNode.optString("harga");
                System.out.println("id :" + idmotor);
                System.out.println("kdmotor :" + kdmotor);
                System.out.println("nama :" + nama);
                System.out.println("harga :" + harga);

                barisTabel = new TableRow(this);

                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }

                TextView viewKdMotor = new TextView(this);
                viewKdMotor.setText(kdmotor);
                viewKdMotor.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewKdMotor);

                TextView viewNama = new TextView(this);
                viewNama.setText(nama);
                viewNama.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewNama);

                TextView viewHarga = new TextView(this);
                viewHarga.setText(harga);
                viewHarga.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewHarga);

                //Membuat Button Edit pada Baris
                buttonEdit.add(i, new MaterialButton(this));
                buttonEdit.get(i).setId(Integer.parseInt(idmotor));
                buttonEdit.get(i).setTag("Edit");
                buttonEdit.get(i).setText("Edit");
                buttonEdit.get(i).setOnClickListener(this);
                buttonEdit.get(i).setBackgroundColor(getColor(R.color.green_700));
                barisTabel.addView(buttonEdit.get(i));

                //Membuat Button Delete pada Baris
                buttonDelete.add(i, new MaterialButton(this));
                buttonDelete.get(i).setId(Integer.parseInt(idmotor));
                buttonDelete.get(i).setTag("Delete");
                buttonDelete.get(i).setText("Delete");
                buttonDelete.get(i).setOnClickListener(this);
                buttonDelete.get(i).setBackgroundColor(getColor(R.color.red_700));
                barisTabel.addView(buttonDelete.get(i));

                tbMotor.addView(barisTabel, new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Hapus Motor bersadarkan ID
    public void deleteMotor(int id) {
        motor.deleteMotor(id);

        /* restart acrtivity */
        finish();
        startActivity(getIntent());
    }

    //Ambil data motor bersdasarkan Id
    public void getMotorByKdmotor(int idmotor) {

        String idmotorEdit = null;
        String kdmotorEdit = null;
        String namaEdit = null;
        String hargaEdit = null;

        JSONArray arrayPersonal;

        try {
            arrayPersonal = new JSONArray(motor.getMotorByKdmotor(idmotor));
            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                idmotorEdit = jsonChildNode.optString("idmotor");
                kdmotorEdit = jsonChildNode.optString("kdmotor");
                namaEdit = jsonChildNode.optString("nama");
                hargaEdit = jsonChildNode.optString("harga");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setPadding(56, 56,56,56);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        // buat id tersembunyi di alertbuilder
        final TextView viewKdmotor = new TextView(this);
        viewKdmotor.setText("Kode Motor =" + String.valueOf(kdmotorEdit));
        viewKdmotor.setTextColor(Color.WHITE);
        viewKdmotor.setTextSize(20);
        viewKdmotor.setVisibility(View.GONE);
        layoutInput.addView(viewKdmotor);

        //membuat edit text di Allert builder
        final EditText editIdMotor = new EditText(this);
        editIdMotor.setText(idmotorEdit);

        //membuat edit text di Allert builder
        final EditText editNama = new EditText(this);
        editNama.setCompoundDrawablesWithIntrinsicBounds(R.drawable.data_motor,0,0,0);
        editNama.setCompoundDrawablePadding(16);
        editNama.setText(namaEdit);
        layoutInput.addView(editNama);

        //membuat edit text di Allert builder
        final EditText editHarga = new EditText(this);
        editHarga.setCompoundDrawablesWithIntrinsicBounds(R.drawable.money,0,0,0);
        editHarga.setCompoundDrawablePadding(16);
        editHarga.setText(hargaEdit);
        layoutInput.addView(editHarga);

        AlertDialog.Builder builderEditMotor = new AlertDialog.Builder(this);
        builderEditMotor.setTitle("EDIT MOTOR");
        builderEditMotor.setView(layoutInput);
        builderEditMotor.setPositiveButton("Update", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String idmotor = editIdMotor.getText().toString();
                        String kdmotor = viewKdmotor.getText().toString();
                        String nama = editNama.getText().toString();
                        String harga = editHarga.getText().toString();
                        System.out.println("IdMotor :" + idmotor + " KdMotor : " + kdmotor + " Nama : " + nama + " Harga : " + harga);

                        String laporan = motor.updateMotor(editIdMotor.getText().toString(), viewKdmotor.getText().toString(), editNama.getText().toString(), editHarga.getText().toString());

                        Toast.makeText(DataMotorActivity.this, laporan, Toast.LENGTH_SHORT).show();

                        finish();
                        startActivity(getIntent());
                    }

                });
        //membuat Button Cancel pada builder
        builderEditMotor.setNegativeButton("Cancel", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builderEditMotor.show();
    }


    //Metode tambah Motor
    public void tambahMotor() {
        /* layout akan ditampilkan pada AlertDialog */
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setPadding(56, 56,56,56);
        layoutInput.setOrientation(LinearLayout.VERTICAL);
        final EditText editKdMotor = new EditText(this);
        editKdMotor.setCompoundDrawablesWithIntrinsicBounds(R.drawable.number,0,0,0);
        editKdMotor.setCompoundDrawablePadding(16);
        editKdMotor.setHint("KdMotor");
        layoutInput.addView(editKdMotor);

        final EditText editNama = new EditText(this);
        editNama.setCompoundDrawablesWithIntrinsicBounds(R.drawable.data_motor,0,0,0);
        editNama.setCompoundDrawablePadding(16);
        editNama.setHint("Nama");
        layoutInput.addView(editNama);

        final EditText editHarga = new EditText(this);
        editHarga.setCompoundDrawablesWithIntrinsicBounds(R.drawable.money,0,0,0);
        editHarga.setCompoundDrawablePadding(16);
        editHarga.setHint("harga");
        layoutInput.addView(editHarga);

        AlertDialog.Builder builderInsertMotor = new AlertDialog.Builder(this);
        builderInsertMotor.setIcon(R.drawable.add_data);
        builderInsertMotor.setTitle("TAMBAH MOTOR");
        builderInsertMotor.setView(layoutInput);
        builderInsertMotor.setPositiveButton("Insert", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String kdmotor = editKdMotor.getText().toString();
                        String nama = editNama.getText().toString();
                        String harga = editHarga.getText().toString();
                        System.out.println("KdMotor : " + kdmotor + " Nama : " + nama + " Harga : " + harga);
                        String laporan = motor.insertMotor(kdmotor, nama, harga);
                        Toast.makeText(DataMotorActivity.this, laporan, Toast.LENGTH_SHORT).show();

                        /* restart acrtivity */
                        finish();
                        startActivity(getIntent());
                    }
                });

        builderInsertMotor.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertMotor.show();
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < buttonEdit.size(); i++) {

            /* jika yang diklik adalah button edit */
            if (view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
                int idmotor = buttonEdit.get(i).getId();
                getMotorByKdmotor(idmotor);

            } else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
                /* jika yang diklik adalah button delete */
                int idmotor = buttonDelete.get(i).getId();
                deleteMotor(idmotor);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}