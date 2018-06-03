package com.reppernews.sokajamur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.reppernews.sokajamur.LoginActivity.TAG_ALAMAT;
import static com.reppernews.sokajamur.LoginActivity.TAG_NAMA;
import static com.reppernews.sokajamur.LoginActivity.my_shared_preferences;
import static com.reppernews.sokajamur.LoginActivity.session_status;
import static com.reppernews.sokajamur.SplashScreen.TAG_ID_JAMUR;
import static com.reppernews.sokajamur.SplashScreen.TAG_STOK_JAMUR;
import static com.reppernews.sokajamur.SplashScreen.my_shared_preferences2;

public class InfoJamur extends AppCompatActivity {
    private Spinner combobox;
    private Button btpilih;
    int harga, jumlahbeli, stokawal, stokakhir, total;
    public final static String TAG_NOHP = "nohp";
    SharedPreferences sharedPreferences, sharedPreferences2;
    Boolean session = false;
    String nohp, idjamur, stokjamur, nama, alamat, jumlah;
    TextView txtStok, txtTotal;
    EditText txtNama, txtNohp, txtAlamat, txtJumlah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_jamur);
        combobox=(Spinner) findViewById(R.id.cbBox);
        txtNama=(EditText)findViewById(R.id.editText);
        txtTotal=(TextView)findViewById(R.id.textView14);
        txtAlamat=(EditText)findViewById(R.id.editText2);
        txtNohp=(EditText)findViewById(R.id.editText3);
        btpilih=(Button)findViewById(R.id.btPilih);
        txtStok=(TextView)findViewById(R.id.textView11);
        txtJumlah=(EditText)findViewById(R.id.editText4);
        final String[] pilihan_menu=getResources().getStringArray(R.array.pilihan_menu); // ambil menu dari string.xml
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin, pilihan_menu);
        combobox.setAdapter(adapter);
        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences(my_shared_preferences2, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(session_status, false);
        nohp = sharedPreferences.getString(TAG_NOHP, null);
        nama = sharedPreferences.getString(TAG_NAMA, null);
        alamat = sharedPreferences.getString(TAG_ALAMAT, null);
        idjamur = sharedPreferences2.getString(TAG_ID_JAMUR, null);
        stokjamur = sharedPreferences2.getString(TAG_STOK_JAMUR, null);
        txtStok.setText(stokjamur);
        txtNama.setText(nama);
        txtNohp.setText(nohp);
        txtAlamat.setText(alamat);

        Toast.makeText(getApplicationContext(), "tes"+idjamur,Toast.LENGTH_LONG).show();
        combobox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected "+ adapter.getItem(position), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       /* btpilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(session){
                    Intent intent = new Intent(getApplicationContext(), PesananSaya.class);
                    intent.putExtra(TAG_NOHP, nohp);
                    finish();
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra(TAG_NOHP, nohp);
                    finish();
                    startActivity(intent);
                }


            }


        });*/

    }
    public void klikPilih(View v){
        String pilihan=combobox.getSelectedItem().toString();
    }
}