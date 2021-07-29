package fr.jpsave.android.movieapp.adapter;

import android.content.Context;
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
import fr.jpsave.android.movieapp.model.Movie;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Movie> mMovies;

    public SearchAdapter(Context context, ArrayList<Movie> movies) {
        this.mContext = context;
        this.mMovies = movies;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIvImage;
        public TextView mTvItemTitle;
        public TextView mTvItemReleaseDate;

        public ViewHolder(View view) {
            super(view);
            mIvImage = (ImageView) view.findViewById(R.id.image_view_item_image);
            mTvItemTitle = (TextView) view.findViewById(R.id.text_view_item_title);
            mTvItemReleaseDate = (TextView) view.findViewById(R.id.text_view_item_releaseDate);
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
        holder.mTvItemReleaseDate.setText(movie.getReleased());
        Picasso.get().load(movie.getPoster()).into(holder.mIvImage);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

}
