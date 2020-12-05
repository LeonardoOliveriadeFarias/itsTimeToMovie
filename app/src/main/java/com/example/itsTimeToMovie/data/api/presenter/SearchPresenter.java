package com.example.itsTimeToMovie.data.api.presenter;

import com.example.itsTimeToMovie.UI.ResultSearchActivity;
import com.example.itsTimeToMovie.data.Model.Filme;
import com.example.itsTimeToMovie.data.api.contract.SearchContract;
import com.example.itsTimeToMovie.data.api.service.ApiService;
import com.example.itsTimeToMovie.data.api.service.FilmesResult;
import com.example.itsTimeToMovie.data.mapper.FilmeMapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements SearchContract.SearchPreseter {

    private SearchContract.SearchView view;

    public SearchPresenter(SearchContract.SearchView view) {this.view = view;}

    @Override
    public void takeMovies(String title) {

        ApiService.getInstance()
                .searchMovies("b62a2397c0144943747e51552d1229", title)
                .enqueue(new Callback<FilmesResult>() {
                    @Override
                    public void onResponse(Call<FilmesResult> call, Response<FilmesResult> response) {
                        if (response.isSuccessful()) {
                            final List<Filme> filmeList = FilmeMapper
                                    .responseToDomainList(response.body().getResultadoFilmes());

                            view.showMovies(filmeList);
                        } else {
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
