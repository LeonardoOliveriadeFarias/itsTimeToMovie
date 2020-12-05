package com.example.itsTimeToMovie.data.api.contract;

import com.example.itsTimeToMovie.data.Model.Filme;

import java.util.List;

public interface SearchContract {

    interface SearchView{
        void showMovies(List<Filme> filmeList);
        void showError();
    }

    interface SearchPreseter{
        void takeMovies(String title);
        void destroyView();
    }
}
