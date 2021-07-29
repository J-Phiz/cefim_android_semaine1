package fr.jpsave.android.movieapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import fr.jpsave.android.movieapp.R;
import fr.jpsave.android.movieapp.adapter.SearchAdapter;
import fr.jpsave.android.movieapp.constants.Constants;
import fr.jpsave.android.movieapp.constants.StaticMovies;
import fr.jpsave.android.movieapp.model.Movie;
import fr.jpsave.android.movieapp.model.Search;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {

    private Context mContext;
    private ArrayList<Movie> mMovies;
    private SearchView mSvSearch;
    private RecyclerView mRecyclerView;
    private SearchAdapter mSearchAdapter;
    private OkHttpClient mOkHttpClient;
    private String searchText;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mContext = this;
        mOkHttpClient = new OkHttpClient();
        mMovies = new ArrayList<>();
        mSvSearch = findViewById(R.id.search_view);
        mRecyclerView = findViewById(R.id.recycler_view);

        mSvSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // Call API
                String url = getString(R.string.movie_base_url) + "&s=" + s.replace(" ", "%20");
                Request request = new Request.Builder().url(url).build();
                mOkHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("ChezMoi", "Movie API Communication failure");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String stringJson = response.body().string();
                        Log.d("ChezMoi", "Movie API Communication OK\n" + stringJson);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Code exécuté dans le Thread principale
                                Gson gson = new Gson();
                                Search search = (gson.fromJson(stringJson, Search.class));
                                mMovies.removeAll(mMovies);
                                if (search != null) {
                                    mMovies.addAll(Arrays.asList(search.getSearch()));
                                    mSearchAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mSearchAdapter = new SearchAdapter(mContext, mMovies);
        mRecyclerView.setAdapter(mSearchAdapter);
    }
}