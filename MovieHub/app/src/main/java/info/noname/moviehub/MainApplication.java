package info.noname.moviehub;

import android.app.Application;
import android.content.ContextWrapper;

import com.orm.SugarContext;
import com.orm.SugarDb;

import java.io.File;

import info.noname.moviehub.models.Actor;
import info.noname.moviehub.models.Category;
import info.noname.moviehub.models.Movie;
import info.noname.moviehub.models.MovieActors;
import info.noname.moviehub.models.MovieComment;
import info.noname.moviehub.models.User;
import info.noname.moviehub.models.UserListMovies;
import info.noname.moviehub.models.UserVotes;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Delete the database if exist
        if (doesDatabaseExist(this, "movie_hub.db")) {
            SugarDb sugarDb = new SugarDb(getApplicationContext());

            new File(sugarDb.getDB().getPath()).delete();
        }

        // Create and fill the database
        SugarContext.init(getApplicationContext());
        boolean doesDbExist = doesDatabaseExist(this, "movie_hub.db");
        if (!doesDbExist){

            Category.findById(Category.class, (long) 1);
            Movie.findById(Movie.class, (long) 1);

            initDb();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    private void initDb() {
        User user1 = new User("asd", "asd", "asd@asd.bg", "http://image.com");

        Category category1 = new Category("Comedy", "Funny stories");

        Movie firstMovie = new Movie("Movie1", "The best movie", "www.abv.bg", "http://image.com", category1);

        MovieComment firstComment = new MovieComment("Comment for the first movie", firstMovie);

        Actor kevinHart = new Actor("Kevin", "Hart");

        MovieActors movieActors = new MovieActors(firstMovie, kevinHart);

        UserVotes userVotes = new UserVotes(firstMovie, user1, 1);

        UserListMovies userListMovies = new UserListMovies(firstMovie, user1);

        user1.save();
        category1.save();
        firstMovie.save();
        firstComment.save();
        kevinHart.save();
        movieActors.save();
        userVotes.save();
        userListMovies.save();
    }

    private boolean doesDatabaseExist(ContextWrapper context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);

        return dbFile.exists();
    }
}
