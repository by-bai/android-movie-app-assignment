package com.my.movieapp.model;

import com.google.gson.annotations.SerializedName;

public class ProductionCompanies {

    @SerializedName("id")
    private int id;

    @SerializedName("logo_path")
    private String logoPath;

    @SerializedName("name")
    private String name;

    @SerializedName("origin_country")
    private String originCountry;


}
