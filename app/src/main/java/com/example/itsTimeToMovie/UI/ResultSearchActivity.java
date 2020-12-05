package com.example.itsTimeToMovie.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.itsTimeToMovie.Adapters.FilmeAdapter;
import com.example.itsTimeToMovie.R;
import com.example.itsTimeToMovie.data.Model.Filme;
import com.example.itsTimeToMovie.data.api.contract.SearchContract;
import com.example.itsTimeToMovie.data.api.presenter.SearchPresenter;

import java.util.List;

public class ResultSearchActivity extends AppCompatActivity
implements SearchContract.SearchView,
        FilmeAdapter.ItemFilmeClickListener {

    private FilmeAdapter adapter;
    private SearchContract.SearchPreseter presenter;
    private EditText searchBar;
    private Button submit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);

        searchBar = findViewById(R.id.searchBar);
        submit = (Button) findViewById(R.id.submit);
        if(submit.isClickable()){
            String search = searchBar.getText().toString();
            configAdapter();

            presenter = new SearchPresenter(this);
            presenter.takeMovies(search);
        }



    }

    @Override
    public void onItemFilmeClicked(Filme filme) {

    }

    private void configAdapter(){
        final RecyclerView result = findViewById(R.id.recycler_result);

        adapter = new FilmeAdapter(this);
        RecyclerView.LayoutManager LLM = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        result.setLayoutManager(LLM);
        result.setAdapter(adapter);
    }



    public void onItemFilmeClick(Filme filme) {
        Intent intent = new Intent(this, DetalhesFilmeActivity.class);
        intent.putExtra(DetalhesFilmeActivity.EXTRA_FILME, filme);
        startActivity(intent);
    }

    @Override
    public void showMovies(List<Filme> filmeList) {adapter.setFilmes(filmeList);}

    @Override
    public void showError() {
        Toast.makeText(this,"Erro ao obter lista de filmes",Toast.LENGTH_SHORT).show();
    }
}