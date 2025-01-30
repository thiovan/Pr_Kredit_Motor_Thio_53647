package com.thio.pr_kredit_motor_thio_53647;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataPengajuanKreditActivity extends AppCompatActivity implements View.OnClickListener {

    //Inisialisasi Objek +  Variabel + Class
    Kredit kredit = new Kredit();
    TableLayout tbQueryKredit;
    Button btRefreshKredit;
    ArrayList<Button> buttonPdf = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();
    JSONArray arrayQueryKredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajuan_kredit);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Pemberian Nama komponen
        tbQueryKredit = (TableLayout) findViewById(R.id.tbQueryKredit);
        btRefreshKredit = (Button) findViewById(R.id.btRefreshKredit);

        tampilQueryKredit();
    }

    private void tampilQueryKredit() {
        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.BLACK);

        //Memberi ID Header Tabel
        TextView viewHeaderInvoice = new TextView(this);
        TextView viewHeaderTgl = new TextView(this);
        TextView viewHeaderIdKreditor = new TextView(this);
        TextView viewHeaderNama = new TextView(this);
        TextView viewHeaderAlamat = new TextView(this);
        TextView viewHeaderKdMotor = new TextView(this);
        TextView viewHeaderNmMotor = new TextView(this);
        TextView viewHeaderHrgTunai = new TextView(this);
        TextView viewHeaderDP = new TextView(this);
        TextView viewHeaderHrgKredit = new TextView(this);
        TextView viewHeaderBunga = new TextView(this);
        TextView viewHeaderLama = new TextView(this);
        TextView viewHeaderTotKredit = new TextView(this);
        TextView viewHeaderAngsuran = new TextView(this);

        //Memberi Nama kolom HEADER
        viewHeaderInvoice.setText("Invoice");
        viewHeaderTgl.setText("Tgl");
        viewHeaderIdKreditor.setText("IdKreditor");
        viewHeaderNama.setText("Nama");
        viewHeaderAlamat.setText("Alamat");
        viewHeaderKdMotor.setText("KdMotor");
        viewHeaderNmMotor.setText("NmMotor");
        viewHeaderHrgTunai.setText("HrgTunai");
        viewHeaderDP.setText("DP");
        viewHeaderHrgKredit.setText("HrgKredit");
        viewHeaderBunga.setText("Bunga");
        viewHeaderLama.setText("Lama");
        viewHeaderTotKredit.setText("TotKredit");
        viewHeaderAngsuran.setText("Angsuran");

        //viewHeaderId
        viewHeaderInvoice.setTextColor(Color.WHITE);
        viewHeaderTgl.setTextColor(Color.WHITE);
        viewHeaderIdKreditor.setTextColor(Color.WHITE);
        viewHeaderNama.setTextColor(Color.WHITE);
        viewHeaderAlamat.setTextColor(Color.WHITE);
        viewHeaderKdMotor.setTextColor(Color.WHITE);
        viewHeaderNmMotor.setTextColor(Color.WHITE);
        viewHeaderHrgTunai.setTextColor(Color.WHITE);
        viewHeaderDP.setTextColor(Color.WHITE);
        viewHeaderHrgKredit.setTextColor(Color.WHITE);
        viewHeaderBunga.setTextColor(Color.WHITE);
        viewHeaderLama.setTextColor(Color.WHITE);
        viewHeaderTotKredit.setTextColor(Color.WHITE);
        viewHeaderAngsuran.setTextColor(Color.WHITE);

        viewHeaderInvoice.setPadding(5, 1, 5, 1);
        viewHeaderTgl.setPadding(5, 1, 5, 1);
        viewHeaderIdKreditor.setPadding(5, 1, 5, 1);
        viewHeaderNama.setPadding(5, 1, 5, 1);
        viewHeaderAlamat.setPadding(5, 1, 5, 1);
        viewHeaderKdMotor.setPadding(5, 1, 5, 1);
        viewHeaderNmMotor.setPadding(5, 1, 5, 1);
        viewHeaderHrgTunai.setPadding(5, 1, 5, 1);
        viewHeaderDP.setPadding(5, 1, 5, 1);
        viewHeaderHrgKredit.setPadding(5, 1, 5, 1);
        viewHeaderBunga.setPadding(5, 1, 5, 1);
        viewHeaderLama.setPadding(5, 1, 5, 1);
        viewHeaderTotKredit.setPadding(5, 1, 5, 1);
        viewHeaderAngsuran.setPadding(5, 1, 5, 1);

        barisTabel.addView(viewHeaderInvoice);
        barisTabel.addView(viewHeaderTgl);
        barisTabel.addView(viewHeaderIdKreditor);
        barisTabel.addView(viewHeaderNama);
        barisTabel.addView(viewHeaderAlamat);
        barisTabel.addView(viewHeaderKdMotor);
        barisTabel.addView(viewHeaderNmMotor);
        barisTabel.addView(viewHeaderHrgTunai);
        barisTabel.addView(viewHeaderDP);
        barisTabel.addView(viewHeaderHrgKredit);
        barisTabel.addView(viewHeaderBunga);
        barisTabel.addView(viewHeaderLama);
        barisTabel.addView(viewHeaderTotKredit);
        barisTabel.addView(viewHeaderAngsuran);

        tbQueryKredit.addView(barisTabel, new
                TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

        try {
            arrayQueryKredit = new JSONArray(kredit.tampil_query_kredit());

            //Menampilkan Header Kolom
            for (int i = 0; i < arrayQueryKredit.length(); i++) {
                JSONObject jsonChildNode = arrayQueryKredit.getJSONObject(i);
                //ambil data dari nama tabel databse
                String invoice = jsonChildNode.optString("invoice");
                String tanggal = jsonChildNode.optString("tanggal");
                String idkreditor = jsonChildNode.optString("idkreditor");
                String nama = jsonChildNode.optString("nama");
                String alamat = jsonChildNode.optString("alamat");
                String kdmotor = jsonChildNode.optString("kdmotor");
                String nmotor = jsonChildNode.optString("nmotor");
                String hrgtunai = jsonChildNode.optString("hrgtunai");
                String dp = jsonChildNode.optString("dp");
                String hrgkredit = jsonChildNode.optString("hrgkredit");
                String bunga = jsonChildNode.optString("bunga");
                String lama = jsonChildNode.optString("lama");
                String totalkredit = jsonChildNode.optString("totalkredit");
                String angsuran = jsonChildNode.optString("angsuran");
                System.out.println("invoice :" + invoice);


                barisTabel = new TableRow(this);

                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }
                TextView viewInvoice = new TextView(this);
                viewInvoice.setText(invoice);
                viewInvoice.setPadding(5, 1, 5, 1);
                viewInvoice.setGravity(Gravity.CENTER);
                barisTabel.addView(viewInvoice);
                TextView viewTgl = new TextView(this);
                viewTgl.setText(tanggal);
                viewTgl.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewTgl);
                TextView viewIdKreditor = new TextView(this);
                viewIdKreditor.setText(idkreditor);
                viewIdKreditor.setPadding(5, 1, 5, 1);
                viewIdKreditor.setGravity(Gravity.CENTER);
                barisTabel.addView(viewIdKreditor);

                TextView viewviewNama = new TextView(this);
                viewviewNama.setText(nama);
                viewviewNama.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewviewNama);
                TextView viewAlamat = new TextView(this);
                viewAlamat.setText(alamat);
                viewAlamat.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewAlamat);
                TextView viewKdMotor = new TextView(this);
                viewKdMotor.setText(kdmotor);
                viewKdMotor.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewKdMotor);
                TextView viewNmMotor = new TextView(this);
                viewNmMotor.setText(nmotor);
                viewNmMotor.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewNmMotor);
                TextView viewHrgTunai = new TextView(this);
                viewHrgTunai.setText(hrgtunai);
                viewHrgTunai.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewHrgTunai);
                TextView viewDP = new TextView(this);
                viewDP.setText(dp);
                viewDP.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewDP);
                TextView viewHrgKredit = new TextView(this);
                viewHrgKredit.setText(hrgkredit);
                viewHrgKredit.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewHrgKredit);
                TextView viewBunga = new TextView(this);
                viewBunga.setText(bunga);
                viewBunga.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewBunga);
                TextView viewLama = new TextView(this);
                viewLama.setText(lama);
                viewLama.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewLama);
                TextView viewTotKredit = new TextView(this);
                viewTotKredit.setText(totalkredit);
                viewTotKredit.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewTotKredit);
                TextView viewAngsuran = new TextView(this);
                viewAngsuran.setText(angsuran);
                viewAngsuran.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewAngsuran);

                //Membuat Button Edit pada Baris
                buttonPdf.add(i, new MaterialButton(this));
                buttonPdf.get(i).setId(Integer.parseInt(invoice));
                buttonPdf.get(i).setTag("PDF");
                buttonPdf.get(i).setText("PDF");
                buttonPdf.get(i).setOnClickListener(this);
                buttonPdf.get(i).setBackgroundColor(getColor(R.color.green_700));
                barisTabel.addView(buttonPdf.get(i));

                //Membuat Button Delete pada Baris
                buttonDelete.add(i, new MaterialButton(this));
                buttonDelete.get(i).setId(Integer.parseInt(invoice));
                buttonDelete.get(i).setTag("Delete");
                buttonDelete.get(i).setText("Delete");
                buttonDelete.get(i).setOnClickListener(this);
                buttonDelete.get(i).setBackgroundColor(getColor(R.color.red_700));
                barisTabel.addView(buttonDelete.get(i));

                tbQueryKredit.addView(barisTabel, new
                        TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void deleteKredit(int invoice) {
        kredit.hapus_kredit(invoice);

        /* restart acrtivity */
        finish();
        startActivity(getIntent());
    }


    public void KlikbtRefreshKredit(View view) {
        /* restart acrtivity */
        finish();
        startActivity(getIntent());
    }


    @Override
    public void onClick(View view) {
        for (int i = 0; i < buttonPdf.size(); i++) {

            /* jika yang diklik adalah button edit */
            if (view.getId() == buttonPdf.get(i).getId() && view.getTag().toString().trim().equals("PDF")) {
                System.out.println("pdf");
                int invoice = buttonPdf.get(i).getId();
                Server server = new Server();
                String pdfUrl = "http://" + server.urlServer + "/jskreditmotor/report.php?invoice=" + invoice;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdfUrl));
                startActivity(browserIntent);

            } /* jika yang diklik adalah button delete */ else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
                int invoice = buttonDelete.get(i).getId();
                deleteKredit(invoice);

            }
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}