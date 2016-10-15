package info.noname.moviehub.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.query.Condition;
import com.orm.query.Select;
import com.squareup.picasso.Picasso;

import info.noname.moviehub.IOnItemClicked;
import info.noname.moviehub.R;
import info.noname.moviehub.UserLocalStore;
import info.noname.moviehub.appData.CommentData;
import info.noname.moviehub.fragments.CommentFragment;
import info.noname.moviehub.models.Movie;
import info.noname.moviehub.models.User;

/**
 * Created by Bare7a on 13/10/2016.
 */

public class DetailedMovieActivity extends AppCompatActivity implements View.OnClickListener, IOnItemClicked {
    Movie mSelectedMovie;
    TextView mMovieTitle;
    ImageView mMoviePoster;
    TextView mMovieYear;
    TextView mMovieDuration;
    TextView mMovieCategory;
    TextView mMovieActors;
    TextView mMovieDescription;
    Resources res;
    CommentFragment commentFragment;
    Button mShowComments;
    Button mAddComment;
    TextView mAddCommentEditText;
    CommentData mCommentData;
    UserLocalStore mUserLocalStore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_movie_view);

        mSelectedMovie = getIntent().getParcelableExtra("movie");
        Bundle bundle = new Bundle();
        bundle.putParcelable("selected", mSelectedMovie);

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

        mShowComments = (Button) findViewById(R.id.showComments);
        commentFragment = new CommentFragment();
        commentFragment.setArguments(bundle);
        mShowComments.setOnClickListener(this);

        mAddComment = (Button) findViewById(R.id.addComment);
        mAddCommentEditText = (EditText)findViewById(R.id.add_comment_content);
        mAddComment.setOnClickListener(this);
        mCommentData = new CommentData();
        mUserLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.showComments){
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.comments, commentFragment);
            ft.commit();
        }
        if (v.getId() == R.id.addComment){
            User user = Select.from(User.class)
                    .where(Condition.prop("_username").like(mUserLocalStore.getLoggedInUser().get_username())).first();

            Movie movie = Select.from(Movie.class)
                    .where(Condition.prop("_title").like(mSelectedMovie.get_title())).first();

            mCommentData.addComment(mAddCommentEditText.getText().toString(), movie, user);
            Toast.makeText(this, "Successfully add comment", Toast.LENGTH_SHORT).show();
        }
    }
}
