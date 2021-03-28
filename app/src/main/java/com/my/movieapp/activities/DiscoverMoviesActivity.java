package com.my.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.my.movieapp.R;
import com.my.movieapp.model.Movies;
import com.my.movieapp.model.MoviesResponse;
import com.my.movieapp.networking.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverMoviesActivity extends AppCompatActivity {

    private List<Movies> moviesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_movies);

        getMovies();
    }

    private void getMovies(){

        Call<MoviesResponse> getMovies = ApiClient.movieApi().getMovies("328c283cd27bd1877d9080ccb1604c91", "2016-12-31", "release_date.desc", 2);

        getMovies.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                if (response.isSuccessful()) {
                    Log.d("getMovies", "Request success");
                    Log.d("getMovies", "Request body: " + response.body());
                    Log.d("getMovies", "Request body: " + response.body().getResults());

                    moviesList = response.body().getResults();
                    Log.d("getMovies", moviesList + "");

                    for (int i=0; i<moviesList.size(); i++) {
                        Log.d("getMovies", "Request body: " + moviesList.get(i).getMoviePopularity());
                    }


                }
            }
            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d("getMovies", "Request failure");
            }
        });

    }

}