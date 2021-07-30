package fr.jpsave.android.movieapp.client;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.jpsave.android.movieapp.R;
import fr.jpsave.android.movieapp.model.Movie;
import fr.jpsave.android.movieapp.model.Search;
import fr.jpsave.android.movieapp.tools.Tools;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public interface MovieAPI {

    default void callAPIMovieInfo(Activity context, String movieId) {
        OkHttpClient okHttpClient = new OkHttpClient();

        if (!Tools.checkInternetConnection(context)) {
            onAPINoInternetAccess();
            return;
        }

        // Call API
        String url = context.getString(R.string.movie_base_url) +
                "&plot=full&i=" + movieId;
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(this.getClass().getName(), "Movie API Communication failure");
                Toast.makeText(context, R.string.no_movies_db_access, Toast.LENGTH_LONG).show();
                MovieAPI.this.onAPIFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String stringJson = response.body().string();
                Log.i(this.getClass().getName(), "Movie API Communication OK");
                Log.d(this.getClass().getName(), stringJson);

                // Attendre juste pour voir le progressBar
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Code exécuté dans le Thread principale
                        Gson gson = new Gson();
                        Movie movie = gson.fromJson(stringJson, Movie.class);
                        if (movie != null) {
                            ArrayList<Movie> movies = new ArrayList<>();
                            movies.add(movie);
                            onAPISuccess(movies);
                        } else {
                            onAPISuccess(null);
                        }
                    }
                });
            }
        });
    }
    default void callAPISearch(Activity context, String movieTitle) {
        OkHttpClient okHttpClient = new OkHttpClient();

        if (!Tools.checkInternetConnection(context)) {
            onAPINoInternetAccess();
            return;
        }

        // Call API
        String url = context.getString(R.string.movie_base_url) +
                "&plot=full&s=" + movieTitle.replace(" ", "%20");
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(this.getClass().getName(), "Movie API Communication failure");
                Toast.makeText(context, R.string.no_movies_db_access, Toast.LENGTH_LONG).show();
                MovieAPI.this.onAPIFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String stringJson = response.body().string();
                Log.i(this.getClass().getName(), "Movie API Communication OK");
                Log.d(this.getClass().getName(), stringJson);

                // Attendre juste pour voir le progressBar
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Code exécuté dans le Thread principale
                        Gson gson = new Gson();
                        Search search = (gson.fromJson(stringJson, Search.class));

                        if (search != null) {
                            onAPISuccess(Arrays.asList(search.getSearch()));
                        } else {
                            onAPISuccess(null);
                        }
                    }
                });
            }
        });
    }

    void onAPIFailure();
    void onAPISuccess(List<Movie> movies);
    void onAPINoInternetAccess();

}
