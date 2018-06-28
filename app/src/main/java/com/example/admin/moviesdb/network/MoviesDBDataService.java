package com.example.admin.moviesdb.network;

import com.example.admin.moviesdb.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesDBDataService {



    @GET("/3/search/movie")
    Call<Movie> getMoviesResult(@Query("api_key") String apiKey, @Query("query") String search);

}
