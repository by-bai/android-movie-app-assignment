package com.my.movieapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.my.movieapp.model.Movies;
import com.my.movieapp.networking.MovieApi;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MovieDataSource extends PageKeyedDataSource<Integer, Movies> {
    private static final int FIRST_PAGE = 1;

    //call api using rxjava - composite disposable
    //network state -> mutable live data

    private MovieApi movieApi;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<NetworkState> networkState = new MutableLiveData<>();

    MovieDataSource(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movies> callback) {
        compositeDisposable = new CompositeDisposable();

        //update network state
        networkState.postValue(NetworkState.LOADING);

        //get initial movies from api
        compositeDisposable.add(movieApi.getMovies(FIRST_PAGE)
                .subscribe(movies -> {
                            callback.onResult(movies.getResults(), null, FIRST_PAGE + 1);
                            networkState.postValue(NetworkState.LOADED);
                        }, throwable -> {
                            networkState.postValue(NetworkState.ERROR);

                        }
                )
        );
    }



    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movies> callback) {


    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movies> callback) {
        compositeDisposable.add(movieApi.getMovies(params.key)
                .subscribe(movies -> {
                            if(movies.getTotalPages() >= params.key) {
                                callback.onResult(movies.getResults(),FIRST_PAGE + 1);
                                networkState.postValue(NetworkState.LOADED);
                            } else {
                                networkState.postValue(NetworkState.ENDOFLIST);
                            }

                        }, throwable -> {
                            networkState.postValue(NetworkState.ERROR);

                        }
                )
        );
    }

    @NonNull
    public MutableLiveData<NetworkState> getNetworkState() {
        return networkState;
    }


}
