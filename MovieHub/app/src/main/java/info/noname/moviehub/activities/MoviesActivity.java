package info.noname.moviehub.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import info.noname.moviehub.IOnItemClicked;
import info.noname.moviehub.R;
import info.noname.moviehub.adapters.MoviesAdapter;
import info.noname.moviehub.models.Movie;
import info.noname.moviehub.models.MovieCategories;

/**
 * Created by Bare7a on 10/10/2016.
 */

public class MoviesActivity extends BasicActivity implements IOnItemClicked {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Movie> mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<MovieCategories> selectedCategory = getIntent().getParcelableArrayListExtra("category_movies");
        // setTitle(getIntent().getStringExtra("category_name"));

        mRecyclerView = (RecyclerView) findViewById(R.id.movies_recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mMovies = new ArrayList<Movie>();

        mMovies.add(selectedCategory.iterator().next().get_movie());
        //mMovies = Movie.listAll(Movie.class);

        mAdapter = new MoviesAdapter(this, mMovies, this);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onItemClicked(int position) {
        Movie selectedMovie = mMovies.get(position);
        Intent intent = new Intent(MoviesActivity.this, DetailedMovieActivity.class);
        intent.putExtra("movie", selectedMovie);
        startActivity(intent);
    }
}
