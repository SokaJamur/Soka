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

import static com.reppernews.sokajamur.LoginActivity.TAG_ALAMAT;
import static com.reppernews.sokajamur.LoginActivity.TAG_NAMA;
import static com.reppernews.sokajamur.LoginActivity.my_shared_preferences;
import static com.reppernews.sokajamur.LoginActivity.session_status;
import static com.reppernews.sokajamur.SplashScreen.TAG_ID_BAGLOG;
import static com.reppernews.sokajamur.SplashScreen.TAG_STOK_BAGLOG;
import static com.reppernews.sokajamur.SplashScreen.my_shared_preferences2;

public class InfoBaglog extends AppCompatActivity {
    private Spinner combobox2;
    private Button btpilih2;
    int harga, jumlahbeli, stokawal, stokakhir, total;
    public final static String TAG_NOHP = "nohp";
    SharedPreferences sharedPreferences, sharedPreferences2;
    Boolean session = false;
    String nohp, idbaglog, stokbaglog, nama, alamat, jumlah;
    TextView txtStok, txtTotal;
    EditText txtNama, txtNohp, txtAlamat, txtJumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_baglog);
        combobox2=(Spinner) findViewById(R.id.cbBox2);
        txtNama=(EditText)findViewById(R.id.editText);
        txtTotal=(TextView)findViewById(R.id.textView14);
        txtAlamat=(EditText)findViewById(R.id.editText2);
        txtNohp=(EditText)findViewById(R.id.editText3);
        btpilih2=(Button)findViewById(R.id.btPilih2);
        txtStok=(TextView)findViewById(R.id.textView11);
        txtJumlah=(EditText)findViewById(R.id.editText4);
        final String[] pilihan_menu2=getResources().getStringArray(R.array.pilihan_menu2); // ambil menu dari string.xml
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin, pilihan_menu2);
        combobox2.setAdapter(adapter);
        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences(my_shared_preferences2, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(session_status, false);
        nohp = sharedPreferences.getString(TAG_NOHP, null);
        nama = sharedPreferences.getString(TAG_NAMA, null);
        alamat = sharedPreferences.getString(TAG_ALAMAT, null);
        idbaglog = sharedPreferences2.getString(TAG_ID_BAGLOG, null);
        stokbaglog = sharedPreferences2.getString(TAG_STOK_BAGLOG, null);
        txtStok.setText(stokbaglog);
        txtNama.setText(nama);
        txtNohp.setText(nohp);
        txtAlamat.setText(alamat);
        stokawal = Integer.parseInt(stokbaglog);
        harga = 2500;
        if(String.valueOf(txtTotal).equals("")){
            btpilih2.setText("Hitung");
        }
        else{
            btpilih2.setText("Pesan1");
        }
        Toast.makeText(getApplicationContext(), "tes"+idbaglog,Toast.LENGTH_LONG).show();
        combobox2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected "+ adapter.getItem(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*btpilih2.setOnClickListener(new View.OnClickListener() {
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

    public void klikPilih2(View v){
        String pilihan=combobox2.getSelectedItem().toString();

    }

}

