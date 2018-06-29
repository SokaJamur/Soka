package com.reppernews.sokajamur;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.reppernews.sokajamur.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.reppernews.sokajamur.PesananSaya.TAG_ID_PESAN;


public class DetailPesanan extends AppCompatActivity {
    TextView txtNama, txtAlamat, txtNamaBarang, txtTotal, txttglKirim, txtStatus, txtPembayaran, txtJumlah;
    private String url = Server.URL + "detailPesanan.php";
    private String url2 = Server.URL + "batalPesanan.php";
    String tag_json_obj = "json_obj_req";
    public static final String TAG_ID_PESAN2 = "id_pesan";
    public static final String TAG_NAMA_PESAN = "nama";
    public static final String TAG_NAMA_BARANG_PESAN = "nama_barang";
    public static final String TAG_JMLPESAN = "jumlah_pesanan";
    public static final String TAG_TGLKIMIR = "tgl_kirim";
    public static final String TAG_METODE = "metode";
    public static final String TAG_TOTAL_PESAN = "total";
    public static final String TAG_STATUS = "status";
    public static final String TAG_ALAMAT = "alamat";
    ConnectivityManager conMgr;
    Button btnbatal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);
        txtNama = (TextView) findViewById(R.id.textView30);
        txtAlamat = (TextView) findViewById(R.id.textView32);
        txtNamaBarang = (TextView) findViewById(R.id.textView40);
        txtJumlah = (TextView) findViewById(R.id.textView44);
        txtTotal = (TextView) findViewById(R.id.textView48);
        txttglKirim = (TextView) findViewById(R.id.textView46);
        txtStatus = (TextView) findViewById(R.id.textView49);
        txtPembayaran = (TextView) findViewById(R.id.textView51);
        btnbatal = (Button) findViewById(R.id.btnBatal);
        if(txtStatus.getText().toString().equals("Dibatalkan")){
            btnbatal.setVisibility(View.GONE);
        }
        else{
            btnbatal.setVisibility(View.VISIBLE);
        }
        Bundle b = getIntent().getExtras();
        final String id_pesan = b.getString(TAG_ID_PESAN);
        Toast.makeText(getApplicationContext(), "ini id pesan" + id_pesan, Toast.LENGTH_LONG).show();
        detailpesan(id_pesan);

        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = txtStatus.getText().toString();
                Toast.makeText(getApplicationContext(),"ini status"+status,Toast.LENGTH_LONG).show();
                if(status.equals("Diproses") ||status.equals("Terkirim")){
                    showDialog1();
                }
                else {
                    batalpesan(id_pesan);
                }

            }
        });


    }
    private void showDialog1(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("Batalkan Pesanan?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Pesanan telah di proses, untuk membatalkan harap hubungi Admin = 089631843242")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setNegativeButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }
    public void batalpesan(final String id_pesan){
        StringRequest strReq = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jObj = new JSONObject(response);

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("idpesan", id_pesan);
                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);


    }
    public void detailpesan(final String id_pesam) {
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jObj = new JSONObject(response);
                    String id_pesam = jObj.getString(TAG_ID_PESAN2);
                    String nama = String.valueOf(jObj.get(TAG_NAMA_PESAN));
                    String nama_barang = String.valueOf(jObj.get(TAG_NAMA_BARANG_PESAN));
                    String jumlah_pesanan = String.valueOf(jObj.get(TAG_JMLPESAN));
                    String total = String.valueOf(jObj.get(TAG_TOTAL_PESAN));
                    String tgl_kirim = String.valueOf(jObj.get(TAG_TGLKIMIR));
                    String status = String.valueOf(jObj.get(TAG_STATUS));
                    String metode = String.valueOf(jObj.get(TAG_METODE));
                    String alamat = String.valueOf(jObj.get(TAG_ALAMAT));

                    txtNamaBarang.setText(nama_barang);
                    txtJumlah.setText(jumlah_pesanan);
                    txtTotal.setText(total);
                    txttglKirim.setText(tgl_kirim);
                    txtStatus.setText(status);
                    txtPembayaran.setText(metode);
                    txtNama.setText(nama);
                    txtAlamat.setText(alamat);
                    //String nama = jObj.get(TAG_ID_NAMA);
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("idpesan", id_pesam);
                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), PesananSaya.class);
        finish();
        startActivity(intent);
    }
}
