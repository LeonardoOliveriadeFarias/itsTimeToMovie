package com.example.itsTimeToMovie.data.api.service;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiService {

    private static FilmeService INSTANCE;

    public static FilmeService getInstance(){
        if(INSTANCE==null){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

       INSTANCE = retrofit.create(FilmeService.class);
        }

        return INSTANCE;
    }

}
