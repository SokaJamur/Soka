package com.reppernews.sokajamur;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.reppernews.sokajamur.LoginActivity.my_shared_preferences;

public class ProfilUser extends AppCompatActivity {
    EditText namauser, alamatuser, emailuser, noHpuser, passworduser;
    SharedPreferences sharedpreferences;
    private Button btpilih2, btpilih3, btpilih4;
    String nohp, id, nama, alamat, email, password;
    ConnectivityManager conMgr;
    String tag_json_obj = "json_obj_req";
    EditText txtNama, txtAlamat, txtEmail, txtNohp, txtPassword;
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
        txtNama = (EditText)findViewById(R.id.editText11);
        txtAlamat = (EditText)findViewById(R.id.editText12);
        txtEmail = (EditText)findViewById(R.id.editText13);
        txtNohp = (EditText)findViewById(R.id.editText14);
        txtPassword = (EditText)findViewById(R.id.editText15);
        btpilih2 = (Button)findViewById(R.id.btPilih2);
        btpilih3 = (Button)findViewById(R.id.btPilih3);
        btpilih4 = (Button)findViewById(R.id.btPilih4);
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
        id = sharedpreferences.getString(TAG_ID, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);
        alamat = sharedpreferences.getString(TAG_ALAMAT, null);
        email = sharedpreferences.getString(TAG_EMAIL, null);
        password = sharedpreferences.getString(TAG_PASSWORD, null);

        namauser = (EditText) findViewById(R.id.editText11);
        alamatuser = (EditText) findViewById(R.id.editText12);
        emailuser = (EditText) findViewById(R.id.editText13);
        noHpuser = (EditText) findViewById(R.id.editText14);
        passworduser = (EditText) findViewById(R.id.editText15);

        noHpuser.setText(nohp);
        namauser.setText(nama);
        alamatuser.setText(alamat);
        emailuser.setText(email);
        passworduser.setText(password);

        btpilih2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNama.setEnabled(true);
                txtAlamat.setEnabled(true);
                txtEmail.setEnabled(true);
                txtNohp.setEnabled(true);
                txtPassword.setEnabled(true);
                btpilih3.setVisibility(View.VISIBLE);
                btpilih4.setVisibility(View.VISIBLE);
                btpilih2.setVisibility(View.GONE);
            }
        });
        btpilih3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nama = txtNama.getText().toString();
                final String alamat = txtAlamat.getText().toString();
                final String email = txtEmail.getText().toString();
                final String nohp = txtNohp.getText().toString();
                final String password = txtPassword.getText().toString();

            }
        });
        btpilih4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNama.setEnabled(false);
                txtAlamat.setEnabled(false);
                txtEmail.setEnabled(false);
                txtNohp.setEnabled(false);
                txtPassword.setEnabled(false);
                noHpuser.setText(nohp);
                namauser.setText(nama);
                alamatuser.setText(alamat);
                emailuser.setText(email);
                passworduser.setText(password);
                btpilih3.setVisibility(View.GONE);
                btpilih4.setVisibility(View.GONE);
                btpilih2.setVisibility(View.VISIBLE);
            }
        });
    }
}

