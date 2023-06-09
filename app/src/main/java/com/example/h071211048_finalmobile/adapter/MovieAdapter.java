package com.example.h071211048_finalmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211048_finalmobile.DetailDataMovie;
import com.example.h071211048_finalmobile.R;
import com.example.h071211048_finalmobile.model.MovieResult;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieResult> movieResultList = new ArrayList<>();
    private Context context;
    private onSelectData onSelectData;
    public interface onSelectData{
        void onSelect(MovieResult movieResult);
    }

    public void setOnSelectData(onSelectData onSelectData)  {
        this.onSelectData = onSelectData;
    }

    public MovieAdapter() {
    }


    public MovieAdapter(List<MovieResult> movieResultList) {
        this.movieResultList = movieResultList;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        MovieResult movieResult = movieResultList.get(position);
        holder.setData(movieResult);
//        holder.rl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (onSelectData != null) {
//                    onSelectData.onSelect(movieResult);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return movieResultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView poster;
        private TextView title, year;
        private RelativeLayout rl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.iv_poster);
            title = itemView.findViewById(R.id.tv_title);
            year = itemView.findViewById(R.id.tv_year);
            rl = itemView.findViewById(R.id.itemLayout);

        }
        public void setData(MovieResult movieResult) {
            String yearr = movieResult.getReleaseDate().substring(0,4);
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w185" + movieResult.getPosterPath()).into(poster);
            title.setText(movieResult.getTitle());
            year.setText(yearr);

//            rl.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onSelectData.onSelect(movieResult);
//                }
//            });
        }
    }
}
