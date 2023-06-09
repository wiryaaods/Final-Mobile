package com.example.h071211048_finalmobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211048_finalmobile.R;
import com.example.h071211048_finalmobile.model.TvShowResult;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private List<TvShowResult> tvshow;

    public TvShowAdapter(List<TvShowResult> tvshow) {
        this.tvshow = tvshow;
    }

    @NonNull
    @Override
    public TvShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_show, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.ViewHolder holder, int position) {
        TvShowResult tvShowResult = tvshow.get(position);
        holder.setData(tvShowResult);
    }

    @Override
    public int getItemCount() {
        return tvshow.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView poster;
        private TextView title, year;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.iv_img);
            title = itemView.findViewById(R.id.tv_title);
            year = itemView.findViewById(R.id.tv_year);
        }

        public void setData(TvShowResult tvShowResult) {
            String yearr = tvShowResult.getFirstAirDate().substring(0, 4);
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w185" + tvShowResult.getPosterPath()).into(poster);
            title.setText(tvShowResult.getName());
            year.setText(yearr);
        }
    }
}
