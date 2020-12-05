package com.example.itsTimeToMovie.data.database.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserFavorite {
    @ColumnInfo(name = "id_user")
    @PrimaryKey(autoGenerate = true)
    public long idUser;

    @ColumnInfo(name = "id")
    public long idFilme;

    public UserFavorite(long idUser, long idFilme) {
        this.idUser = idUser;
        this.idFilme = idFilme;
    }
}
