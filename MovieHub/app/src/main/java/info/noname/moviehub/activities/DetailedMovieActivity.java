package info.noname.moviehub.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import info.noname.moviehub.IOnItemClicked;
import info.noname.moviehub.R;
import info.noname.moviehub.models.Movie;

/**
 * Created by Bare7a on 13/10/2016.
 */

public class DetailedMovieActivity extends AppCompatActivity implements IOnItemClicked {
    Movie mSelectedMovie;
    TextView mMovieTitle;
    ImageView mMoviePoster;
    TextView mMovieYear;
    TextView mMovieDuration;
    TextView mMovieCategory;
    TextView mMovieActors;
    TextView mMovieDescription;
    Resources res;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_movie_view);

        mSelectedMovie = getIntent().getParcelableExtra("movie");
        res = getResources();

        mMovieTitle = (TextView)findViewById(R.id.detailedMovieTitle);
        mMoviePoster = (ImageView)findViewById(R.id.detailedMoviePoster);
        mMovieYear = (TextView)findViewById(R.id.detailedMovieYear);
        mMovieDuration = (TextView)findViewById(R.id.detailedMovieDuration);
        mMovieCategory = (TextView)findViewById(R.id.detailedMovieCategory);
        mMovieActors = (TextView) findViewById(R.id.detailedMovieActors);
        mMovieDescription = (TextView) findViewById(R.id.detailedMovieDescription);

        mMovieTitle.setText(mSelectedMovie.get_title());
        Picasso.with(this).load(mSelectedMovie.get_poster()).into(mMoviePoster);
        mMovieYear.setText(String.format(res.getString(R.string.year), String.valueOf(mSelectedMovie.get_year())));
        mMovieDuration.setText(String.format(res.getString(R.string.duration),String.valueOf(mSelectedMovie.get_duration())));
        mMovieCategory.setText(String.format(res.getString(R.string.category), mSelectedMovie.get_category().get_name()));
        mMovieActors.setText("");
        mMovieDescription.setText(String.format(res.getString(R.string.description),mSelectedMovie.get_description()));
    }

    @Override
    public void onItemClicked(int position) {

    }
}
