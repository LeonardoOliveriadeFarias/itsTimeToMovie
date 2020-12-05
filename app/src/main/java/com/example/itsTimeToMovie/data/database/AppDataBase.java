package com.example.itsTimeToMovie.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.itsTimeToMovie.data.Model.Filme;
import com.example.itsTimeToMovie.data.Model.User;
import com.example.itsTimeToMovie.data.database.dao.FavoriteDAO;
import com.example.itsTimeToMovie.data.database.dao.FilmeDAO;
import com.example.itsTimeToMovie.data.database.dao.UserDAO;
import com.example.itsTimeToMovie.data.database.dao.UserFavorite;

@Database(entities = {User.class, Filme.class, UserFavorite.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDAO getUsuarioDAO();
    public abstract FilmeDAO getFilmeDAO();
    public abstract FavoriteDAO getFavoritosDAO();
}
