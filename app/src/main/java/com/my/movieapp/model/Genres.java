package com.my.movieapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Genres {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
