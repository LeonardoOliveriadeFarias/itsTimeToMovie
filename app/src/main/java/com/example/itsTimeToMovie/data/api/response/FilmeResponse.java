package com.example.itsTimeToMovie.data.api.response;

import com.squareup.moshi.Json;

public class FilmeResponse {

    @Json(name = "poster_path")
    private final String posterPath;

    @Json(name = "original_title")
    private final String title;

    @Json(name = "title")
    private final String name;




    public FilmeResponse(String posterPath, String title, String name) {
        this.posterPath = posterPath;
        this.title = title;
        this.name = name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getName() { return name;}
}

