package com.example.itsTimeToMovie.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.itsTimeToMovie.R;
import com.example.itsTimeToMovie.data.Model.Filme;

public class DetalhesFilmeActivity extends AppCompatActivity {

    public static final String EXTRA_FILME =  "EXTRA_FILME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        TextView textTituloFilme = findViewById(R.id.text_filme_Title);

        final Filme filme = (Filme) getIntent().getSerializableExtra(EXTRA_FILME);

        textTituloFilme.setText(filme.getTitle());
    }
}