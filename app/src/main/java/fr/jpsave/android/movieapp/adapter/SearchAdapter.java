package fr.jpsave.android.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import fr.jpsave.android.movieapp.R;
import fr.jpsave.android.movieapp.activity.MovieActivity;
import fr.jpsave.android.movieapp.constants.Constants;
import fr.jpsave.android.movieapp.model.Movie;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Movie> mMovies;

    public SearchAdapter(Context context, ArrayList<Movie> movies) {
        this.mContext = context;
        this.mMovies = movies;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mIvImage;
        public TextView mTvItemTitle;
        public TextView mTvItemReleaseDate;
        public Movie mMovie;

        public ViewHolder(View view) {
            super(view);
            mIvImage = (ImageView) view.findViewById(R.id.image_view_item_image);
            mTvItemTitle = (TextView) view.findViewById(R.id.text_view_item_title);
            mTvItemReleaseDate = (TextView) view.findViewById(R.id.text_view_item_releaseDate);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mMovie != null) {
                Intent intent = new Intent(mContext, MovieActivity.class);
                intent.putExtra(Constants.MOVIE_ID_KEY, mMovie.getImdbID());
                intent.putExtra(Constants.MOVIE_TITLE_KEY, mMovie.getTitle());
                mContext.startActivity(intent);
            }
        }
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_search_movie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        holder.mTvItemTitle.setText(movie.getTitle());
        holder.mTvItemReleaseDate.setText(movie.getYear());
        Picasso.get().load(movie.getPoster())
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_image_24)
                .into(holder.mIvImage);
        holder.mMovie = movie;
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

}
