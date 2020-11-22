package com.example.itsTimeToMovie.data.mapper;

import com.example.itsTimeToMovie.data.Model.Filme;
import com.example.itsTimeToMovie.data.api.response.FilmeResponse;

import java.util.ArrayList;
import java.util.List;

public class FilmeMapper {

    public static List<Filme> responseToDomain(List<FilmeResponse> filmeResponseList){
        List<Filme> filmeList = new ArrayList<>();

        for (FilmeResponse filmeResponse : filmeResponseList){
            final Filme filme = new Filme(filmeResponse.getTitle(), filmeResponse.getPosterPath());
            filmeList.add(filme);
        }

        return filmeList;
    }

}
