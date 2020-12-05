package com.example.itsTimeToMovie.data.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_user")
    private long uid;

    @ColumnInfo(name = "user")
    private String user;

    @ColumnInfo(name = "password")
    private String password;

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUser() {
        return user;
    }

    public void setUsuario(String usuario) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = password;
    }
}
