package fr.jpsave.android.movieapp.activity;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import fr.jpsave.android.movieapp.R;
import fr.jpsave.android.movieapp.client.ClientAPI;
import fr.jpsave.android.movieapp.constants.Constants;
import fr.jpsave.android.movieapp.model.Movie;

public class MovieActivity extends AppCompatActivity implements ClientAPI {

    private TextView mTvDescription;
    private TextView mTvDescriptionLabel;
    private TextView mTvError;
    private boolean mShowMore = false;
    private boolean mIsFavorite = false;
    private ProgressBar mPbloading;
    private LinearLayout mLlAllContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mTvDescription = findViewById(R.id.text_view_description);
        mTvDescriptionLabel = findViewById(R.id.text_view_description_label);
        mTvError = findViewById(R.id.text_view_no_internet);
        mPbloading = findViewById(R.id.progress_bar_load);
        mLlAllContent =  findViewById(R.id.linear_layout_movie);

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
                String message = "UnKnown";

                if (mIsFavorite) {
                    message = getString(R.string.favorite_removed);
                    fab.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    mIsFavorite = false;
                } else {
                    message = getString(R.string.favorite_added);
                    fab.setImageResource(R.drawable.ic_baseline_favorite_24);
                    mIsFavorite = true;
                }

                Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mTvError.setVisibility(View.INVISIBLE);
        mLlAllContent.setVisibility(View.INVISIBLE);
        mPbloading.setVisibility(View.VISIBLE);
        mPbloading.animate();

        callAPI(
            this,
            getString(R.string.movie_base_url) +
                        "&plot=full&i=" + params.getString(Constants.MOVIE_ID_KEY)
        );
    }

    public void showMoreLess(View view) {

        if (!mShowMore) {
            mTvDescription.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            mTvDescription.setMaxLines(Integer.MAX_VALUE);
            mShowMore = true;
            mTvDescriptionLabel.setText(R.string.show_less);
        } else {
            mTvDescription.setEllipsize(TextUtils.TruncateAt.END);
            mTvDescription.setMaxLines(3);
            mShowMore = false;
            mTvDescriptionLabel.setText(R.string.show_more);
        }
    }

    private void updateUI(Movie movie) {
        if (movie != null) {
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
            if (movie.getPosterId() != 0) {
                ivImage.setImageResource(movie.getPosterId());
            } else {
                Picasso.get().load(movie.getPoster()).into(ivImage);
            }
        }
    }

    private void failure(int msgId) {
        mTvError.setText(msgId);
        mTvError.setVisibility(View.VISIBLE);
        mLlAllContent.setVisibility(View.INVISIBLE);
        mPbloading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onAPIFailure() {
        failure(R.string.no_movies_db_access);
    }

    @Override
    public void onAPISuccess(String json) {
        Gson gson = new Gson();
        Movie movie = gson.fromJson(json, Movie.class);
        if (movie != null) {
            updateUI(movie);
            mPbloading.setVisibility(View.GONE);
            mLlAllContent.setVisibility(View.VISIBLE);
        } else {
            failure(R.string.no_reseult);
        }
    }

    @Override
    public void onAPINoInternetAccess() {
        failure(R.string.no_internet);
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