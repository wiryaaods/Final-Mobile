package com.example.h071211048_finalmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.h071211048_finalmobile.model.MovieResult;

public class DetailData extends AppCompatActivity {

    private ImageView backdropimg, poster;
    private TextView title, releaseDate, rating, sinopsis;
    private MovieResult movieResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
        getSupportActionBar().hide();

        backdropimg = findViewById(R.id.iv_backdropimg);
        poster = findViewById(R.id.iv_poster);
        title = findViewById(R.id.tv_title);
        releaseDate = findViewById(R.id.tv_releaseDate);
        rating = findViewById(R.id.tv_rating);
        sinopsis = findViewById(R.id.tv_sinopsis);

        Intent intent = new Intent();
        intent.putExtra("DetailData", movieResult);
        title.setText(movieResult.getTitle());

    }


    public void back(View view){
        finish();
    }
}