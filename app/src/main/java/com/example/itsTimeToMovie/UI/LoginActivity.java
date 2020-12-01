package com.example.itsTimeToMovie.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.itsTimeToMovie.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private EditText editTxt_email, editTxt_senha;
    private Button btmLogin, btmCadastrar, btmRecuperar;
    private ImageButton btmLoginGoogle;
    private FirebaseAuth authEmail;
    private FirebaseAuth authGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTxt_email = (EditText) findViewById(R.id.editLogin) ;
        editTxt_senha = (EditText) findViewById(R.id.editSenha) ;

        authGoogle = FirebaseAuth.getInstance();

        btmLogin = findViewById(R.id.BttmLogin);
        btmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {loginEmail();}
        });

        btmCadastrar = findViewById(R.id.BttmCadastrar);
        btmCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {cadastrar();}
        });

        btmRecuperar = findViewById(R.id.recuperar);
        btmRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {recuperarSenha();}
        });

        authEmail = FirebaseAuth.getInstance();
        criarRequisição();
        btmLoginGoogle = findViewById(R.id.BttmGoogle);
        btmLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleLogin();
            }
        });


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

    public void login(){
        Intent intent = new Intent(this, Catalogo.class);
        startActivity(intent);
    }

    private void recuperarSenha(){
        String email = editTxt_email.getText().toString().trim();
        if(email.isEmpty()){
            Toast.makeText(getBaseContext(),
                    "Insira seu email para recuperar a senha",
                    Toast.LENGTH_LONG).show();
        } else{
            enviarEmail(email);
        }
    }

    private void enviarEmail(String email){
        authEmail.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getBaseContext(), "Enviamos email para redefinição de senha",
                        Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getBaseContext(), "Erro ao enviar email ou email inválido",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void cadastrar(){
        Intent intent = new Intent(this, CadastrarActivity.class);
        startActivity(intent);
    }

    public void criarRequisição(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void googleLogin() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this,"Erro ao Conectar com o Google", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        authGoogle.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = authGoogle.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), Catalogo.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Erro ao logar com Google", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}