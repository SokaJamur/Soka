package com.reppernews.sokajamur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import static com.reppernews.sokajamur.LoginActivity.my_shared_preferences;

public class ProfilUser extends AppCompatActivity {
    EditText namauser, alamatuser, emailuser, noHpuser, passworduser;
    SharedPreferences sharedpreferences;
    String nohp;
    ConnectivityManager conMgr;
    String tag_json_obj = "json_obj_req";
    int success;
    private String url = Server.URL + "profiluser.php";
    public final static String TAG_NOHP = "nohp";
    public final static String TAG_ID = "id";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_user);
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        nohp = sharedpreferences.getString(TAG_NOHP, null);

        namauser = (EditText) findViewById(R.id.editText11);
        alamatuser = (EditText) findViewById(R.id.editText12);
        emailuser = (EditText) findViewById(R.id.editText13);
        noHpuser = (EditText) findViewById(R.id.editText14);
        passworduser = (EditText) findViewById(R.id.editText15);

        noHpuser.setText(nohp);
    }


}

