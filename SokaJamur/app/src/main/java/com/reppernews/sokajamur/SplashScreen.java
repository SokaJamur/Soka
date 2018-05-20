package com.reppernews.sokajamur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import static com.reppernews.sokajamur.LoginActivity.my_shared_preferences;
import static com.reppernews.sokajamur.LoginActivity.session_status;

public class SplashScreen extends AppCompatActivity{
    public final static String TAG_NOHP = "nohp";
    public final static String TAG_ID = "id";
    SharedPreferences sharedpreferences;
    Boolean session = false;
    String id, nohp;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);
        nohp = sharedpreferences.getString(TAG_NOHP, null);

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

