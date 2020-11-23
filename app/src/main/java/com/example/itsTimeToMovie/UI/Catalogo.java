package com.example.itsTimeToMovie.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.itsTimeToMovie.Adapters.FilmeAdapter;
import com.example.itsTimeToMovie.data.api.contract.CatalogoContract;
import com.example.itsTimeToMovie.data.Model.Filme;
import com.example.itsTimeToMovie.R;
import com.example.itsTimeToMovie.data.api.contract.presenter.FilmePopularPresenter;

import java.util.List;

public class Catalogo extends AppCompatActivity
        implements CatalogoContract.FilmeListView,
        FilmeAdapter.ItemFilmeClickListener {

    private FilmeAdapter filmesAdapter;
    private FilmeAdapter maisVistosAdapter;


    CatalogoContract.FilmeListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);


        //configToolbar();
        configAdapter();


        presenter = new FilmePopularPresenter(this);
        presenter.takeFilme();
    }

    /*private void configToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }*/

    private void configAdapter(){
        final RecyclerView rvPopular = findViewById(R.id.recycler_popular);
        final RecyclerView rvAcao = findViewById(R.id.recycler_mais_vistos);

        filmesAdapter = new FilmeAdapter(this);
        RecyclerView.LayoutManager LLM = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        rvPopular.setLayoutManager(LLM);
        rvPopular.setAdapter(filmesAdapter);

        maisVistosAdapter = new FilmeAdapter(this);
        LLM = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        rvAcao.setLayoutManager(LLM);
        rvAcao.setAdapter(maisVistosAdapter);

    }

    @Override
    public void showFilme(List<Filme> filmeList) {
        filmesAdapter.setFilmes(filmeList);
    }

    @Override
    public void showError(){
        Toast.makeText(this,"Erro ao obter lista de filmes",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroyView();
    }

    @Override
    public void onItemFilmeClicked(Filme filme) {
        Intent intent = new Intent(this, DetalhesFilmeActivity.class);
        intent.putExtra(DetalhesFilmeActivity.EXTRA_FILME, filme);
        startActivity(intent);
    }
}