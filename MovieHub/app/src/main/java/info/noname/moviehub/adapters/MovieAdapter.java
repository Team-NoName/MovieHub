package info.noname.moviehub.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import info.noname.moviehub.IOnItemClicked;
import info.noname.moviehub.R;
import info.noname.moviehub.UserLocalStore;
import info.noname.moviehub.models.Movie;
import info.noname.moviehub.models.UserListMovies;

/**
 * Created by Bare7a on 10/10/2016.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> mMovies;
    private Context mCtx;
    private Resources res;
    private UserLocalStore mUserLocalStore;
    private static IOnItemClicked callback;

    public void setCallback(IOnItemClicked callback) {
        this.callback = callback;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTitle;
        TextView mCategory;
        TextView mAddOrRemoveFromList;
        TextView mDescription;
        ImageView mPoster;
        RelativeLayout mParent;

        ViewHolder(View v) {
            super(v);

            mTitle = (TextView) v.findViewById(R.id.movieTitle);
            mCategory = (TextView) v.findViewById(R.id.movieCategory);
            mAddOrRemoveFromList = (TextView) v.findViewById(R.id.addOrRemoveFromList);
            mDescription = (TextView) v.findViewById(R.id.movieDescription);
            mPoster = (ImageView) v.findViewById(R.id.moviePoster);
            mParent = (RelativeLayout) v.findViewById(R.id.movieParent);

            mParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null) {
                        callback.onItemClicked(getAdapterPosition());
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (callback != null) {
                callback.onItemClicked(getAdapterPosition());
            }
        }
    }

    public MovieAdapter(Context context, List<Movie> movies, IOnItemClicked callback) {
        this.mCtx = context;
        this.mMovies = movies;
        this.res = context.getResources();
        mUserLocalStore = new UserLocalStore(mCtx);
        this.setCallback(callback);
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recycle_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(String.format(res.getString(R.string.movie_title), mMovies.get(position).get_title(), Integer.toString(mMovies.get(position).get_year())));
        holder.mCategory.setText(String.format(res.getString(R.string.movie_category), mMovies.get(position).get_category().get_name(), Integer.toString(mMovies.get(position).get_duration())));
        holder.mDescription.setText(mMovies.get(position).get_description());
        Picasso.with(mCtx).load(mMovies.get(position).get_poster()).fit().into(holder.mPoster);

        List<Movie> currentMovies = Movie.findWithQuery(Movie.class, "SELECT * FROM MOVIE WHERE _title = ?", mMovies.get(position).get_title());
        Movie currentMovie = currentMovies.get(0);

        List<UserListMovies> userListMovies = currentMovie.gatAllUserListMoviesByMovie();

        boolean isAdded = false;

        for (int i = 0; i < userListMovies.size(); i++) {
            if (mUserLocalStore.getLoggedInUser().get_username().equals(userListMovies.get(i).get_user().get_username())) {
                isAdded = true;
                break;
            }
        }

        if (isAdded) {
            holder.mAddOrRemoveFromList.setText(R.string.remove_from_list);
            holder.mAddOrRemoveFromList.setTextColor(ContextCompat.getColor(mCtx,R.color.colorMovieRemove));
        } else {
            holder.mAddOrRemoveFromList.setText(R.string.add_to_list);
            holder.mAddOrRemoveFromList.setTextColor(ContextCompat.getColor(mCtx,R.color.colorMovieAdd));
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}