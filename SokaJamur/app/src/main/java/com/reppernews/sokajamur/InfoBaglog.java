package com.reppernews.sokajamur;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import static com.reppernews.sokajamur.LoginActivity.my_shared_preferences;

public class InfoBaglog extends AppCompatActivity {
    private Spinner combobox2;
    private Button btpilih2;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_baglog);
        combobox2=(Spinner) findViewById(R.id.cbBox2);
        btpilih2=(Button)findViewById(R.id.btPilih2);
        String[] pilihan_menu2=getResources().getStringArray(R.array.pilihan_menu2); // ambil menu dari string.xml
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin, pilihan_menu2);
        combobox2.setAdapter(adapter);
        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);

        combobox2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                Toast.makeText(InfoBaglog.this, combobox2.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

    }

    public void klikPilih(View v){
        String pilihan=combobox2.getSelectedItem().toString();
        Toast.makeText(this,pilihan,Toast.LENGTH_SHORT).show();
    }
}

