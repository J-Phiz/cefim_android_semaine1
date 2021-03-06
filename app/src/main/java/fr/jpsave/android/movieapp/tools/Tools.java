package fr.jpsave.android.movieapp.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import fr.jpsave.android.movieapp.R;

public class Tools {

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Oui il y a Internet
            Log.i(context.getClass().getName(), "Internet Access OK");
            return true;
        } else {
            // Non... J’affiche un message à l’utilisateur
            Log.e(context.getClass().getName(), "No Internet Access");
            Toast.makeText(context, R.string.no_internet, Toast.LENGTH_LONG).show();
            return false;
        }
    }

}
