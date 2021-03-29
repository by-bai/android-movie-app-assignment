package com.my.movieapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.my.movieapp.model.Movies;
import com.my.movieapp.repository.MovieDataSource;
import com.my.movieapp.repository.MovieDataSourceFactory;
import com.my.movieapp.repository.NetworkState;

import io.reactivex.disposables.CompositeDisposable;

public class MovieListViewModel extends AndroidViewModel {

    public LiveData<PagedList<Movies>> movieList;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static final int POST_PER_PAGE = 20;

    private MovieDataSourceFactory movieDataSourceFactory;

    public MovieListViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        movieDataSourceFactory = new MovieDataSourceFactory(compositeDisposable);
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(POST_PER_PAGE)
                .setEnablePlaceholders(false)
                .build();

        movieList = new LivePagedListBuilder<>(movieDataSourceFactory, config).build();

    }

    public LiveData<NetworkState> getNetworkState() {
        return Transformations.switchMap(movieDataSourceFactory.getMovieDataSourceLiveData(), MovieDataSource::getNetworkState);
    }


    public void refresh() {
        movieDataSourceFactory.getMovieDataSourceLiveData().getValue().invalidate();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
