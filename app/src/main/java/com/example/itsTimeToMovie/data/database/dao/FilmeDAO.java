package com.example.itsTimeToMovie.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.itsTimeToMovie.data.Model.Filme;

@Dao
public interface FilmeDAO {
    @Query("SELECT * FROM filme where id = (:idFilme)")
    Filme searchId(long idFilme);

    @Query("SELECT * FROM filme where title = (:title)")
    Filme searchTitle(String title);

    @Query("SELECT * FROM filme")
    List<Filme> searchAll();


    @Insert
    void save(Filme filme);

    @Delete
    void delete(Filme filme);

    @Update
    void update(Filme filme);
}
