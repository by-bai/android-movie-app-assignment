package com.my.movieapp.networking;

import com.my.movieapp.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("discover/movie")
    Call<MoviesResponse> getMovies (@Query("api_key") String key,
                                    @Query("primary_release_date.lte") String releaseDate,
                                    @Query("sort_by") String sortBy,
                                    @Query("page") int page);

}
