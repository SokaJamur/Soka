package com.reppernews.sokajamur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import static com.reppernews.sokajamur.LoginActivity.TAG_ALAMAT;
import static com.reppernews.sokajamur.LoginActivity.TAG_ID;
import static com.reppernews.sokajamur.LoginActivity.TAG_NAMA;
import static com.reppernews.sokajamur.LoginActivity.my_shared_preferences;
import static com.reppernews.sokajamur.LoginActivity.session_status;

//import static com.reppernews.sokajamur.LoginActivity.my_shared_preferences;

public class InfoBaglog extends AppCompatActivity {
    private String url2 = Server.URL + "databaglog.php";
    public final static String TAG_ID_BAGLOG = "idbaglog";
    public static StringRequest stringRequest2;
    public final static String TAG_NAMA_BAGLOG = "namabaglog";
    public final static String TAG_STOK_BAGLOG = "stokbaglog";
    public final static String TAG_HARGA_BAGLOG = "hargabaglog";
    private Spinner combobox2;
    private Button btpilih2, btpilih3, btpilih4;
    String tag_json_obj = "json_obj_req";
    final Context context = this;
    int harga, jumlahbeli, stokawal, stokakhir, total;
    private String url = Server.URL + "pesanan.php";
    public final static String TAG_NOHP = "nohp";
    SharedPreferences sharedPreferences, sharedPreferences2;
    Boolean session = false;
    public static final String my_shared_preferences2 = "my_shared_preferences2";
    String nohp, idbaglog, stokbaglog, nama, alamat, jumlah , iduser, stokbaru;
    private static final String TAG_SUCCESS = "success";
    TextView txtStok, txtTotal;
    EditText txtNama, txtNohp, txtAlamat, txtJumlah;
    int success1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_baglog);
        final String[] isispinner = new String[1];
        //idbaglog = sharedPreferences2.getString(TAG_ID_BAGLOG, null);
        //namabaglog = sharedPreferences2.getString(TAG_NAMA_BAGLOG,null);
        //stokbaglog = sharedPreferences2.getString(TAG_STOK_BAGLOG, null);
        //hargabaglog = sharedPreferences2.getString(TAG_STOK_BAGLOG, null);
        combobox2=(Spinner) findViewById(R.id.cbBox2);
        txtNama=(EditText)findViewById(R.id.editText);
        txtTotal=(TextView)findViewById(R.id.textView14);
        txtAlamat=(EditText)findViewById(R.id.editText2);
        txtNohp=(EditText)findViewById(R.id.editText3);
        btpilih2=(Button)findViewById(R.id.btPilih2);
        btpilih3=(Button)findViewById(R.id.btPilih3);
        btpilih4=(Button)findViewById(R.id.btPilih4);
        txtStok=(TextView)findViewById(R.id.textView11);
        txtJumlah=(EditText)findViewById(R.id.editText4);
        final String[] pilihan_menu2=getResources().getStringArray(R.array.pilihan_menu2); // ambil menu dari string.xml
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin, pilihan_menu2);
        combobox2.setAdapter(adapter);
        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences(my_shared_preferences2, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(session_status, false);
        nohp = sharedPreferences.getString(TAG_NOHP, null);
        iduser = sharedPreferences.getString(TAG_ID, null);
        nama = sharedPreferences.getString(TAG_NAMA, null);
        alamat = sharedPreferences.getString(TAG_ALAMAT, null);
        idbaglog = sharedPreferences2.getString(TAG_ID_BAGLOG, null);
        stokbaglog = sharedPreferences2.getString(TAG_STOK_BAGLOG, null);
        txtNama.setText(nama);
        txtNohp.setText(nohp);
        txtAlamat.setText(alamat);
        //stokawal = Integer.parseInt(stokbaglog);
        harga = 2500;
        stringRequest2 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String idbaglog = jsonObject.getString(TAG_ID_BAGLOG);
                    String stokbaglog = jsonObject.getString(TAG_STOK_BAGLOG);
                    String namabaglog = jsonObject.getString(TAG_NAMA_BAGLOG);
                    String hargabaglog = jsonObject.getString(TAG_HARGA_BAGLOG);

                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString(TAG_ID_BAGLOG, idbaglog);
                    editor.putString(TAG_STOK_BAGLOG, stokbaglog);
                    editor.putString(TAG_NAMA_BAGLOG, namabaglog);
                    editor.putString(TAG_HARGA_BAGLOG, hargabaglog);
                    editor.commit();
                }
                catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //Toast.makeText(getApplicationContext(), "toast"+idbaglog, Toast.LENGTH_LONG).show();
        AppController.getInstance().addToRequestQueue(stringRequest2, tag_json_obj);
        txtStok.setText(stokbaglog);

        combobox2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Selected "+ adapter.getItem(position), Toast.LENGTH_SHORT).show();
                isispinner[0] = String.valueOf(adapter.getItem(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btpilih2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(session){
                    if(txtJumlah.getText().toString().length() == 0){
                        Toast.makeText(getApplicationContext(), "Jumlah Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                    }
                    else {
                        hasilHitung();
                        btpilih3.setVisibility(View.VISIBLE);
                        btpilih4.setVisibility(View.VISIBLE);
                        btpilih2.setVisibility(View.GONE);
                    }
                }

                else{
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra(TAG_NOHP, nohp);
                    finish();
                    startActivity(intent);
                }


            }

        });
        btpilih3.setOnClickListener(new View.OnClickListener() {

             @Override
            public void onClick(View v) {
                 final String iduser1 = String.valueOf(iduser).toString();
                 final String jumlah1 = txtJumlah.getText().toString();
                 final String spiner = isispinner[0];
                 final String total = txtTotal.getText().toString();
                 inputpesanan(iduser1,jumlah1,spiner,total);
                 Intent intent = new Intent(getApplicationContext(), PesananSaya.class);
                 finish();
                 startActivity(intent);


             }
        });
        btpilih4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTotal.setText("0");
                btpilih3.setVisibility(View.GONE);
                btpilih4.setVisibility(View.GONE);
                btpilih2.setVisibility(View.VISIBLE);
            }
        });

    }

    public void hasilHitung(){
        if (txtJumlah.length() > 0 ) {
            jumlahbeli = Integer.parseInt(String.valueOf(txtJumlah.getText()));
            //Integer hitungtotal2 = Integer.parseInt(hitungtotal);
            total = jumlahbeli * harga;
            txtTotal.setText(String.valueOf(total));
        }
        else{
            Toast.makeText(getApplicationContext(), "Jumlah tidak boleh kosong", Toast.LENGTH_LONG).show();        }
    }

    public void klikPilih2(View v){
        String pilihan=combobox2.getSelectedItem().toString();

    }
    public void inputpesanan(final String iduser1, final String jumlah1, final String spiner, final String total){
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jObj = new JSONObject(response);
                    success1 = jObj.getInt(TAG_SUCCESS);
                    stokbaru = jObj.getString(TAG_STOK_BAGLOG);
                    if(success1 == 1){
                        SharedPreferences.Editor editor = sharedPreferences2.edit();
                        editor.putString(TAG_STOK_BAGLOG, stokbaru);
                        editor.commit();
                    //    Toast.makeText(getApplicationContext(),"Pesan Berhasil", Toast.LENGTH_LONG).show();
                    }
                    else{
                       // Toast.makeText(getApplicationContext(),"Gagal gan", Toast.LENGTH_LONG).show();
                    }


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
                params.put("txtJumlah", jumlah1);
                params.put("adapter", spiner);
                params.put("iduser", iduser1);
                params.put("txtTotal", total);
                params.put("idbaglog", String.valueOf(idbaglog).toString());
                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);


    }

}

