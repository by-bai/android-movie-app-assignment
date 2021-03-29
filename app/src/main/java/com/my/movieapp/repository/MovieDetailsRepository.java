package com.my.movieapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.my.movieapp.model.MovieDetails;
import com.my.movieapp.networking.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsRepository {

    private static MovieDetailsRepository movieDetailsRepository;

    public static MovieDetailsRepository getInstance() {
        if (movieDetailsRepository == null) {
            movieDetailsRepository = new MovieDetailsRepository();
        }

        return movieDetailsRepository;
    }

    public MutableLiveData<MovieDetails> getMovieDetails(int movieID) {
        MutableLiveData<MovieDetails> movieDetailsData = new MutableLiveData<>();
        Call<MovieDetails> getMovies = ApiClient.getMovieDetailsService().getMovieDetails(movieID);
        getMovies.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {

                if (response.isSuccessful()) {
                   movieDetailsData.setValue(response.body());
                }


            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Log.d("getMovies", "Request failure");
                movieDetailsData.setValue(null);
            }
        });

        return movieDetailsData;
    }
}
