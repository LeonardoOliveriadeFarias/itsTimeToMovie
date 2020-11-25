package com.example.itsTimeToMovie.data.api.contract;

import com.example.itsTimeToMovie.data.Model.Filme;

import java.util.List;

public interface CatalogoContract {

    interface FilmeListView{

        void showPopulares(List<Filme> filmeList);
        void showError();
        void showMaisVistos(List<Filme> filmeList);
    }

    interface FilmeListPresenter{

        void takePopulares();
        void takeMaisVistos();
        void destroyView();

    }
}
