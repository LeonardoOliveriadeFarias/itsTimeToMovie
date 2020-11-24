package com.example.itsTimeToMovie.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.itsTimeToMovie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastrarActivity extends AppCompatActivity {

    private EditText editTxt_email, editTxt_senha, editTxt_confirmasenha;
    private Button cadastrarBttm, limparBttm;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        editTxt_email = (EditText)findViewById(R.id.Login_cadastro);
        editTxt_senha = (EditText)findViewById(R.id.senha_cadastro);
        editTxt_confirmasenha= (EditText)findViewById(R.id.confirmsenha_cadastro);

        cadastrarBttm = (Button)findViewById(R.id.cadastrar_novo);
        limparBttm = (Button) findViewById(R.id.BttmLimpar);

        cadastrarBttm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

        limparBttm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpar();
            }
        });

        auth = FirebaseAuth.getInstance();
    }


    private void cadastrar(){

        String email = editTxt_email.getText().toString().trim();
        String senha = editTxt_senha.getText().toString().trim();
        String confirmarSenha = editTxt_confirmasenha.getText().toString().trim();

        if(email.isEmpty()||senha.isEmpty()||confirmarSenha.isEmpty()){

            Toast.makeText(getBaseContext(),
                    "Necessário preencher todos os campos", Toast.LENGTH_LONG)
                    .show();

        }else {

            if(senha.contentEquals(confirmarSenha)){

                if(verificarInternet()){
                    criarUsuário(email,senha);
                }else {
                    Toast.makeText(getBaseContext(),
                            "Erro - Sem conexão com internet", Toast.LENGTH_LONG)
                            .show();
                }

            }else{

                Toast.makeText(getBaseContext(),
                        "Senhas diferentes", Toast.LENGTH_LONG)
                        .show();
            }
        }



    }

    private void limpar(){

    }


    private void criarUsuário(String email, String senha){
        auth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(getBaseContext(),
                                    "Cadastro efetuado com sucesso", Toast.LENGTH_LONG)
                                    .show();
                            back();

                        }else{

                            String resposta = task.getException().toString();
                            opcaoErro(resposta);
                        }
                    }
                });
    }

    private void opcaoErro(String resposta){

        if (resposta.contains("least 6 characters")){
            Toast.makeText(getBaseContext(),
                    "Sua senha deve ter no mínimo 6 caracteres", Toast.LENGTH_LONG)
                    .show();
        }else if (resposta.contains("address is badly")){
            Toast.makeText(getBaseContext(),
                    "E-mail inválido", Toast.LENGTH_LONG)
                    .show();
        }else if (resposta.contains("interrupted connection")){
            Toast.makeText(getBaseContext(),
                    "Sem conexão com Firebase", Toast.LENGTH_LONG)
                    .show();
        }else if (resposta.contains("address is already")){
            Toast.makeText(getBaseContext(),
                    "E-mail já cadastrado", Toast.LENGTH_LONG)
                    .show();
        }else {
            Toast.makeText(getBaseContext(),
                    resposta, Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void back(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private boolean verificarInternet(){

        ConnectivityManager conexao = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo infoNet = conexao.getActiveNetworkInfo();

        if(infoNet != null && infoNet.isConnected()){
            return true;
        }else {
            return false;
        }
    }
}