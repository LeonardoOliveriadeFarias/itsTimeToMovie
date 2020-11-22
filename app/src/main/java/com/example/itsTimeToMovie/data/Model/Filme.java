package com.example.itsTimeToMovie.data.Model;

import java.io.Serializable;

public class Filme implements Serializable {

    private final String title;
    private final String posterPath;
    public Filme(String name, String posterPath) {
        this.title = name;
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {return posterPath;}
}