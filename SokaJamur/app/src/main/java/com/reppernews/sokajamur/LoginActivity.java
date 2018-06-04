package com.reppernews.sokajamur;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {
    ProgressDialog pDialog;
    EditText isianNo, isianPass;
    Button btnmasuk;
    //Intent intent;
    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "login.php";

    private static final String TAG = LoginActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_NOHP = "nohp";
    public final static String TAG_ID = "id";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_PASSWORD = "password";

    String tag_json_obj = "json_obj_req";

    SharedPreferences sharedpreferences;
    Boolean session = false;
    String id, nohp, alamat, nama, email, password;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
         isianNo = (EditText) findViewById(R.id.isianNo);
         isianPass = (EditText) findViewById(R.id.isianPass);
        btnmasuk = (Button) findViewById(R.id.btnmasuk);
// Cek session login jika TRUE maka langsung buka MainActivity
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);
        nohp = sharedpreferences.getString(TAG_NOHP, null);
        password = sharedpreferences.getString(TAG_PASSWORD, null);

        if(session){
            if(nohp.equals("admin")) {
                Intent intent = new Intent(LoginActivity.this, HomeAdmin.class);
                intent.putExtra(TAG_ID, id);
                intent.putExtra(TAG_NOHP, nohp);
                intent.putExtra(TAG_ALAMAT, alamat);
                intent.putExtra(TAG_EMAIL, email);
                intent.putExtra(TAG_NAMA, nama);
                intent.putExtra(TAG_PASSWORD, password);
                finish();
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(LoginActivity.this, HomeUser.class);
                intent.putExtra(TAG_ID, id);
                intent.putExtra(TAG_NOHP, nohp);
                intent.putExtra(TAG_ALAMAT, alamat);
                intent.putExtra(TAG_EMAIL, email);
                intent.putExtra(TAG_NAMA, nama);
                intent.putExtra(TAG_PASSWORD, password);
                finish();
                startActivity(intent);
            }


        }
        btnmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nohp = isianNo.getText().toString();
                String password = isianPass.getText().toString();
                // mengecek kolom yang kosong
                if (nohp.trim().length() > 0 && password.trim().length() > 0) {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        checkLogin(nohp, password);
                    } else {
                        Toast.makeText(getApplicationContext() ,"No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                }

              //Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            //startActivity(intent);
            }
        });
    }
    public void textView3(View view) {
     Intent intent = new Intent(getApplicationContext(),DaftarActivity.class);
     startActivity(intent);
    }

    private void checkLogin(final String nohp, final String password) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {
                        String nohp = jObj.getString(TAG_NOHP);
                        String id = jObj.getString(TAG_ID);
                        String nama = jObj.getString(TAG_NAMA);
                        String alamat = jObj.getString(TAG_ALAMAT);
                        String email = jObj.getString(TAG_EMAIL);
                        String password = jObj.getString(TAG_PASSWORD);
                        //String nohp = isianNo.getText().toString();

                        Log.e("Successfully Login!", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        // menyimpan login ke session
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_ID, id);
                        editor.putString(TAG_NOHP, nohp);
                        editor.putString(TAG_NAMA, nama);
                        editor.putString(TAG_ALAMAT, alamat);
                        editor.putString(TAG_EMAIL, email);
                        editor.putString(TAG_PASSWORD, password);
                        editor.commit();

                        // Memanggil main activity
                        if(nohp.equals("admin")){
                            Intent intent = new Intent(LoginActivity.this, HomeAdmin.class);
                            intent.putExtra(TAG_ID, id);
                            intent.putExtra(TAG_NOHP, nohp);
                            editor.putString(TAG_NAMA, nama);
                            editor.putString(TAG_ALAMAT, alamat);
                            editor.putString(TAG_EMAIL, email);
                            editor.putString(TAG_PASSWORD, password);
                            finish();
                            startActivity(intent);
                        }
                        else{
                            Intent intent = new Intent(LoginActivity.this, HomeUser.class);
                            intent.putExtra(TAG_ID, id);
                            intent.putExtra(TAG_NOHP, nohp);
                            intent.putExtra(TAG_PASSWORD, password);
                            finish();
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nohp", nohp);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public void coba(View view) {
        Intent intent = new Intent(getApplicationContext(),HomeAdmin.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        finish();
        startActivity(intent);
    }
}