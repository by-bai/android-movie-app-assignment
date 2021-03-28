package com.my.movieapp.model;

import com.google.gson.annotations.SerializedName;

public class BelongsToCollection {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("poster_path")
    private String posterPath;


    @SerializedName("backdrop_path")
    private String backdropPath;


}
