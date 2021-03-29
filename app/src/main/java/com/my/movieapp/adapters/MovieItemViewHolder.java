package com.my.movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.my.movieapp.R;
import com.my.movieapp.activities.MovieDetailsActivity;
import com.my.movieapp.model.Movies;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.movie_title)
    TextView movieTitle;

    @BindView(R.id.movie_rating)
    TextView movieRating;

    @BindView(R.id.movie_poster)
    ImageView moviePoster;

    @BindView(R.id.movie_details_button)
    TextView movieDetailsButton;


    private MovieItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindTo(Movies movies) {

        movieTitle.setText(movies.getMovieTitle());
        movieRating.setText(String.valueOf(movies.getMoviePopularity()));
        Glide.with(itemView.getContext())
                .load("https://image.tmdb.org/t/p/w342" + movies.getMoviePoster())
                .into(moviePoster);
        movieDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(movieTitle.getContext(), MovieDetailsActivity.class);
                intent.putExtra("id", String.valueOf(movies.getMovieID()));

                movieTitle.getContext().startActivity(intent);
            }
        });
    }

    public static MovieItemViewHolder create(ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_movie, parent, false);
        return new MovieItemViewHolder(view);
    }


}
