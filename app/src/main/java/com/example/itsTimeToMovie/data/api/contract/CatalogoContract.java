package com.example.itsTimeToMovie.data.api.contract;

import com.example.itsTimeToMovie.data.Model.Filme;

import java.util.List;

public interface CatalogoContract {

    interface FilmeListView{

        void showFilme(List<Filme> filmeList);
        void showError();
    }

    interface FilmeListPresenter{

        void takeFilme();
        void destroyView();

    }
}
