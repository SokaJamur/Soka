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
import android.widget.Spinner;
import android.widget.Toast;

import static com.reppernews.sokajamur.LoginActivity.my_shared_preferences;
import static com.reppernews.sokajamur.LoginActivity.session_status;

public class InfoJamur extends AppCompatActivity {
    private Spinner combobox;
    private Button btpilih;
    public final static String TAG_NOHP = "nohp";
    SharedPreferences sharedPreferences;
    Boolean session = false;
    String nohp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_jamur);
        combobox=(Spinner) findViewById(R.id.cbBox);
        btpilih=(Button)findViewById(R.id.btPilih);
        String[] pilihan_menu=getResources().getStringArray(R.array.pilihan_menu); // ambil menu dari string.xml
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin, pilihan_menu);
        combobox.setAdapter(adapter);
        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(session_status, false);
        nohp = sharedPreferences.getString(TAG_NOHP, null);

        combobox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        btpilih.setOnClickListener(new View.OnClickListener() {
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


        });

    }
    public void klikPilih(View v){
        String pilihan=combobox.getSelectedItem().toString();
        Toast.makeText(this,pilihan,Toast.LENGTH_SHORT).show();
    }
}