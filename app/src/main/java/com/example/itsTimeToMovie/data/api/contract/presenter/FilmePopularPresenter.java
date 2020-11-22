package com.example.itsTimeToMovie.data.api.contract.presenter;

import com.example.itsTimeToMovie.data.api.contract.CatalogoContract;
import com.example.itsTimeToMovie.data.Model.Filme;
import com.example.itsTimeToMovie.data.mapper.FilmeMapper;
import com.example.itsTimeToMovie.data.api.ApiService;
import com.example.itsTimeToMovie.data.api.results.FilmesResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmePopularPresenter implements CatalogoContract.FilmeListPresenter {

    private CatalogoContract.FilmeListView view;

    public FilmePopularPresenter(CatalogoContract.FilmeListView view){
        this.view = view;
    }

    @Override
    public void takeFilme() {
        ApiService.getInstance()
                .obterFilmesPopulares("16b62a2397c0144943747e51552d1229")
                .enqueue(new Callback<FilmesResult>() {
                    @Override
                    public void onResponse(Call<FilmesResult> call, Response<FilmesResult> response) {
                        if(response.isSuccessful()){
                            final List<Filme> filmeList = FilmeMapper
                                    .responseToDomain(response.body().getResultadoFilmes());

                            view.showFilme(filmeList);
                        }else {
                            view.showError();
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmesResult> call, Throwable t) {
                        view.showError();
                    }
                });

        ApiService.getInstance()
                .obterMaisAssistidos("16b62a2397c0144943747e51552d1229")
                .enqueue(new Callback<FilmesResult>() {
                    @Override
                    public void onResponse(Call<FilmesResult> call, Response<FilmesResult> response) {
                        if(response.isSuccessful()){
                            final List<Filme> filmeList = FilmeMapper
                                    .responseToDomain(response.body().getResultadoFilmes());

                            view.showFilme(filmeList);
                        }else {
                            view.showError();
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmesResult> call, Throwable t) {
                        view.showError();
                    }
                });


            }



    @Override
    public void destroyView() {
        this.view = null;
    }
}
