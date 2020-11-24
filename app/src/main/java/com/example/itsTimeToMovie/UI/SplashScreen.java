package com.example.itsTimeToMovie.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import androidx.appcompat.app.AppCompatActivity;

import com.example.itsTimeToMovie.R;


public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.getSupportActionBar().hide();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                open();
            }
        },1000);

    }

    private void open() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
