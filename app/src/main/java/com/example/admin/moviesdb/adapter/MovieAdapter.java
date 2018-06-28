package com.example.admin.moviesdb.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.moviesdb.R;
import com.example.admin.moviesdb.main.MainContract;
import com.example.admin.moviesdb.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviewViewHolder> {

    MainContract.Presenter presenter;
    ArrayList<Result> movies;

    public MovieAdapter(MainContract.Presenter presenter, ArrayList<Result> movies) {
        this.presenter = presenter;
        this.movies = movies;
    }

    @Override

    public MoviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_row, parent, false);
        return new MoviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviewViewHolder holder, int position) {
        holder.title.setText(movies.get(position).getOriginalTitle());
        holder.pop.setText(movies.get(position).getPopularity().toString());
        Picasso.with(holder.itemView.getContext()).load("http://image.tmdb.org/t/p/w185/" + movies.get(position).getPosterPath()).into(holder.poster);
        holder.itemView.setId(position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MoviewViewHolder extends RecyclerView.ViewHolder {

        TextView title, pop;
        ImageView poster;

        public MoviewViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvMovieTitle);
            pop = itemView.findViewById(R.id.tvMoviePop);
            poster = itemView.findViewById(R.id.ivPoster);
            itemView.setOnClickListener((View.OnClickListener) presenter);

        }

    }

}
