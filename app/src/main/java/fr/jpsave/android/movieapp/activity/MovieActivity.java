package fr.jpsave.android.movieapp.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import java.io.IOException;

import fr.jpsave.android.movieapp.R;
import fr.jpsave.android.movieapp.constants.Constants;
import fr.jpsave.android.movieapp.constants.JSONMovies;
import fr.jpsave.android.movieapp.constants.StaticMovies;
import fr.jpsave.android.movieapp.model.Movie;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieActivity extends AppCompatActivity {

    private TextView mTvDescription;
    private TextView mTvDescriptionLabel;
    private boolean mShowMore = false;
    private boolean mIsFavorite = false;
    private OkHttpClient mOkHttpClient;
    private ProgressBar mPbloading;
    private LinearLayout mLlAllContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mTvDescription = findViewById(R.id.text_view_description);
        mTvDescriptionLabel = findViewById(R.id.text_view_description_label);
        mPbloading = findViewById(R.id.progress_bar_load);
        mLlAllContent =  findViewById(R.id.linear_layout_movie);

        mOkHttpClient = new OkHttpClient();

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

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Oui il y a Internet je lance un appel API

            mLlAllContent.setVisibility(View.INVISIBLE);
            mPbloading.setVisibility(View.VISIBLE);
            mPbloading.animate();

            String url = getString(R.string.movie_base_url) + "&i=" + params.getString(Constants.MOVIE_ID_KEY);
            Request request = new Request.Builder().url(url).build();
            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("ChezMoi", "Movie API Communication failure");
                    ((TextView) findViewById(R.id.text_view_no_internet)).setVisibility(View.VISIBLE);
                    mLlAllContent.setVisibility(View.INVISIBLE);
                    mPbloading.setVisibility(View.GONE);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String stringJson = response.body().string();
                    Log.d("ChezMoi", "Movie API Communication OK\n" + stringJson);

                    // Attendre juste pour voir le progressBar
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Code exécuté dans le Thread principale
                            Gson gson = new Gson();
                            updateUI(gson.fromJson(stringJson, Movie.class));
                            mPbloading.setVisibility(View.GONE);
                            mLlAllContent.setVisibility(View.VISIBLE);
                        }
                    });
                }
            });

        } else {
            // Non... J’affiche un message à l’utilisateur
            ((TextView) findViewById(R.id.text_view_no_internet)).setVisibility(View.VISIBLE);
            mLlAllContent.setVisibility(View.INVISIBLE);
            mPbloading.setVisibility(View.GONE);
        }
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