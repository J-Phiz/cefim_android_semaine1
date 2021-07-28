package fr.jpsave.android.movieapp;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import fr.jpsave.android.movieapp.constants.Constants;

public class MovieActivity extends AppCompatActivity {

    private TextView mTvDescription;
    private TextView mTvDescriptionLabel;
    private boolean showMore;

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

        mTvDescription = findViewById(R.id.text_view_description);
        mTvDescriptionLabel = findViewById(R.id.text_view_description_label);
        showMore = true;
        showMoreLess(null);
    }

    public void showMoreLess(View view) {
        if (!showMore) {
            mTvDescription.setText(getString(R.string.large_text));
            showMore = true;
            mTvDescriptionLabel.setText(R.string.show_less);
        } else {
            mTvDescription.setText(
                    String.format("%s...", getText(R.string.large_text).subSequence(0, 200))
            );
            showMore = false;
            mTvDescriptionLabel.setText(R.string.show_more);
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