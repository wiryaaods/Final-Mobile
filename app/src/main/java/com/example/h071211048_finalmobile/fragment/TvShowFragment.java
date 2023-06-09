package com.example.h071211048_finalmobile.fragment;

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
import android.widget.TextView;

import com.example.h071211048_finalmobile.R;
import com.example.h071211048_finalmobile.adapter.TvShowAdapter;
import com.example.h071211048_finalmobile.model.TvShowResponse;
import com.example.h071211048_finalmobile.model.TvShowResult;
import com.example.h071211048_finalmobile.networking.ApiConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowFragment extends Fragment {

    private RecyclerView rv_tvshow;
    private TextView tv_alert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        getActivity().setTitle("TV Shows");
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        rv_tvshow = view.findViewById(R.id.rv_tvshow);
        rv_tvshow.setLayoutManager(new GridLayoutManager(getContext(), 2));
        tv_alert = view.findViewById(R.id.tv_alert);

        consumeAPI();

    }
    public void consumeAPI() {
        tv_alert.setVisibility(View.GONE);
        Call<TvShowResponse> client = ApiConfig.getApiService().getTv("top_rated", "b784846a95277f1dc4106ff2519fe987", "en-US", 1);
        client.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.isSuccessful()){
                    if (response != null) {
                        List<TvShowResult> tvshowResults = response.body().getTvshowResults();
                        TvShowAdapter tvshowAdapter = new TvShowAdapter(tvshowResults);
                        rv_tvshow.setAdapter(tvshowAdapter);
                    }
                }else{
                    if(response.body() !=null){
                        Log.e("Main Activity", "onFailure: "+ response.message());
                    }
                }
            }
            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
//                tv_alert.setVisibility(View.VISIBLE);
            }
        });
    }
}