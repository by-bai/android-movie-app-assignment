package com.my.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.my.movieapp.R;
import com.my.movieapp.adapters.MovieListAdapter;

import com.my.movieapp.viewmodel.MovieListViewModel;



public class MovieListActivity extends AppCompatActivity {

    private MovieListViewModel movieListViewModel;
    private MovieListAdapter movieListAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout movieListRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_movies);

        recyclerView = findViewById(R.id.movie_recycler);
        movieListRefreshLayout = findViewById(R.id.swipe_refresh);
        movieListViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MovieListViewModel.class);
//        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        movieListViewModel.init();
        initAdapter();
        initSwipeToRefresh();
    }

    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        movieListAdapter = new MovieListAdapter(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(movieListAdapter);
        movieListViewModel.movieList.observe(this, movieListAdapter::submitList);
        movieListViewModel.getNetworkState().observe(this, movieListAdapter::setNetworkState);

    }

    private void initSwipeToRefresh() {
        movieListRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                movieListViewModel.refresh();
                movieListAdapter.notifyDataSetChanged();
                Log.d("data refreshed", "data refreshed true");
                movieListRefreshLayout.setRefreshing(false);
            }
        });
//        movieListRefreshLayout.setOnRefreshListener(() -> movieListViewModel.refresh());
//        movieListAdapter.notifyDataSetChanged();
    }

}