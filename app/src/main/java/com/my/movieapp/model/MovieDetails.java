package com.my.movieapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails {

    @SerializedName("adult")
    private boolean movieAdult;

    @SerializedName("backdrop_path")
    private String movieBackdropPath;

    @SerializedName("belongs_to_collection")
    private BelongsToCollection movieBelongsToCollection;

    @SerializedName("budget")
    private int movieBudget;

    @SerializedName("genres")
    private List<Genres> movieGenres;

    @SerializedName("homepage")
    private String movieHomepage;

    @SerializedName("id")
    private int movieID;

    @SerializedName("imdb_id")
    private String imdbID;

    @SerializedName("original_language")
    private String movieLanguage;

    @SerializedName("original_title")
    private String movieOriginalTitle;

    @SerializedName("overview")
    private String movieOverview;

    @SerializedName("popularity")
    private float moviePopularity;

    @SerializedName("poster_path")
    private String moviePosterPath;

    @SerializedName("production_companies")
    private List<ProductionCompanies> movieProductionCompanies;

    @SerializedName("production_countries")
    private List<ProductionCountries> movieProductionCountries;

    @SerializedName("release_date")
    private String movieReleaseDate;

    @SerializedName("revenue")
    private int movieRevenue;

    @SerializedName("runtime")
    private int movieRuntime;

    @SerializedName("spoken_languages")
    private List<SpokenLanguages> movieSpokenLanguages;

    @SerializedName("status")
    private String movieStatus;

    @SerializedName("tagline")
    private String movieTagline;

    @SerializedName("title")
    private String movieTitle;

    @SerializedName("video")
    private boolean movieVideo;

    @SerializedName("vote_average")
    private double movieVoteAverage;

    @SerializedName("vote_count")
    private int movieVoteCount;

    public List<Genres> getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(List<Genres> movieGenres) {
        this.movieGenres = movieGenres;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public int getMovieRuntime() {
        return movieRuntime;
    }

    public void setMovieRuntime(int movieRuntime) {
        this.movieRuntime = movieRuntime;
    }
}
