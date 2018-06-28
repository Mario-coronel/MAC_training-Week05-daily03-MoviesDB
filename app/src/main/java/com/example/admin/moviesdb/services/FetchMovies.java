package com.example.admin.moviesdb.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.moviesdb.R;
import com.example.admin.moviesdb.adapter.MovieAdapter;
import com.example.admin.moviesdb.main.MainActivity;
import com.example.admin.moviesdb.main.MainContract;
import com.example.admin.moviesdb.main.MainPresenter;
import com.example.admin.moviesdb.model.Movie;
import com.example.admin.moviesdb.model.Result;
import com.example.admin.moviesdb.network.MoviesDBDataService;
import com.example.admin.moviesdb.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchMovies extends Service {

    IBinder iBinder = new FetchBinder();
    final String apiKey = "ed1a8992e2a1c2b5e3aa88195a20bf08";
    Movie movie;
    MainPresenter presenter;


    public FetchMovies() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    public void getData(String search) {
        presenter = MainPresenter.getMainPresenter();
        MoviesDBDataService service = RetrofitInstance.getRetrofitInstance().create(MoviesDBDataService.class);
        Call<Movie> call = service.getMoviesResult(apiKey, search);
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movie = response.body();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(presenter.getMainView(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
        if (movie != null) {
            presenter.generateMoviesList(movie.getResults());
        }


    }


    public class FetchBinder extends Binder {
        public FetchMovies getService() {
            return FetchMovies.this;
        }
    }

}
