package info.noname.moviehub.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.noname.moviehub.R;
import info.noname.moviehub.adapters.CommentAdapter;
import info.noname.moviehub.appData.CommentData;
import info.noname.moviehub.models.Category;
import info.noname.moviehub.models.Movie;
import info.noname.moviehub.models.MovieComments;

/**
 * Created by Ivan on 10/13/2016.
 */

public class CommentFragment extends Fragment {

    //only for test, will be moved later
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<MovieComments> myDataset;
    private Movie mMovie;
    private List<MovieComments> mMovies;
    private CommentData mCommentData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comments_recycle_view,container,false);

        //test
        mRecyclerView = (RecyclerView)view.findViewById(R.id.comments_recycle_view);
        // mRecyclerView.setHasFixedSize(true);

        mCommentData = new CommentData();
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        myDataset = MovieComments.listAll(MovieComments.class);

        Movie movie = (Movie)getArguments().getParcelable("selected");
        Movie selectedMovie = mCommentData.getMovie(movie.get_title());

        mMovies = selectedMovie.gatAllCommentsByMovie();
        // specify an adapter (see also next example)
        mAdapter = new CommentAdapter(getActivity(), mMovies);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
