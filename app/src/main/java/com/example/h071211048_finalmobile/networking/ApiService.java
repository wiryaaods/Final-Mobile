package com.example.h071211048_finalmobile.networking;

import com.example.h071211048_finalmobile.model.MovieResponse;
import com.example.h071211048_finalmobile.model.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("3/movie/{category}")
    Call<MovieResponse> getMovie(
            @Path("category") String category,
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page")int page);

    @GET("3/tv/{category}")
    Call<TvShowResponse> getTv(
            @Path("category") String category,
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page")int page);
}
