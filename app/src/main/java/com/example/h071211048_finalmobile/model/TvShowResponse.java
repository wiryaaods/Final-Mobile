package com.example.h071211048_finalmobile.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowReponse {
    @SerializedName("results")
    private List<TvShowResult> tvshowResults;

    public List<TvShowResult> getTvshowResults(){
        return tvshowResults;
    }
}}
