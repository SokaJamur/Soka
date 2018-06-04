package com.reppernews.sokajamur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PesananSaya extends AppCompatActivity {
    private RecyclerView lvhape;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String url = Server.URL + "ambilpesanan.php";
    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_saya);
        lvhape = (RecyclerView) findViewById(R.id.lvhape);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lvhape.setLayoutManager(llm);

        requestQueue = Volley.newRequestQueue(PesananSaya.this);
        list_data = new ArrayList<HashMap<String, String>>();
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("handphone");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("id", json.getString("ID_PESAN"));
                        map.put("merk", json.getString("JUMLAH_PESANAN"));
                        map.put("tipe", json.getString("TOTAL"));
                        map.put("gambar", json.getString("gambar"));
                        map.put("keterangan", json.getString("TGL_KIRIM"));
                        list_data.add(map);
                        AdapterList adapter = new AdapterList(PesananSaya.this, list_data);
                        lvhape.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PesananSaya.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
        }
    }

