package com.example.itsTimeToMovie.data.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface FavoriteDAO {
    @Transaction
    @Query("select * from user where id_user = (:idUser)")
    UserWithFavorite getFavoriteUser(long idUser);

    @Insert
    void save(UserFavorite userfavorite);
}
