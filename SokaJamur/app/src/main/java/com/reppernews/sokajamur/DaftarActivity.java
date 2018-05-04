package com.reppernews.sokajamur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DaftarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        final EditText isianNama= (EditText) findViewById(R.id.isianNama);
        final EditText isianAlm = (EditText) findViewById(R.id.isianAlm);
        final EditText isianNo = (EditText) findViewById(R.id.isianNo);
        final EditText isianEmail = (EditText) findViewById(R.id.isianEmail);
        final EditText isianPass = (EditText) findViewById(R.id.isianPass);
        Button btndaftar = (Button) findViewById(R.id.btndaftar);

        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),HomeUser.class);
                startActivity(intent);
            }
        });
    }
}
