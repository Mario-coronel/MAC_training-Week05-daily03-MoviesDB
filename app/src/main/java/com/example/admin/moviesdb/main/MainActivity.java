package com.example.admin.moviesdb.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.admin.moviesdb.R;
import com.example.admin.moviesdb.services.FetchMovies;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements MainContract.View, ServiceConnection {
    MainPresenter presenter;
    boolean isBounded;
    FetchMovies fetchMovies;
    Intent fetchIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = MainPresenter.getMainPresenter();
        presenter.setMainView(this);
        fetchIntent = new Intent(this, FetchMovies.class);
        bindService(fetchIntent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void showResults() {
        if (isBounded) {
            fetchMovies.getData(((EditText) (findViewById(R.id.edSearch))).getText().toString());

        } else {
            bindService(fetchIntent, this, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void showMovie() {

    }


    public void showResults(View view) {
        showResults();

    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        FetchMovies.FetchBinder binder = (FetchMovies.FetchBinder) iBinder;
        fetchMovies = binder.getService();
        isBounded = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        isBounded = false;
    }
}
