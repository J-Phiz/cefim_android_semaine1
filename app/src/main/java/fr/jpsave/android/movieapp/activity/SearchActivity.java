package fr.jpsave.android.movieapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import fr.jpsave.android.movieapp.R;
import fr.jpsave.android.movieapp.adapter.SearchAdapter;
import fr.jpsave.android.movieapp.client.ClientAPI;
import fr.jpsave.android.movieapp.constants.Constants;
import fr.jpsave.android.movieapp.constants.StaticMovies;
import fr.jpsave.android.movieapp.model.Movie;
import fr.jpsave.android.movieapp.model.Search;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity implements ClientAPI {

    private Activity mContext;
    private ArrayList<Movie> mMovies;
    private TextView mTvError;
    private SearchView mSvSearch;
    private RecyclerView mRecyclerView;
    private SearchAdapter mSearchAdapter;
    private ProgressBar mPbloading;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mContext = this;
        mMovies = new ArrayList<>();
        mSvSearch = findViewById(R.id.search_view);
        mRecyclerView = findViewById(R.id.recycler_view);
        mPbloading = findViewById(R.id.progress_bar_load_list);
        mTvError = findViewById(R.id.text_view_no_internet);

        // Manage Search View
        mSvSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mTvError.setVisibility(View.INVISIBLE);
                mPbloading.setVisibility(View.VISIBLE);
                mPbloading.animate();
                callAPI(mContext, getString(R.string.movie_base_url) + "&s=" + s.replace(" ", "%20"));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        // Manage Recycler View
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mSearchAdapter = new SearchAdapter(mContext, mMovies);
        mRecyclerView.setAdapter(mSearchAdapter);
    }

    private void failure(int msgId) {
        mTvError.setText(msgId);
        mTvError.setVisibility(View.VISIBLE);
        mPbloading.setVisibility(View.GONE);
    }

    @Override
    public void onAPIFailure() {
        failure(R.string.no_movies_db_access);
    }

    @Override
    public void onAPISuccess(String json) {
        Gson gson = new Gson();
        Search search = (gson.fromJson(json, Search.class));
        mMovies.removeAll(mMovies);
        if (search != null && search.getResponse().equals("True")) {
            mMovies.addAll(Arrays.asList(search.getSearch()));
            mPbloading.setVisibility(View.INVISIBLE);
            mSearchAdapter.notifyDataSetChanged();
        } else {
            if (search != null && search.getError().equals("Too many results.")) {
                failure(R.string.too_many_results);
            } else {
                failure(R.string.no_reseult);
            }
        }
    }

    @Override
    public void onAPINoInternetAccess() {
        failure(R.string.no_internet);
    }
}