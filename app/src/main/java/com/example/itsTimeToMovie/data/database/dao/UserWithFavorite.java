package com.example.itsTimeToMovie.data.database.dao;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.itsTimeToMovie.data.Model.Filme;
import com.example.itsTimeToMovie.data.Model.User;

import java.util.List;

public class UserWithFavorite {
    @Embedded
    public User user;

    @Relation(
            parentColumn = "id_user",
            entityColumn = "id",
            associateBy = @Junction(UserFavorite.class)
    )
    public List<Filme> favoritos;
}
