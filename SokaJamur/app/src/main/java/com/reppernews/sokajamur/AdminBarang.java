package com.reppernews.sokajamur;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import static com.reppernews.sokajamur.InfoBaglog.TAG_HARGA_BAGLOG;
import static com.reppernews.sokajamur.InfoBaglog.TAG_ID_BAGLOG;
import static com.reppernews.sokajamur.InfoBaglog.TAG_NAMA_BAGLOG;
import static com.reppernews.sokajamur.InfoBaglog.TAG_STOK_BAGLOG;
import static com.reppernews.sokajamur.InfoJamur.TAG_HARGA_JAMUR;
import static com.reppernews.sokajamur.InfoJamur.TAG_ID_JAMUR;
import static com.reppernews.sokajamur.InfoJamur.TAG_NAMA_JAMUR;
import static com.reppernews.sokajamur.InfoJamur.TAG_STOK_JAMUR;
import static com.reppernews.sokajamur.SplashScreen.my_shared_preferences2;

public class AdminBarang extends AppCompatActivity {
    private String url2 = Server.URL + "databaglog.php";
    private Button btpilih, btnTambahBaglog, btnTambahJamur;
    private String url = Server.URL + "datajamur.php";
    private String url3 = Server.URL + "update.php";
    SharedPreferences sharedpreferences2;
    String tag_json_obj = "json_obj_req";
    Button btnJamur, btnBaglog, btnSimpanJamur, btnBatalJamur, btnSimpanBaglog, btnBatalBaglog;
    EditText jenisJamur, hargaJamur, stokJamur, jenisBaglog, hargaBaglog, stokBaglog;
    String idjamur, idbaglog, namajamur, stokjamur, hargabaglog, hargajamur, stokbaglog, namabaglog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_barang);
        btnTambahBaglog = (Button) findViewById(R.id.btntambah);
        jenisJamur = (EditText)findViewById(R.id.editText10);
        stokJamur = (EditText)findViewById(R.id.editText17);
        hargaJamur = (EditText)findViewById(R.id.editText18);
        btnTambahJamur = (Button)findViewById(R.id.button3);
        jenisBaglog = (EditText)findViewById(R.id.editText11);
        stokBaglog = (EditText)findViewById(R.id.editText19);
        hargaBaglog = (EditText)findViewById(R.id.editText20);
        btnJamur = (Button)findViewById(R.id.button3);
        btnSimpanJamur = (Button)findViewById(R.id.button5);
        btnBatalJamur = (Button)findViewById(R.id.button6);
        btnBaglog = (Button)findViewById(R.id.button4);
        btnSimpanBaglog = (Button)findViewById(R.id.button7);
        btnBatalBaglog = (Button)findViewById(R.id.button8);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String idjamur = jsonObject.getString(TAG_ID_JAMUR);
                    String stokjamur = jsonObject.getString(TAG_STOK_JAMUR);
                    String namajamur = jsonObject.getString(TAG_NAMA_JAMUR);
                    String hargajamur = jsonObject.getString(TAG_HARGA_JAMUR);

                    SharedPreferences.Editor editor = sharedpreferences2.edit();
                    editor.putString(TAG_ID_JAMUR, idjamur);
                    editor.putString(TAG_STOK_JAMUR, stokjamur);
                    editor.putString(TAG_NAMA_JAMUR, namajamur);
                    editor.putString(TAG_HARGA_JAMUR, hargajamur);
                    editor.commit();
                } catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String idbaglog = jsonObject.getString(TAG_ID_BAGLOG);
                    String stokbaglog = jsonObject.getString(TAG_STOK_BAGLOG);
                    String namabaglog = jsonObject.getString(TAG_NAMA_BAGLOG);
                    String hargabaglog = jsonObject.getString(TAG_HARGA_BAGLOG);

                    SharedPreferences.Editor editor = sharedpreferences2.edit();
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
        AppController.getInstance().addToRequestQueue(stringRequest1, tag_json_obj);
        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
        sharedpreferences2 = getSharedPreferences(my_shared_preferences2, Context.MODE_PRIVATE);
        idjamur = sharedpreferences2.getString(TAG_ID_JAMUR, null);
        idbaglog = sharedpreferences2.getString(TAG_ID_BAGLOG, null);
        namajamur = sharedpreferences2.getString(TAG_NAMA_JAMUR, null);
        stokjamur = sharedpreferences2.getString(TAG_STOK_JAMUR, null);
        hargajamur = sharedpreferences2.getString(TAG_HARGA_JAMUR, null);
        stokbaglog = sharedpreferences2.getString(TAG_STOK_BAGLOG, null);
        hargabaglog = sharedpreferences2.getString(TAG_HARGA_BAGLOG, null);
        namabaglog = sharedpreferences2.getString(TAG_NAMA_BAGLOG,null);

        jenisJamur.setText(namajamur);
        stokJamur.setText(stokjamur);
        hargaJamur.setText(hargajamur);
        jenisBaglog.setText(namabaglog);
        stokBaglog.setText(stokbaglog);
        hargaBaglog.setText(hargabaglog);

        btnJamur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stokJamur.setEnabled(true);
                btnSimpanJamur.setVisibility(View.VISIBLE);
                btnBatalJamur.setVisibility(View.VISIBLE);
                btnJamur.setVisibility(View.GONE);

            }
        });

        btnBaglog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stokJamur.setEnabled(true);
                btnSimpanBaglog.setVisibility(View.VISIBLE);
                btnBatalBaglog.setVisibility(View.VISIBLE);
                btnBaglog.setVisibility(View.GONE);
            }
        });

        btnBatalJamur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jenisJamur.setEnabled(false);
                stokJamur.setEnabled(false);
                stokJamur.setText(stokjamur);
                hargaJamur.setText(hargajamur);
                btnSimpanJamur.setVisibility(View.GONE);
                btnBatalJamur.setVisibility(View.GONE);
                btnJamur.setVisibility(View.VISIBLE);
            }
        });

        btnBatalBaglog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jenisBaglog.setEnabled(false);
                stokJamur.setEnabled(false);
                stokBaglog.setText(stokbaglog);
                hargaBaglog.setText(hargabaglog);
                btnSimpanBaglog.setVisibility(View.GONE);
                btnBatalBaglog.setVisibility(View.GONE);
                btnBaglog.setVisibility(View.VISIBLE);
            }
        });
        btnSimpanJamur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idJamur = String.valueOf(idjamur).toString();
                Toast.makeText(getApplicationContext(),"ini id jamur"+idJamur, Toast.LENGTH_LONG).show();
                String stokjamur = stokJamur.getText().toString();
                Toast.makeText(getApplicationContext(),"ini stok jamur"+stokjamur, Toast.LENGTH_LONG).show();
                updateJamur(idJamur, stokjamur);
            }
        });

    }
    public void updateJamur(final String idJamur, final String stokjamur) {
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("idbarang", idJamur);
                params.put("stokbaru", stokjamur);
                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest2, tag_json_obj);
    }


}


