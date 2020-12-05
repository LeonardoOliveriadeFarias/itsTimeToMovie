package com.example.itsTimeToMovie.data.Model;

import android.content.Context;
import android.view.View;

import com.example.itsTimeToMovie.data.database.AppDataBase;
import com.example.itsTimeToMovie.data.database.FactoryDataBase;
import com.example.itsTimeToMovie.data.database.dao.FilmeDAO;

import java.util.List;

public class Favorite {
    private AppDataBase appDataBase;
    public Favorite(Context context) {
        appDataBase = FactoryDataBase.getInstanceAppDataBase(context);
    }

    public void add(Filme filme) {
        FilmeDAO filmeDAO = appDataBase.getFilmeDAO();

        List<Filme> favoritos = filmeDAO.searchAll();

        if(!favoritos.contains(filme)) filmeDAO.save(filme);
    }

    public void remove(Filme filme) {
        FilmeDAO filmeDAO = appDataBase.getFilmeDAO();

        List<Filme> favoritos = filmeDAO.searchAll();

        if(favoritos.contains(filme)) filmeDAO.delete(filme);
    }

    public List<Filme> list() {
        FilmeDAO filmeDAO = appDataBase.getFilmeDAO();

        return filmeDAO.searchAll();
    }

    private Filme save(Filme filme) {
        FilmeDAO filmeDAO = appDataBase.getFilmeDAO();

        if(filmeDAO.searchTitle(filme.getTitle()) == null) {
            filmeDAO.save(filme);
        }

        return filmeDAO.searchTitle(filme.getTitle());
    }
}
