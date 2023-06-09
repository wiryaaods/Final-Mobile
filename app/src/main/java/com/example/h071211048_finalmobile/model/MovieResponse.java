package com.example.h071211048_finalmobile.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    private List<MovieResult> movieResults;

    public List<MovieResult> getResults(){

        return movieResults;
    }

}
