package info.noname.moviehub.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.noname.moviehub.IOnItemClicked;
import info.noname.moviehub.R;
import info.noname.moviehub.UserLocalStore;
import info.noname.moviehub.adapters.MovieAdapter;
import info.noname.moviehub.models.Movie;
import info.noname.moviehub.models.MovieCategories;
import info.noname.moviehub.models.User;
import info.noname.moviehub.models.UserListMovies;

/**
 * Created by Bare7a on 10/10/2016.
 */

public class MovieActivity extends BasicActivity implements IOnItemClicked {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Movie> mMovies;
    private Context mCtx;
    UserLocalStore mUserLocalStore;
    User loggedInUser;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCtx = this;
        mUserLocalStore =  new UserLocalStore(mCtx);
        List<User> users = User.findWithQuery(User.class, "SELECT * FROM USER WHERE _username = ?",mUserLocalStore.getLoggedInUser().get_username());
        loggedInUser = users.get(0);
        res = getResources();

        List<MovieCategories> selectedCategory = getIntent().getParcelableArrayListExtra("category_movies");
        // setTitle(getIntent().getStringExtra("category_name"));

        mRecyclerView = (RecyclerView) findViewById(R.id.movies_recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mIth.attachToRecyclerView(mRecyclerView);
        mMovies = new ArrayList<Movie>();

        for (MovieCategories aSelectedCategory : selectedCategory) {
            mMovies.add(aSelectedCategory.get_movie());
        }

        mAdapter = new MovieAdapter(this, mMovies, this);
        mRecyclerView.setAdapter(mAdapter);


    }

    ItemTouchHelper mIth = new ItemTouchHelper(
            new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                    ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    int moviePosition = viewHolder.getAdapterPosition();
                    List<Movie> currentMovies = Movie.findWithQuery(Movie.class, "SELECT * FROM MOVIE WHERE _title = ?", mMovies.get(moviePosition).get_title());
                    Movie currentMovie = currentMovies.get(0);

                    List<UserListMovies> userListMovies = currentMovie.gatAllUserListMoviesByMovie();

                    UserListMovies userListMovie = null;

                    for(int i = 0; i < userListMovies.size(); i++){
                        if(loggedInUser.get_username().equals(userListMovies.get(i).get_user().get_username())){
                            userListMovie = userListMovies.get(i);
                            break;
                        }
                    }

                    if(userListMovie != null){
                        userListMovie.delete();
                        Toast.makeText(mCtx, String.format(res.getString(R.string.removed_from_favourites),currentMovie.get_title()), Toast.LENGTH_SHORT).show();
                    }else{
                        new UserListMovies(currentMovie,loggedInUser).save();
                        Toast.makeText(mCtx, String.format(res.getString(R.string.added_to_favourites),currentMovie.get_title()), Toast.LENGTH_SHORT).show();
                    }

                    mAdapter.notifyItemChanged(moviePosition);
                }
            });

    @Override
    public void onItemClicked(int position) {
        Movie selectedMovie = mMovies.get(position);
        Intent intent = new Intent(MovieActivity.this, DetailedMovieActivity.class);
        intent.putExtra("movie", selectedMovie);
        startActivity(intent);
    }
}
