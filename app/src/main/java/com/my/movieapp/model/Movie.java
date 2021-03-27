package com.my.movieapp.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("id")
    private int movieID;

    @SerializedName("original_title")
    private String movieTitle;

    @SerializedName("popularity")
    private String moviePopularity;

    @SerializedName("poster_path")
    private String moviePoster;

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMoviePopularity() {
        return moviePopularity;
    }

    public void setMoviePopularity(String moviePopularity) {
        this.moviePopularity = moviePopularity;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }
}
