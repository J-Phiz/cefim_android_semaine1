package fr.jpsave.android.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewWelcome;
    private Button mButtonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(this, "BlaBlaBla... Je ne dis jamais BlaBlaBla!!!", Toast.LENGTH_SHORT).show();
        mTextViewWelcome = (TextView) findViewById(R.id.text_view_welcome);
        mTextViewWelcome.setText(getString(R.string.welcome, "JP"));
        Toast.makeText(this, mTextViewWelcome.getText(), Toast.LENGTH_SHORT).show();

        mButtonSearch = (Button) findViewById(R.id.button_search);
        mButtonSearch.setOnClickListener(
            view -> Log.d("ChezMoi", "Click sur bouton rechercher")
        );
    }

    public void defaultOnClickSearchButton(View view) {
        Log.d("ChezMoi", "Click sur bouton rechercher avec methode par defaut");
    }

    public void onClickFilm(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        String message = "Click on Film ";

        if (linearLayout.getId() == R.id.linear_layout_film1)
            message += ((TextView) view.findViewById(R.id.text_view_film1)).getText().toString();
        if (linearLayout.getId() == R.id.linear_layout_film2)
            message += ((TextView) view.findViewById(R.id.text_view_film2)).getText().toString();

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}