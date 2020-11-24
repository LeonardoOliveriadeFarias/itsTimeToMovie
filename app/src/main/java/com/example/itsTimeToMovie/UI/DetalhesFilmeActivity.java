package com.example.itsTimeToMovie.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itsTimeToMovie.R;
import com.example.itsTimeToMovie.data.Model.Filme;
import com.squareup.picasso.Picasso;

public class DetalhesFilmeActivity extends AppCompatActivity {

    public static final String EXTRA_FILME =  "EXTRA_FILME";
    private Button comp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        TextView textTituloFilme = findViewById(R.id.text_filme_Title_Detail);
        ImageView imagePoster = findViewById(R.id.imagePoster_Detail);
        TextView textViewDescription = findViewById(R.id.description_filme);

        final Filme filme = (Filme) getIntent().getSerializableExtra(EXTRA_FILME);

        textTituloFilme.setText(filme.getTitle());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w342/" + filme.getPosterPath())
                .into(imagePoster);
        textViewDescription.setText(filme.getDescription());



        comp = (Button) findViewById(R.id.Compartilhar_button);
        comp.setOnClickListener(new View.OnClickListener(){public void onClick(View view){compLink();}});
    }




    public void compLink() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Aqui será enviado o link");
        sendIntent.setType("text/plain");
        if(sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        } else {
            Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT).show();
        }
    }
}