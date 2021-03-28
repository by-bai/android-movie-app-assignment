package com.my.movieapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.my.movieapp.R;
import com.my.movieapp.model.Movies;
import com.my.movieapp.repository.NetworkState;

import java.util.Objects;

public class MovieListAdapter extends PagedListAdapter<Movies, RecyclerView.ViewHolder> {

    private NetworkState networkState;
    private Context context;

    public MovieListAdapter(Context context) {
        super(MovieDiffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case R.layout.item_movie:
                return MovieItemViewHolder.create(parent);
            case R.layout.item_networkstate:
                return NetworkStateViewHolder.create(parent);
            default:
                throw new IllegalArgumentException("unknown view type");

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.item_movie:
                ((MovieItemViewHolder) holder).bindTo(getItem(position));
                break;
            case R.layout.item_networkstate:
                ((NetworkStateViewHolder) holder).bindTo(networkState);
                break;
        }
    }

    private boolean hasExtraRow() {
        return networkState != null && networkState != NetworkState.LOADED;
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.item_networkstate;
        } else {
            return R.layout.item_movie;
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + (hasExtraRow() ? 1 : 0);
    }

    public void setNetworkState(NetworkState newNetworkState) {
        if (getCurrentList() != null) {
            if (getCurrentList().size() != 0) { // after initial load
                NetworkState previousState = this.networkState;
                boolean hadExtraRow = hasExtraRow();
                this.networkState = newNetworkState;
                boolean hasExtraRow = hasExtraRow();
                if (hadExtraRow != hasExtraRow) {
                    if (hadExtraRow) { // hadExtraRow is true and hasExtraRow false
                        notifyItemRemoved(super.getItemCount()); //remove the progressbar at the end
                    } else { // has ExtraRow is true and hadExtraRow false
                        notifyItemInserted(super.getItemCount()); //add the progressbar at the end
                    }
                } else if (hasExtraRow && previousState != newNetworkState) { //hasExtraRow is true and hadExtraRow true and NetworkState.ERROR or NetworkState.ENDOFLIST
                    notifyItemChanged(getItemCount() - 1); // add the network message at the end
                }
            }
        }
    }

    private static DiffUtil.ItemCallback<Movies> MovieDiffCallback = new DiffUtil.ItemCallback<Movies>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
            return oldItem.getMovieID() == newItem.getMovieID();
        }

        @SuppressLint("NewApi")
        @Override
        public boolean areContentsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };
}
