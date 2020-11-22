package com.example.itsTimeToMovie.data.api;

import com.example.itsTimeToMovie.data.api.results.FilmesResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmeService {

    @GET("movie/popular")
    Call<FilmesResult> obterFilmesPopulares(@Query("api_key") String chaveApi);

    @GET("/movie/top_rated")
    Call<FilmesResult> obterMaisAssistidos(@Query("api_key") String chaveApi);
}
