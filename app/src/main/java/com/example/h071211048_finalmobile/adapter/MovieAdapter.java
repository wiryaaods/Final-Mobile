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

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieResult> movie;

    public MovieAdapter(List<MovieResult> movie) {

        this.movie = movie;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        MovieResult movieResults = movie.get(position);
        holder.setData(movieResults);
    }

    @Override
    public int getItemCount() {
        return movie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView poster;
        private TextView title, year;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster= itemView.findViewById(R.id.iv_poster);
            title = itemView.findViewById(R.id.tv_title);
            year = itemView.findViewById(R.id.tv_year);
        }

        public void setData(MovieResult movieResult) {
            String yearr = movieResult.getReleaseDate().substring(0,4);
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w185" + movieResult.getPosterPath()).into(poster);
            title.setText(movieResult.getTitle());
            year.setText(yearr);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DetailData.class);
                    intent.putExtra(DetailData.EXTRA_MOVIE, movieResult);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
