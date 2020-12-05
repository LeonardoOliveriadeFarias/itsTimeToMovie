package com.example.itsTimeToMovie.data.Model;

import java.io.Serializable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Filme implements Serializable {


    @ColumnInfo(name = "title")
    private  String title;
    @ColumnInfo(name = "posterPath")
    private  String posterPath;
    @ColumnInfo(name = "description")
    private  String description;
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    public Filme(String title, String posterPath, String description, int id) {

        this.title = title;
        this.posterPath = posterPath;
        this.description = description;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }


    public String getPosterPath() {return posterPath;}

    public String getDescription() {return description;}

    public int getId() {return id;}

    public void setTitle(String title) {this.title = title;}

    public void setPosterPath(String posterPath) {this.posterPath = posterPath;}

    public void setDescription(String description) {this.description = description;}

    public void setId(int id) {this.id = id;}

    @Override
    public boolean equals(Object o) {
        Filme f = (Filme) o;

        return title.equals(f.title);
    }

    @Override
    public int hashCode() {
        return id;
    }
}