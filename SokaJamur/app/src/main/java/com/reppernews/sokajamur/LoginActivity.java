package com.reppernews.sokajamur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText isianNo = (EditText) findViewById(R.id.isianNo);
        final EditText isianPass = (EditText) findViewById(R.id.isianPass);

        Button btnmasuk = (Button) findViewById(R.id.btnmasuk);

        btnmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);
            }
        });
    }
    public void textView3(View view) {
     Intent intent = new Intent(getApplicationContext(),DaftarActivity.class);
     startActivity(intent);
    }
}