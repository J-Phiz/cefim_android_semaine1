package fr.jpsave.android.movieapp.client;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import fr.jpsave.android.movieapp.R;
import fr.jpsave.android.movieapp.tools.Tools;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public interface ClientAPI {

    default void callAPI(Activity context, String url) {
        OkHttpClient okHttpClient = new OkHttpClient();

        if (!Tools.checkInternetConnection(context)) {
            onAPINoInternetAccess();
            return;
        }

        // Call API
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(context.getClass().getName(), "API Communication failure");
                Toast.makeText(context, R.string.no_movies_db_access, Toast.LENGTH_LONG).show();
                ClientAPI.this.onAPIFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String stringJson = response.body().string();
                Log.i(context.getClass().getName(), "API Communication OK");
                Log.d(context.getClass().getName(), stringJson);

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
                        onAPISuccess(stringJson);
                    }
                });
            }
        });
    }

    void onAPIFailure();

    void onAPISuccess(String json);

    void onAPINoInternetAccess();

}
