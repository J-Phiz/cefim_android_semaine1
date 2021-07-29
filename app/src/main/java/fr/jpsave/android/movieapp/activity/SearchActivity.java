package fr.jpsave.android.movieapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.SearchView;

import java.util.ArrayList;

import fr.jpsave.android.movieapp.R;
import fr.jpsave.android.movieapp.adapter.SearchAdapter;
import fr.jpsave.android.movieapp.constants.StaticMovies;
import fr.jpsave.android.movieapp.model.Movie;

public class SearchActivity extends AppCompatActivity {

    private Context mContext;
    private ArrayList<Movie> mMovies;
    private SearchView mSvSearch;
    private RecyclerView mRecyclerView;
    private SearchAdapter mSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mContext = this;
        mSvSearch = findViewById(R.id.search_view);
        mRecyclerView = findViewById(R.id.recycler_view);

        mMovies = new ArrayList<>();
        mMovies.add(StaticMovies.FillStarWars());
        mMovies.add(StaticMovies.FillBatman());

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mSearchAdapter = new SearchAdapter(mContext, mMovies);
        mRecyclerView.setAdapter(mSearchAdapter);
    }
}