package com.example.h071211048_finalmobile.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.h071211048_finalmobile.DetailDataMovie;
import com.example.h071211048_finalmobile.R;
import com.example.h071211048_finalmobile.adapter.MovieAdapter;
import com.example.h071211048_finalmobile.model.MovieResponse;
import com.example.h071211048_finalmobile.model.MovieResult;
import com.example.h071211048_finalmobile.networking.ApiConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {

    private RecyclerView rv_movies;
    private TextView tv_alert;
    private ProgressBar progressBar;
    private MovieAdapter movieAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        getActivity().setTitle("Movies");
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        rv_movies = view.findViewById(R.id.rv_movie);
        rv_movies.setLayoutManager(new GridLayoutManager(getContext(), 2));
        tv_alert = view.findViewById(R.id.tv_alert);
        progressBar = view.findViewById(R.id.progress_bar);

        movieAdapter = new MovieAdapter();
        movieAdapter.setOnSelectData(new MovieAdapter.onSelectData() {
            @Override
            public void onSelect(MovieResult movieResult) {
                Intent intent = new Intent(getActivity(), DetailDataMovie.class);
//                Bundle bundle = new Bundle();
                intent.putExtra("DetailMovie", movieResult);
//                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        rv_movies.setAdapter(movieAdapter);


        consumeAPI();

    }

    public void consumeAPI() {
        showLoading();
        Call<MovieResponse> client = ApiConfig.getApiService().getMovie("popular", "b784846a95277f1dc4106ff2519fe987", "en-US", 1);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response != null) {
                        List<MovieResult> movie = response.body().getResults();
                        MovieAdapter moviesAdapter = new MovieAdapter(movie);

                        rv_movies.setAdapter(moviesAdapter);
                        hideLoading();
                    }
                }else{
                    if(response.body() !=null){
                        Log.e("Main Activity", "onFailure: "+ response.message());
                    }
                }
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                showAlert();
            }
        });
    }
    public void showAlert() {
        progressBar.setVisibility(View.GONE);
        tv_alert.setVisibility(View.VISIBLE);
        rv_movies.setVisibility(View.GONE);
    }

    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        tv_alert.setVisibility(View.INVISIBLE);
        rv_movies.setVisibility(View.VISIBLE);
    }

    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        tv_alert.setVisibility(View.GONE);
        rv_movies.setVisibility(View.GONE);

    }
}