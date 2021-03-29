package com.my.movieapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.my.movieapp.R;
import com.my.movieapp.model.Genres;
import com.my.movieapp.model.MovieDetails;
import com.my.movieapp.networking.ApiClient;
import com.my.movieapp.viewmodel.MovieDetailsViewModel;
import com.my.movieapp.viewmodel.MovieListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private MovieDetails movieDetails;
    MovieDetailsViewModel movieDetailsViewModel;

    private TextView movieSypnosis;
    private TextView movieGenre;
    private TextView movieLanguage;
    private TextView movieDuration;
    private String movieGenreText;
    private List<Genres> movieGenreList;
    private ImageView backButton;
    private ImageView bookButton;
    private int movieID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        //initialise
        movieSypnosis = findViewById(R.id.movie_sypnosis_text);
        movieGenre = findViewById(R.id.movie_genre_text);
        movieLanguage = findViewById(R.id.movie_language_text);
        movieDuration = findViewById(R.id.movie_duration_text);
        bookButton = findViewById(R.id.book_button);
        bookButton.setOnClickListener(this);
        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(this);

        //get clicked movie id from previous activity
        Log.d("movie id", getIntent().getStringExtra("id")+"");
        movieID = Integer.parseInt(getIntent().getStringExtra("id"));

        //view model
        movieDetailsViewModel= new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MovieDetailsViewModel.class);
        movieDetailsViewModel.init(movieID);
        movieDetailsViewModel.getMovieDetails().observe(this, movieDetails -> {

            if (movieDetails != null) {
                setMovieText(movieDetails);

            }
        });
    }

    private void setMovieText(MovieDetails movieDetails) {

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
