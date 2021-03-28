package com.my.movieapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.my.movieapp.R;
import com.my.movieapp.repository.NetworkState;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NetworkStateViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.progress_bar_item)
    ProgressBar progressBar;

    @BindView(R.id.error_msg_item)
    TextView errorMsg;

    private NetworkStateViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bindTo(NetworkState networkState) {
        if(networkState != null && networkState == NetworkState.LOADING) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

        if (networkState != null && networkState == NetworkState.ERROR) {
            errorMsg.setVisibility(View.VISIBLE);
            errorMsg.setText(networkState.getMessage());
        } else if (networkState != null && networkState == NetworkState.ENDOFLIST) {
            errorMsg.setVisibility(View.VISIBLE);
            errorMsg.setText(networkState.getMessage());
        } else {
            errorMsg.setVisibility(View.GONE);
        }

    }

    public static NetworkStateViewHolder create(ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_networkstate, parent, false);
        return new NetworkStateViewHolder(view);
    }

}
