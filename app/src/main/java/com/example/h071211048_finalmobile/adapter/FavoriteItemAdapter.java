package com.example.h071211048_finalmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211048_finalmobile.DetailData;
import com.example.h071211048_finalmobile.R;
import com.example.h071211048_finalmobile.model.MovieResult;
import com.example.h071211048_finalmobile.model.TvShowResult;

import java.util.List;

public class FavoriteItemAdapter extends RecyclerView.Adapter<FavoriteItemAdapter.ViewHolder>{

    private List<TvShowResult> tvShowResults;
    private List<MovieResult> movieResults;

    public void setMovieResults(List<MovieResult> movieResults) {
        this.movieResults = movieResults;
        notifyDataSetChanged();
    }

    public void setTvShowResults(List<MovieResult> movieResults) {
        this.movieResults = movieResults;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FavoriteItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull FavoriteItemAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return movieResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_posterImg, iv_logo;
        private TextView tv_title, tv_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_posterImg = itemView.findViewById(R.id.iv_posterimg);
            iv_logo = itemView.findViewById(R.id.iv_logo);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}
