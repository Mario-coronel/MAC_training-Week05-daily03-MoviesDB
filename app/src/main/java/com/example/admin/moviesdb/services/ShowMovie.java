package com.example.admin.moviesdb.services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.moviesdb.R;
import com.squareup.picasso.Picasso;

public class ShowMovie extends AppCompatActivity {

    TextView title, pop, vote, language;
    ImageView poster;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie);
        bundle = getIntent().getExtras();
        title = findViewById(R.id.tvMovieTitle);
        pop = findViewById(R.id.tvRate);
        vote = findViewById(R.id.tvVoteCount);
        language = findViewById(R.id.tvLanguage);
        poster = findViewById(R.id.ivPoster);
        title.setText("Title :" + bundle.getString("title"));
        pop.setText("Vote Average : " + (bundle.getString("pop", "not provided")));
        vote.setText("Vote Count : " + (bundle.getString("count")));
        language.setText("Original Language : " + bundle.getString("lan"));
        Picasso.with(poster.getContext()).load("http://image.tmdb.org/t/p/w185/" + bundle.getString("path", "not")).into(poster);
    }
}
