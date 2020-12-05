package com.example.itsTimeToMovie.data.api.presenter;

import com.example.itsTimeToMovie.data.api.contract.CatalogoContract;
import com.example.itsTimeToMovie.data.Model.Filme;
import com.example.itsTimeToMovie.data.mapper.FilmeMapper;
import com.example.itsTimeToMovie.data.api.service.ApiService;
import com.example.itsTimeToMovie.data.api.service.FilmesResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogoPresenter implements CatalogoContract.FilmeListPresenter {

    private CatalogoContract.FilmeListView view;

    public CatalogoPresenter(CatalogoContract.FilmeListView view){
        this.view = view;
    }

    @Override
    public void takePopulares() {
        ApiService.getInstance()
                .obterFilmesPopulares("16b62a2397c0144943747e51552d1229")
                .enqueue(new Callback<FilmesResult>() {
                    @Override
                    public void onResponse(Call<FilmesResult> call, Response<FilmesResult> response) {
                        if(response.isSuccessful()){
                            final List<Filme> filmeList = FilmeMapper
                                    .responseToDomainList(response.body().getResultadoFilmes());

                            view.showPopulares(filmeList);
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
        public void takeMaisVistos(){
            ApiService.getInstance()
                    .obterMaisAssistidos("16b62a2397c0144943747e51552d1229")
                    .enqueue(new Callback<FilmesResult>() {
                        @Override
                        public void onResponse(Call<FilmesResult> call, Response<FilmesResult> response) {
                            if(response.isSuccessful()){
                                final List<Filme> filmeList = FilmeMapper
                                        .responseToDomainList(response.body().getResultadoFilmes());

                                view.showMaisVistos(filmeList);
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
