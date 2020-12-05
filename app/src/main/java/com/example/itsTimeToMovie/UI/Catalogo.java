package com.example.itsTimeToMovie.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.itsTimeToMovie.Adapters.FilmeAdapter;
import com.example.itsTimeToMovie.data.Model.Favorite;
import com.example.itsTimeToMovie.data.api.contract.CatalogoContract;
import com.example.itsTimeToMovie.data.Model.Filme;
import com.example.itsTimeToMovie.R;
import com.example.itsTimeToMovie.data.api.presenter.CatalogoPresenter;

import java.util.List;

public class Catalogo extends AppCompatActivity
        implements CatalogoContract.FilmeListView,
        FilmeAdapter.ItemFilmeClickListener {

    private FilmeAdapter filmesAdapter;
    private FilmeAdapter maisVistosAdapter;
    private Button favoriteBttn, searchBttn;

    SensorManager sensorManager;
    Sensor sensor;
    long tempoEvento;


    CatalogoContract.FilmeListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        configAdapter();
        favoriteBttn = (Button) findViewById(R.id.favorite_bttn);
        favoriteBttn.setOnClickListener(new View.OnClickListener(){public void onClick(View view){favoriteListStart();}});
        searchBttn = (Button) findViewById(R.id.searchBttn);
        searchBttn.setOnClickListener(new View.OnClickListener(){public void onClick(View view){searchFilm();}});

        presenter = new CatalogoPresenter(this);
        presenter.takePopulares();
        presenter.takeMaisVistos();
    }


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

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (sensor == null){
            Log.e("Sensor","SENSOR não encontrado");
        }

    }

    @Override
    public void showPopulares(List<Filme> filmeList) {
        filmesAdapter.setFilmes(filmeList);
    }

    @Override
    public void showError(){
        Toast.makeText(this,"Erro ao obter lista de filmes",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMaisVistos(List<Filme> filmeList) {
        maisVistosAdapter.setFilmes(filmeList);
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


    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d("sensor",sensor.getName());
        final RecyclerView rvPopular = findViewById(R.id.recycler_popular);
        final RecyclerView rvAcao = findViewById(R.id.recycler_mais_vistos);


        if (sensorEvent.sensor == sensor && sensorEvent.timestamp - tempoEvento > 2000) {

            String d = "";
            float x,y,z;
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            z = sensorEvent.values[2];

            for(float f: sensorEvent.values) {
                d += f+", ";
            }
            if ( y > 3) {
                Log.d("Sensor1", d);
                rvPopular.scrollBy(300,0);
                rvAcao.scrollBy(300,0);
                tempoEvento = sensorEvent.timestamp;
            } else {
                if ( y < -3) {
                    Log.d("Sensor2",  ""+y);
                    rvPopular.scrollBy(-300,0);
                    rvAcao.scrollBy(-300,0);
                    tempoEvento = sensorEvent.timestamp;
                }
            }
        }
    }

    public void favoriteListStart(){
        Intent intent = new Intent(this, Favorite.class);
        startActivity(intent);
    }

    public void searchFilm(){
        Intent intent = new Intent(this, ResultSearchActivity.class);
        startActivity(intent);
    }

}