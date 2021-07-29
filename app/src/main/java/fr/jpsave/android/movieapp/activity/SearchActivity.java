package fr.jpsave.android.movieapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import java.util.ArrayList;

import fr.jpsave.android.movieapp.R;
import fr.jpsave.android.movieapp.constants.StaticMovies;
import fr.jpsave.android.movieapp.model.Movie;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<Movie> mMovies;
    private SearchView mSvSearch;
    private RecyclerView mRvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSvSearch = findViewById(R.id.search_view);
        mRvResults = findViewById(R.id.recycler_view);

        mMovies = new ArrayList<>();
        mMovies.add(StaticMovies.FillStarWars());
        mMovies.add(StaticMovies.FillBatman());
    }
}