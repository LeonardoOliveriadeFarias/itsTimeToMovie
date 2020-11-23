package com.example.itsTimeToMovie.data.Model;

import java.io.Serializable;

public class Filme implements Serializable {

    private final String title;
    private final String posterPath;
    private final String description;
    public Filme(String name, String posterPath, String description) {
        this.title = name;
        this.posterPath = posterPath;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {return posterPath;}

    public String getDescription() {return description;}
}