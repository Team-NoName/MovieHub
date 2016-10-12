package info.noname.moviehub.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import info.noname.moviehub.R;
import info.noname.moviehub.adapters.MoviesAdapter;
import info.noname.moviehub.models.Movie;

/**
 * Created by Bare7a on 10/10/2016.
 */

public class MoviesActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Movie> mMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_layout);

        mRecyclerView = (RecyclerView) findViewById(R.id.movies_recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mMovies = Movie.listAll(Movie.class);
        mAdapter = new MoviesAdapter(this, mMovies);
        mRecyclerView.setAdapter(mAdapter);
    }
}
