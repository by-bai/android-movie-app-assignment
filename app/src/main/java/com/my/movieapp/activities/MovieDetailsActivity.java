package com.my.movieapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.my.movieapp.R;
import com.my.movieapp.model.Genres;
import com.my.movieapp.model.MovieDetails;
import com.my.movieapp.networking.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private MovieDetails movieDetails;
    private TextView movieSypnosis;
    private TextView movieGenre;
    private TextView movieLanguage;
    private TextView movieDuration;
    private String movieGenreText;
    private List<Genres> movieGenreList;
    private ImageView backButton;
    private ImageView bookButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieSypnosis = (TextView) findViewById(R.id.movie_sypnosis_text);
        movieGenre = (TextView) findViewById(R.id.movie_genre_text);
        movieLanguage = (TextView) findViewById(R.id.movie_language_text);
        movieDuration = (TextView) findViewById(R.id.movie_duration_text);

        bookButton = (ImageView) findViewById(R.id.book_button);
        bookButton.setOnClickListener(this);
        backButton = (ImageView) findViewById(R.id.back_button);
        backButton.setOnClickListener(this);

        getMovieDetails();
    }

    private void getMovieDetails() {

        Call<MovieDetails> getMovies = ApiClient.movieApi2().getMovieDetails(328111, "328c283cd27bd1877d9080ccb1604c91");

        getMovies.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {

                if (response.isSuccessful()) {
                    Log.d("getMovies", "Request success");
                    Log.d("getMovies", "Request body: " + response.body());
                    movieDetails = response.body();
                    movieSypnosis.setText(movieDetails.getMovieOverview());
                    movieLanguage.setText(movieDetails.getMovieLanguage());
                    movieGenreList = movieDetails.getMovieGenres();
                    movieGenreText = "";
                    for (int i = 0; i < movieGenreList.size(); i++ ) {

                        if (i == (movieGenreList.size() - 1)) {
                            movieGenreText += movieGenreList.get(i).getName();
                        } else {
                            movieGenreText += movieGenreList.get(i).getName() + ", ";
                        }

                    }
                    movieGenre.setText(movieGenreText);
                    movieDuration.setText(String.valueOf(movieDetails.getMovieRuntime()) + " minutes");
                }


            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Log.d("getMovies", "Request failure");
            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.book_button:
                startActivity(new Intent(getApplicationContext(), WebViewActivity.class));
                break;

            case R.id.back_button:
                startActivity(new Intent(getApplicationContext(), MovieListActivity.class));
                break;

        }
    }

}
