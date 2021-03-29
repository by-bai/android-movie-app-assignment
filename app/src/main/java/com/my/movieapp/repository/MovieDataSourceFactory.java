package com.my.movieapp.repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.my.movieapp.model.Movies;
import com.my.movieapp.networking.ApiClient;

import io.reactivex.disposables.CompositeDisposable;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movies> {

    // DataSourceFactory creates DataSource

    private CompositeDisposable compositeDisposable;

    private MutableLiveData<MovieDataSource> movieDataSourceLiveData = new MutableLiveData<>();

    public MovieDataSourceFactory(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }


    @Override
    public DataSource<Integer, Movies> create() {
        MovieDataSource movieDataSource = new MovieDataSource(ApiClient.getMoviesService(), compositeDisposable);
        movieDataSourceLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    @NonNull
    public MutableLiveData<MovieDataSource> getMovieDataSourceLiveData() {
        return movieDataSourceLiveData;
    }


}
