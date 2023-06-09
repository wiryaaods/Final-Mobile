package com.example.h071211048_finalmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.h071211048_finalmobile.fragment.FavoritesFragment;
import com.example.h071211048_finalmobile.fragment.MoviesFragment;
import com.example.h071211048_finalmobile.fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity {

    private ImageView movie, tvShow, favorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movie = findViewById(R.id.fr_movies);
        tvShow = findViewById(R.id.fr_tvshow);
        favorite = findViewById(R.id.fr_favorite);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MoviesFragment moviesFragment = new MoviesFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(MoviesFragment.class.getSimpleName());
        if (!(fragment instanceof MoviesFragment)){
            fragmentManager.beginTransaction()
                    .add(R.id.frame_container, moviesFragment, MoviesFragment.class.getSimpleName())
                    .commit();
        }

        movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoviesFragment moviesFragment = new MoviesFragment();
                switchToFragment(moviesFragment);

            }
        });

        tvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TvShowFragment tvShowFragment = new TvShowFragment();
                switchToFragment(tvShowFragment);
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavoritesFragment favoritesFragment = new FavoritesFragment();
                switchToFragment(favoritesFragment);
            }
        });

    }
    public void switchToFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
