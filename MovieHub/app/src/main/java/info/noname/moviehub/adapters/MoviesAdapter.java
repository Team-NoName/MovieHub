package info.noname.moviehub.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import info.noname.moviehub.R;
import info.noname.moviehub.models.Movie;

/**
 * Created by Bare7a on 10/10/2016.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<Movie> mMovies;
    private Context mCtx;


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
        TextView mCategory;
        TextView mDescription;
        ImageView mPoster;

        ViewHolder(View v) {
            super(v);

            mTitle = (TextView) v.findViewById(R.id.movieTitle);
            mCategory = (TextView) v.findViewById(R.id.movieCategory);
            mDescription = (TextView) v.findViewById(R.id.movieDescription);
            mPoster = (ImageView) v.findViewById(R.id.moviePoster);

        }
    }

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.mCtx = context;
        this.mMovies = movies;
    }

    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recycle_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mMovies.get(position).get_title() + " ("+mMovies.get(position).get_year()+")");
        holder.mCategory.setText(mMovies.get(position).get_category().get_name() +", "+mMovies.get(position).get_duration()+" minutes");
        holder.mDescription.setText(mMovies.get(position).get_description());
        Picasso.with(mCtx).load(mMovies.get(position).get_poster()).fit().into(holder.mPoster);
    }


    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}