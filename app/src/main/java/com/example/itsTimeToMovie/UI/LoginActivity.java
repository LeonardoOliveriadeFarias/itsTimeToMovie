package com.example.itsTimeToMovie.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.itsTimeToMovie.R;
import com.example.itsTimeToMovie.data.Model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button btmLogin;
        final Button btmCadastrar;

        btmLogin = findViewById(R.id.BttmLogin);
        btmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

       btmCadastrar = findViewById(R.id.BttmCadastrar);
        btmCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });
    }

    public void login(){
        Intent intent = new Intent(this, Catalogo.class);
        startActivity(intent);
    }

    public void cadastrar(){
        Intent intent = new Intent(this, CadastrarActivity.class);
        startActivity(intent);
    }


}