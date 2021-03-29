package com.my.movieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.my.movieapp.model.MovieDetails;
import com.my.movieapp.repository.MovieDetailsRepository;

public class MovieDetailsViewModel extends AndroidViewModel {
    private MutableLiveData<MovieDetails> movieDetailsLiveData;
    private MovieDetailsRepository movieDetailsRepository;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(int movieID) {
        if (movieDetailsLiveData != null) {
            return;
        }

        movieDetailsRepository = movieDetailsRepository.getInstance();
        movieDetailsLiveData = movieDetailsRepository.getMovieDetails(movieID);

    }

    public LiveData<MovieDetails> getMovieDetails() {
        return movieDetailsLiveData;
    }
}
