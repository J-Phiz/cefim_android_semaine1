package fr.jpsave.android.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
            view -> Log.d("Chez Moi", "Click sur bouton rechercher")
        );

    }
}