package com.example.itsTimeToMovie.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.itsTimeToMovie.data.Model.User;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM user where id_user = (:idUser)")
    User searchId(long idUser);

    @Query("SELECT * FROM user where user = (:user)")
    User searchUser(String user);

    @Insert
    void save(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);


}
