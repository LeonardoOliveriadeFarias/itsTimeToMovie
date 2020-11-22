/*package com.example.nordshopapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nordshopapp.R;

public class SplashScreen extends AppCompatActivity {


    private ImageButton imageButtonMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.getSupportActionBar().hide();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openLogin();
            }
        },1000);

    }

    private void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    private void openCadastro() {
        Intent intent = new Intent(this, Cadastro.class);
        startActivity(intent);
        finish();
    }
}*/
