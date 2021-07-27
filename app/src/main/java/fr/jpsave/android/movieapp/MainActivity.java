package fr.jpsave.android.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import fr.jpsave.android.movieapp.constants.Constants;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewWelcome;
    private Button mButtonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("ChezMoi Processus", "MainActivity: onCreate()");

        //Toast.makeText(this, "BlaBlaBla... Je ne dis jamais BlaBlaBla!!!", Toast.LENGTH_SHORT).show();
        mTextViewWelcome = (TextView) findViewById(R.id.text_view_welcome);
        mTextViewWelcome.setText(getString(R.string.welcome, "JP"));
        Toast.makeText(this, mTextViewWelcome.getText(), Toast.LENGTH_SHORT).show();

        mButtonSearch = (Button) findViewById(R.id.button_search);
        mButtonSearch.setOnClickListener(
            view -> Log.d("ChezMoi", "Click sur bouton rechercher")
        );
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

    public void defaultOnClickSearchButton(View view) {
        Log.d("ChezMoi", "Click sur bouton rechercher avec methode par defaut");
    }

    public void onClickFilm(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        String message = "Click on Film ";
        String filmTitle = "";

        if (linearLayout.getId() == R.id.linear_layout_film1) {
            filmTitle = ((TextView) view.findViewById(R.id.text_view_film1)).getText().toString();
        } else if (linearLayout.getId() == R.id.linear_layout_film2) {
            filmTitle = ((TextView) view.findViewById(R.id.text_view_film2)).getText().toString();
        }

        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra(Constants.MOVIE_TITLE_KEY, filmTitle);
        startActivity(intent);

        Toast.makeText(this, message + filmTitle, Toast.LENGTH_SHORT).show();
    }

}