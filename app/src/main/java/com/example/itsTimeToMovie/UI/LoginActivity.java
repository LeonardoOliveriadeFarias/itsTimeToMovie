package com.example.itsTimeToMovie.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itsTimeToMovie.R;
import com.example.itsTimeToMovie.data.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginActivity extends AppCompatActivity {

    private EditText editTxt_email, editTxt_senha;
    private Button btmLogin, btmCadastrar;
    private FirebaseAuth authEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTxt_email = (EditText) findViewById(R.id.editLogin) ;
        editTxt_senha = (EditText) findViewById(R.id.editSenha) ;

        btmLogin = findViewById(R.id.BttmLogin);
        btmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginEmail();
            }
        });

       btmCadastrar = findViewById(R.id.BttmCadastrar);
        btmCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

        authEmail = FirebaseAuth.getInstance();

    }

    public void login(){
        Intent intent = new Intent(this, Catalogo.class);
        startActivity(intent);
    }

    public void cadastrar(){
        Intent intent = new Intent(this, CadastrarActivity.class);
        startActivity(intent);
    }

    private void loginEmail(){
        String email = editTxt_email.getText().toString().trim();
        String senha = editTxt_senha.getText().toString().trim();

        if(email.isEmpty()||senha.isEmpty()){
            Toast.makeText(getBaseContext(),
                    "Preencha os campos",
                    Toast.LENGTH_LONG).show();
        }else {
            loginConfirm(email,senha);
        }
    }

    private void loginConfirm(String email, String senha){
        authEmail.signInWithEmailAndPassword(email,senha).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getBaseContext(),
                                    "Usuário Logado com Sucesso",
                                    Toast.LENGTH_LONG).show();
                            login();
                        }else{
                            Toast.makeText(getBaseContext(),
                                    "Usuário ou senha incorretos",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}