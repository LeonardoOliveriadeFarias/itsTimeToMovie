package com.example.itsTimeToMovie.data.api.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FilmeService {

    @GET("movie/popular")
    Call<FilmesResult> obterFilmesPopulares(

            @Query("api_key") String chaveApi);


    @GET("movie/top_rated")
    Call<FilmesResult> obterMaisAssistidos(

            @Query("api_key") String chaveApi);


    @GET("movie/{Id}")
    Call<FilmeResponse> obterFilme(

            @Query("api_key") String chaveApi);

    @GET("/search/movie")
    Call<FilmesResult> searchMovies(

            @Query("api_key") String chaveApi,
            @Query("query") String titleFilme);
}
