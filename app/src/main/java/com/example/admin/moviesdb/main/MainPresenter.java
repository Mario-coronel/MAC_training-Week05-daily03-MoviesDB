package com.example.admin.moviesdb.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.moviesdb.R;
import com.example.admin.moviesdb.adapter.MovieAdapter;
import com.example.admin.moviesdb.model.Movie;
import com.example.admin.moviesdb.model.Result;
import com.example.admin.moviesdb.network.MoviesDBDataService;
import com.example.admin.moviesdb.network.RetrofitInstance;
import com.example.admin.moviesdb.services.FetchMovies;
import com.example.admin.moviesdb.services.ShowMovie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter, View.OnClickListener{

    public static MainPresenter mainPresenter;

    static MainActivity mainView;
    RecyclerView recyclerView;
    MovieAdapter adapter;
    ArrayList<Result> moives;

    private MainPresenter(){

    }

    public static MainPresenter getMainPresenter() {
        if (mainPresenter != null) {
            return mainPresenter;
        }else {
            return new MainPresenter();
        }

    }

    public void setMainView(MainActivity mainActivity) {
        this.mainView = mainActivity;
    }

    public MainActivity getMainView() {
        return mainView;
    }

    @Override
    public void onClick(View view) {
        Intent showMovie = new Intent(mainView, ShowMovie.class);
        showMovie.putExtra("title", moives.get(view.getId()).getOriginalTitle());
        showMovie.putExtra("pop", moives.get(view.getId()).getVoteAverage().toString());
        showMovie.putExtra("count", moives.get(view.getId()).getVoteCount().toString());
        showMovie.putExtra("lan", moives.get(view.getId()).getOriginalLanguage());
        showMovie.putExtra("path", moives.get(view.getId()).getPosterPath());
        mainView.startActivity(showMovie);

    }

    @Override
    public void getResults() {


    }

    public void generateMoviesList(List<Result> results) {
        moives = new ArrayList(results);
        recyclerView = mainView.findViewById(R.id.recycler_view_movie_list);
        adapter = new MovieAdapter(this,moives);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mainView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

}
