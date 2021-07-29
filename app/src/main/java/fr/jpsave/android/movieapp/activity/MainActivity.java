package fr.jpsave.android.movieapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import fr.jpsave.android.movieapp.R;
import fr.jpsave.android.movieapp.constants.Constants;
import fr.jpsave.android.movieapp.constants.JSONMovies;
import fr.jpsave.android.movieapp.constants.StaticMovies;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private TextView mTextViewWelcome;
    private LinearLayout mLinearLayoutFilm1;
    private LinearLayout mLinearLayoutFilm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Utile pour les class anonymes comme les onClickListener
        mContext = this;

        Log.d("ChezMoi Processus", "MainActivity: onCreate()");

        mTextViewWelcome = (TextView) findViewById(R.id.text_view_welcome);
        mTextViewWelcome.setText(getString(R.string.welcome, "JP"));
        Toast.makeText(this, mTextViewWelcome.getText(), Toast.LENGTH_SHORT).show();

        mLinearLayoutFilm1 = (LinearLayout) findViewById(R.id.linear_layout_film1);
        mLinearLayoutFilm1.setOnClickListener(
            view -> this.onClickFilm(
                   "tt0076759",
                ((TextView) view.findViewById(R.id.text_view_film1)).getText().toString(),
                    JSONMovies.starWars
            )
        );

        mLinearLayoutFilm2 = (LinearLayout) findViewById(R.id.linear_layout_film2);
        mLinearLayoutFilm2.setOnClickListener(view -> {
            Gson gson = new Gson();
            this.onClickFilm(
                    "tt0103776",
                ((TextView) view.findViewById(R.id.text_view_film2)).getText().toString(),
                gson.toJson(StaticMovies.FillBatman())
            );
        });

    }

    public void defaultOnClickSearchButton(View view) {
        Log.d("ChezMoi", "Click sur bouton rechercher avec methode par defaut");
        Toast.makeText(mContext, "Click sur Bouton rechercher", Toast.LENGTH_SHORT).show();
    }

    public void onClickFilm(String filmID, String filmTitle, String filmInfo) {
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra(Constants.MOVIE_ID_KEY, filmID);
        intent.putExtra(Constants.MOVIE_TITLE_KEY, filmTitle);
        intent.putExtra(Constants.MOVIE_JSON_INFO_KEY, filmInfo);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ChezMoi Processus", "MainActivity: onDestroy()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ChezMoi Processus", "MainActivity: onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ChezMoi Processus", "MainActivity: onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ChezMoi Processus", "MainActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ChezMoi Processus", "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ChezMoi Processus", "MainActivity: onStop()");
    }

}