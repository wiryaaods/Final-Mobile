package com.example.h071211048_finalmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.h071211048_finalmobile.model.MovieResult;

public class DetailDataMovie extends AppCompatActivity {

    private ImageView backdropimg, poster;
    private TextView title, releaseDate, rating, sinopsis;
    private MovieResult movieResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_movie);
        getSupportActionBar().hide();

        backdropimg = findViewById(R.id.iv_backdropimg);
        poster = findViewById(R.id.iv_poster);
        title = findViewById(R.id.tv_title);
        releaseDate = findViewById(R.id.tv_releaseDate);
        rating = findViewById(R.id.tv_rating);
        sinopsis = findViewById(R.id.tv_sinopsis);

//        Intent intent = getIntent();
//        if (intent != null) {
//            movieResult = intent.getSerializableExtra("DetailData");
//            if (movieResult != null) {
//                title.setText(movieResult.getTitle());
//                releaseDate.setText(movieResult.getReleaseDate());
//                rating.setText(String.valueOf(movieResult.getVoteAverage()));
//                sinopsis.setText(movieResult.getOverview());
//            }
//        }

        movieResult = (MovieResult) getIntent().getSerializableExtra("DetailData");
        title.setText(movieResult.getTitle());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null && bundle.containsKey("DetailMovie")) {
            MovieResult movieResult = (MovieResult) bundle.getSerializable("DetailMovie");
            // Use the movieResult object
        }

    }


    public void back(View view){
        finish();
    }
}