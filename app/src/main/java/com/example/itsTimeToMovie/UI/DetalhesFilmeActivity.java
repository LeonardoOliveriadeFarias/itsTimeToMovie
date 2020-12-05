package com.example.itsTimeToMovie.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itsTimeToMovie.R;
import com.example.itsTimeToMovie.data.Model.Favorite;
import com.example.itsTimeToMovie.data.Model.Filme;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DetalhesFilmeActivity extends AppCompatActivity{


    public static final String EXTRA_FILME =  "EXTRA_FILME";

    private Button compLink, favorite;
    private Filme filme;
    private Context context ;


    TextView textTituloFilme;
    ImageView imagePoster;
    TextView textViewDescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        textTituloFilme = findViewById(R.id.text_filme_Title_Detail);
        imagePoster = findViewById(R.id.imagePoster_Detail);
        textViewDescription = findViewById(R.id.description_filme);


        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("*/*".equals(type)) {
                Uri arqUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
                if (arqUri != null) {
                    try {
                        FileInputStream fis;
                        fis = openFileInput(arqUri.getPath());
                        fis.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        } else {
            final Filme filme = (Filme) getIntent().getSerializableExtra(EXTRA_FILME);

            textTituloFilme.setText(filme.getTitle());
            Picasso.get()
                    .load("https://image.tmdb.org/t/p/w342/" + filme.getPosterPath())
                    .into(imagePoster);
            textViewDescription.setText(filme.getDescription());
        }

        compLink = (Button) findViewById(R.id.Compartilhar_link);
        compLink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){checarPermissao();}
        });
    }




    public void sharedLink(Filme filme) {
        filme =  (Filme) getIntent().getSerializableExtra(EXTRA_FILME);
        imagePoster = findViewById(R.id.imagePoster_Detail);
        Drawable drawable = imagePoster.getDrawable();
        Bitmap b = ((BitmapDrawable)drawable).getBitmap();

        Intent share = new Intent(Intent.ACTION_SEND);

        share.setType("*/*");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Titulo da Imagem", null);
        Uri imageUri =  Uri.parse(path);

        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        share.putExtra(Intent.EXTRA_TEXT, "http://itstimetomovie.edu.br?id="+filme.getId());

        if(share.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(share, "Selecione"));
        } else {
            Toast.makeText(getApplicationContext(), "falhou", Toast.LENGTH_SHORT).show();
        }
    }

    private static final int SOLICITAR_PERMISSAO = 1;
    private void checarPermissao(){
        // Verifica  o estado da permissão de WRITE_EXTERNAL_STORAGE
        int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // Se for diferente de PERMISSION_GRANTED, então vamos exibir a tela padrão
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, SOLICITAR_PERMISSAO);
            } else {
            // Senão vamos compartilhar a imagem
            sharedLink(filme);
        }

    }



    public void removeFavorite(View view) {
        new Thread() {
            @Override
            public void run() {
                context = DetalhesFilmeActivity.this;
                Favorite favorite = new Favorite(context);
                favorite.remove(filme);
            }
        }.start();
    }


    public void addFavorite(View view) {
        new Thread() {
            @Override
            public void run() {
                context = DetalhesFilmeActivity.this;
                Favorite favorite = new Favorite(context);
                favorite.add(filme);
            }
        }.start();
    }
}
