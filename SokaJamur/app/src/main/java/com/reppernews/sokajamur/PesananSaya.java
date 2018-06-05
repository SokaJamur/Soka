package com.reppernews.sokajamur;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PesananSaya extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private RecyclerView lvhape;
    public ListView listView;
    private String JSON_STRING;
    public static final String TAG_JSON_ARRAY = "result";
    private RequestQueue requestQueue;
    public static final String TAG_TOTAL = "total";
    public static final String TAG_ID_PESAN = "id_pesan";
    public static final String TAG_NAMA_BARANG = "nama_barang";
    private StringRequest stringRequest;
    private String url = Server.URL + "ambilpesanan.php";
    ArrayList<HashMap<String, String>> list_data;
    public static final String URL_GET_ALL = "http://tifpolije16.com/pesananSaya.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_saya);
        listView = (ListView) findViewById(R.id.listView);
        getJSON();
        listView.setOnItemClickListener(this);

    }
    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String total = jo.getString(TAG_TOTAL);
                String id_pesan = jo.getString(TAG_ID_PESAN);
                //String gambar ="http://tifpolije16.com/soka/assests/img/" +jo.getString(TAG_GAMBAR);
                String nama_barang = jo.getString(TAG_NAMA_BARANG);
                HashMap<String,String> employees = new HashMap<>();
                employees.put(TAG_ID_PESAN,id_pesan);
                employees.put(TAG_TOTAL, total);
                employees.put(TAG_NAMA_BARANG,nama_barang);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new PesananSaya.MyAdapter(
                PesananSaya.this, list, R.layout.list_pesan_user,
                new String[]{TAG_TOTAL,TAG_NAMA_BARANG},
                new int[]{R.id.total, R.id.nama_barang});

        listView.setAdapter(adapter);
    }
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(PesananSaya.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(PesananSaya.this, DetailPesanan.class);
        Bundle b = new Bundle();
        HashMap<String,String> map=(HashMap)parent.getItemAtPosition(position);
        String id_pesan = map.get(TAG_ID_PESAN).toString();
        intent.putExtra(TAG_ID_PESAN,id_pesan);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "ini id"+id_pesan, Toast.LENGTH_LONG).show();
    }
    public class MyAdapter extends SimpleAdapter {

        public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to){
            super(context, data, resource, from, to);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            // here you let SimpleAdapter built the view normally.
            View v = super.getView(position, convertView, parent);

            // Then we get reference for Picasso


            // return the view
            return v;

        }
    }
}

