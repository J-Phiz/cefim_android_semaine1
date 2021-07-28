package fr.jpsave.android.movieapp;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.jpsave.android.movieapp.constants.Constants;
import fr.jpsave.android.movieapp.constants.JSONMovies;
import fr.jpsave.android.movieapp.constants.StaticMovies;
import fr.jpsave.android.movieapp.model.Movie;

public class MovieActivity extends AppCompatActivity {

    private TextView mTvDescriptionLabel;
    private boolean mShowMore;
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie);

        Log.d("ChezMoi Processus", "MovieActivity: onCreate()");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get Params from MainActivity
        Bundle params = getIntent().getExtras();
        CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(params.getString(Constants.MOVIE_TITLE_KEY));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.favorite_added, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        Gson gson = new Gson();
        mMovie = gson.fromJson(JSONMovies.starWars, Movie.class);
        //mMovie = StaticMovies.FillStarWars();
        updateUI(mMovie);

        mTvDescriptionLabel = findViewById(R.id.text_view_description_label);
        mShowMore = true;
        showMoreLess(null);
    }

    public void showMoreLess(View view) {
        TextView tvDescription = findViewById(R.id.text_view_description);

        if (!mShowMore) {
            tvDescription.setText(mMovie.getPlot());
            mShowMore = true;
            mTvDescriptionLabel.setText(R.string.show_less);
        } else {
            tvDescription.setText(
                    String.format("%s...", mMovie.getPlot().subSequence(0, 200))
            );
            mShowMore = false;
            mTvDescriptionLabel.setText(R.string.show_more);
        }
    }

    private void updateUI(Movie movie) {
        TextView tvTitle = findViewById(R.id.text_view_title);
        TextView tvReleaseDate = findViewById(R.id.text_view_releaseDate);
        TextView tvGenre = findViewById(R.id.text_view_genre);
        TextView tvDescription = findViewById(R.id.text_view_description);
        TextView tvDirector = findViewById(R.id.text_view_director);
        TextView tvActors = findViewById(R.id.text_view_actors);
        TextView tvAwards = findViewById(R.id.text_view_awards);
        ImageView ivImage = findViewById(R.id.image_view_image);

        tvTitle.setText(movie.getTitle());
        tvReleaseDate.setText(movie.getReleased());
        tvGenre.setText(movie.getGenre());
        tvDescription.setText(movie.getPlot());
        tvDirector.setText(movie.getDirector());
        tvActors.setText(movie.getActors());
        tvAwards.setText(movie.getAwards());
        ivImage.setImageResource(movie.getPosterId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ChezMoi Processus", "MovieActivity: onDestroy()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ChezMoi Processus", "MovieActivity: onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ChezMoi Processus", "MovieActivity: onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ChezMoi Processus", "MovieActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ChezMoi Processus", "MovieActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ChezMoi Processus", "MovieActivity: onStop()");
    }

}