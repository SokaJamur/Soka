package com.reppernews.sokajamur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.reppernews.sokajamur.app.AppController;


import org.json.JSONException;
import org.json.JSONObject;


import static com.reppernews.sokajamur.LoginActivity.my_shared_preferences;
import static com.reppernews.sokajamur.LoginActivity.session_status;

public class SplashScreen extends AppCompatActivity{
    public final static String TAG_NOHP = "nohp";
    public final static String TAG_ID = "id";
    public final static String TAG_ID_BAGLOG = "idbaglog";
    public final static String TAG_NAMA_BAGLOG = "namabaglog";
    public final static String TAG_STOK_BAGLOG = "stokbaglog";
    public final static String TAG_HARGA_BAGLOG = "hargabaglog";

    public static String stokBaglog = "baglog";
    public static String datasimpan = "datasimpan";
    private String url = Server.URL + "databaglog.php";
    private String url2 = Server.URL + "datajamur.php";
    String tag_json_obj = "json_obj_req";
    private StringRequest stringRequest;
    ConnectivityManager conMgr;

    SharedPreferences sharedpreferences, sharedpreferences2;
    public static final String my_shared_preferences2 = "my_shared_preferences2";
    Boolean session = false;
    String id, nohp, idbaglog, namabaglog, stokbaglog, hargabaglog ;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        sharedpreferences2 = getSharedPreferences(my_shared_preferences2, Context.MODE_PRIVATE);


                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);
        nohp = sharedpreferences.getString(TAG_NOHP, null);
        idbaglog = sharedpreferences2.getString(TAG_ID_BAGLOG, null);
        namabaglog = sharedpreferences2.getString(TAG_NAMA_BAGLOG,null);
        stokbaglog = sharedpreferences2.getString(TAG_STOK_BAGLOG, null);
        hargabaglog = sharedpreferences2.getString(TAG_STOK_BAGLOG, null);
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String idbaglog = jsonObject.getString(TAG_ID_BAGLOG);
                    String stokbaglog =  jsonObject.getString(TAG_STOK_BAGLOG);
                    String namabaglog =  jsonObject.getString(TAG_NAMA_BAGLOG);
                    String hargabaglog =  jsonObject.getString(TAG_HARGA_BAGLOG);

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
        Toast.makeText(getApplicationContext(), "toast"+idbaglog, Toast.LENGTH_LONG).show();
        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(session){
                    if(nohp.equals("admin")) {
                        Intent intent = new Intent(getApplicationContext(), HomeAdmin.class);
                        intent.putExtra(TAG_ID, id);
                        intent.putExtra(TAG_NOHP, nohp);
                        finish();
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(getApplicationContext(), HomeUser.class);
                        intent.putExtra(TAG_ID, id);
                        intent.putExtra(TAG_NOHP, nohp);
                        finish();
                        startActivity(intent);
                    }

                }
                else {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        }, 3000L);

    }



}

