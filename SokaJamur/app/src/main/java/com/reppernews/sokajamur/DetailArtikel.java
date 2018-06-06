package com.reppernews.sokajamur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import static com.reppernews.sokajamur.HomeActivity.TAG_ID_ARTIKEL;
import static com.reppernews.sokajamur.PesananSaya.TAG_ID_PESAN;

public class DetailArtikel extends AppCompatActivity {
    TextView txtJudul, txtIsi;
    private String url = Server.URL + "detailArtikel.php";
    String tag_json_obj = "json_obj_req";
    public static final String TAG_ID_ARTIKEL2 = "id_artikel";
    public static final String TAG_JUDUL= "judul";
    public static final String TAG_ISI= "isi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);
        txtJudul = (TextView) findViewById(R.id.judul);
        txtIsi = (TextView) findViewById(R.id.isi);
        Bundle b = getIntent().getExtras();
        String id_artikel = b.getString(TAG_ID_ARTIKEL);
        Toast.makeText(getApplicationContext(), "ini id artikel" + id_artikel, Toast.LENGTH_LONG).show();
        detailartikel(id_artikel);

    }

    public void detailartikel(final String id_artikel) {
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jObj = new JSONObject(response);
                    String id_artikel = jObj.getString(TAG_ID_ARTIKEL2);
                    String judul = jObj.getString(TAG_JUDUL);
                    String isi = jObj.getString(TAG_ISI);

                    txtJudul.setText(judul);
                    txtIsi.setText(isi);
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
                params.put("idartikel", id_artikel);
                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        finish();
        startActivity(intent);
    }
}
