package info.noname.moviehub.appData;

import com.orm.query.Condition;
import com.orm.query.Select;

import info.noname.moviehub.models.Movie;
import info.noname.moviehub.models.MovieComments;
import info.noname.moviehub.models.User;

/**
 * Created by Ivan on 10/15/2016.
 */

public class CommentData {
    public CommentData(){

    }

    public Movie getMovie(String title){
        Movie movie = Select.from(Movie.class)
                .where(Condition.prop("_title").like(title)).first();

        return movie;
    }

    public MovieComments addComment(String comment, Movie movie, User user){
        MovieComments movieComments = new MovieComments(comment, movie, user);

        movieComments.save();

        return movieComments;
    }
}
