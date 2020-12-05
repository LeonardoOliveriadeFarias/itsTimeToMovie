package com.example.itsTimeToMovie.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.itsTimeToMovie.Adapters.FilmeAdapter;
import com.example.itsTimeToMovie.R;
import com.example.itsTimeToMovie.UI.DetalhesFilmeActivity;
import com.example.itsTimeToMovie.data.Model.Filme;
import com.example.itsTimeToMovie.data.Model.User;
import com.example.itsTimeToMovie.data.api.contract.FavoriteContract;
import com.example.itsTimeToMovie.data.api.presenter.FavoritePresenter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity
        implements FavoriteContract.listFavoriteView,
        FilmeAdapter.ItemFilmeClickListener
{
    private User user;
    private FilmeAdapter favoriteAdapter;
    private FavoriteContract.ListFavoritePresenter presenter;
    private FirebaseAuth mAuth;
    private View fView;

    SensorManager sensorManager;
    Sensor sensor;
    long tempoEvento;

    public static final String EXTRA_USUARIO = "EXTRA_USUARIO";


    @Nullable
    public View onCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater
                .inflate(R.layout.activity_favorite, container, false);

        fView = view.findViewById(R.id.fView);
        return view;
    }
    @Override
    public void onStart() {
        user = (User) getIntent().getSerializableExtra(EXTRA_USUARIO);

        configAdapter();

        presenter = new FavoritePresenter( this,user);

        presenter.takeFavorite(getApplication());
        super.onStart();

    }

    private void configAdapter(){
        final RecyclerView favorites = findViewById(R.id.favorite_list);


        RecyclerView.LayoutManager gridLayoutManager =
                new GridLayoutManager(getApplicationContext(), 2);

        favorites.setLayoutManager(gridLayoutManager);

        favorites.setAdapter(favoriteAdapter);

    }


    @Override
    public void onItemFilmeClicked(Filme filme) {
        Intent intent =
                new Intent( getApplicationContext(), DetalhesFilmeActivity.class);
        intent.putExtra(DetalhesFilmeActivity.EXTRA_FILME, filme.getId());
        startActivity(intent);
    }

    @Override
    public void showFavorite(List<Filme> favoritos) {favoriteAdapter.setFilmes(favoritos);}

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), "Erro ao obter lista de filmes",
                Toast.LENGTH_LONG).show();
    }
}