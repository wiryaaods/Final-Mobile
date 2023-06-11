package com.example.h071211048_finalmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.h071211048_finalmobile.localData.DatabaseHelper;
import com.example.h071211048_finalmobile.model.MovieResult;
import com.example.h071211048_finalmobile.model.TvShowResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailData extends AppCompatActivity {

    public static final String EXTRA_TVSHOW = "extra_tvshow";
    public static final String EXTRA_MOVIE = "extra_movie";
    private ImageView iv_backdropimg, iv_poster, iv_logo, btn_addToFav;
    private TextView tv_title, tv_releaseDate, tv_rating, tv_synopsis;
    private MovieResult movieResult;
    private  TvShowResult tvShowResult;
    String title, poster, backdropImg, releaseDate,  synopsis;
    Double rating;
    private boolean isFavorite = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
        getSupportActionBar().hide();

        iv_backdropimg = findViewById(R.id.iv_backdropimg);
        iv_poster = findViewById(R.id.iv_poster);
        tv_title = findViewById(R.id.tv_title);
        tv_releaseDate = findViewById(R.id.tv_releaseDate);
        tv_rating = findViewById(R.id.tv_rating);
        tv_synopsis = findViewById(R.id.tv_synopsis);
        iv_logo = findViewById(R.id.iv_logo);
        btn_addToFav = findViewById(R.id.addToFav);

        Intent intent = getIntent();
            if (intent.hasExtra(EXTRA_MOVIE)) {
                movieResult = intent.getParcelableExtra(EXTRA_MOVIE);

                backdropImg = movieResult.getBackdropPath();
                poster = movieResult.getPosterPath();
                title = movieResult.getOriginalTitle();
                releaseDate = formatDate(movieResult.getReleaseDate());
                rating = movieResult.getVoteAverage();
                synopsis = movieResult.getOverview();
                Glide.with(DetailData.this)
                        .load("https://image.tmdb.org/t/p/w185" + movieResult.getBackdropPath())
                        .into(iv_backdropimg);
                Glide.with(DetailData.this)
                        .load("https://image.tmdb.org/t/p/w185" + movieResult.getPosterPath())
                        .into(iv_poster);
                tv_title.setText(title);
                tv_releaseDate.setText(releaseDate);
                tv_rating.setText(String.valueOf(rating));
                tv_synopsis.setText(synopsis);
                iv_logo.setImageResource(R.drawable.baseline_movie_24);
            } else if (intent.hasExtra(EXTRA_TVSHOW)) {
                tvShowResult = intent.getParcelableExtra(EXTRA_TVSHOW);
                backdropImg = tvShowResult.getBackdropPath();
                poster = tvShowResult.getPosterPath();
                title = tvShowResult.getName();
                releaseDate = formatDate(tvShowResult.getFirstAirDate());
                rating = tvShowResult.getVoteAverage();
                synopsis = tvShowResult.getOverview();

                Glide.with(DetailData.this)
                        .load("https://image.tmdb.org/t/p/w185" + tvShowResult.getBackdropPath())
                        .into(iv_backdropimg);
                Glide.with(DetailData.this)
                        .load("https://image.tmdb.org/t/p/w185" + tvShowResult.getPosterPath())
                        .into(iv_poster);
                tv_title.setText(title);
                tv_releaseDate.setText(releaseDate);
                tv_rating.setText(String.valueOf(rating));
                tv_synopsis.setText(synopsis);
                iv_logo.setImageResource(R.drawable.baseline_tv_24);
            }

        btn_addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                DatabaseHelper DB = new DatabaseHelper(DetailData.this);
                    if (isFavorite) {
                        if (movieResult != null) {
                            Toast.makeText(DetailData.this,  movieResult.getOriginalTitle() + " removed from favorites successfully", Toast.LENGTH_SHORT).show();
                        } else if (tvShowResult != null) {
                            Toast.makeText(DetailData.this, tvShowResult.getName() + " removed from favorites successfully", Toast.LENGTH_SHORT).show();
                        }
                        btn_addToFav.setImageResource(R.drawable.baseline_favorite_border_24);
                        isFavorite = false;
                    } else {
                        if (movieResult != null) {
                            DB.AddData(title, poster, backdropImg, synopsis, releaseDate, (String.valueOf(rating)));
                            Toast.makeText(DetailData.this,  movieResult.getOriginalTitle() + " added to favorites successfully", Toast.LENGTH_SHORT).show();
                        } else if (tvShowResult != null) {
                            Toast.makeText(DetailData.this, tvShowResult.getName() + " added to favorites successfully", Toast.LENGTH_SHORT).show();
                        }
                        btn_addToFav.setImageResource(R.drawable.baseline_favorite_24);
                        isFavorite = true;
                    }
                }
            });
    }

    private String formatDate(String inputDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);

        try {
            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public void back(View view){
        finish();
    }
}