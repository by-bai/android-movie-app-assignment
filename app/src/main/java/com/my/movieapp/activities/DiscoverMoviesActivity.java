package com.my.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.my.movieapp.R;
import com.my.movieapp.model.Movie;
import com.my.movieapp.model.MoviesResponse;
import com.my.movieapp.networking.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverMoviesActivity extends AppCompatActivity {

    private List<Movie> movieList;

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
                Log.d("getMovies", "Request why fail" + response.message());
                if (response.isSuccessful()) {
                    Log.d("getMovies", "Request success");
                    Log.d("getMovies", "Request body: " + response.body());
                    Log.d("getMovies", "Request body: " + response.body().getResults());

                    List<Movie> movies = response.body().getResults();
                    Log.d("getMovies", movies + "");
//
                    for (int i=0; i<movies.size(); i++) {
                        Log.d("getMovies", "Request body: " + movies.get(i).getMoviePopularity());
                    }


//                    try {
//                        JSONObject jsonObject = new JSONObject(response.body());
//                        JSONArray jsonArray = jsonObject.getJSONArray("results");
//                        Log.d("getMovies", "parsing" + jsonArray);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

                }
            }
            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d("getMovies", "Request failure");
            }
        });

    }

}