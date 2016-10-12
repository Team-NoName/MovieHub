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
import info.noname.moviehub.models.MovieCategories;
import info.noname.moviehub.models.MovieComments;
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
        User user1 = new User("John", "Snow", "john@snow.bg", "http://cdn.tvweb.com/img.news.tops/NEaMHVFvWpoJeh_2_b.jpg");

        Category categoryAction = new Category("Action", "http://i279.photobucket.com/albums/kk143/pasqkpo/Chucknorris.png", "#997300");
        Category categoryDrama = new Category("Drama", "http://www.bamangharhs.edu.bd/images/staffs/drama-icon.png", "#0066cc");
        Category categoryComedy = new Category("Comedy", "https://moviefiednyc.files.wordpress.com/2013/11/e0006-ace-ventura-pet-detective-512c7fac5d838.png", "#2eb82e");

        Movie movie1 = new Movie("The Matrix", categoryAction, 1999, 136, "Thomas A. Anderson is a man living two lives. By day he is an average computer programmer and by night a hacker known as Neo. Neo has always questioned his reality, but the truth is far beyond his imagination. Neo finds himself targeted by the police when he is contacted by Morpheus, a legendary computer hacker branded a terrorist by the government. Morpheus awakens Neo to the real world, a ravaged wasteland where most of humanity have been captured by a race of machines that live off of the humans' body heat and electrochemical energy and who imprison their minds within an artificial reality known as the Matrix. As a rebel against the machines, Neo must return to the Matrix and confront the agents: super-powerful computer programs devoted to snuffing out Neo and the entire human rebellion.","http://www.impawards.com/1999/posters/matrix_ver1.jpg", "https://www.youtube.com/watch?v=Q8g9zL-JL8E");
        Movie movie2 = new Movie("American History X ", categoryDrama, 1998, 119, "Derek Vineyard is paroled after serving 3 years in prison for killing two thugs who tried to break into/steal his truck. Through his brother, Danny Vineyard's narration, we learn that before going to prison, Derek was a skinhead and the leader of a violent white supremacist gang that committed acts of racial crime throughout L.A. and his actions greatly influenced Danny. Reformed and fresh out of prison, Derek severs contact with the gang and becomes determined to keep Danny from going down the same violent path as he did.", "https://www.cinematerial.com/media/posters/md/zj/zjjeldsq.jpg", "https://www.youtube.com/watch?v=JsPW6Fj3BUI");
        Movie movie3 = new Movie("The God of Gamblers", categoryAction, 1989, 126, "The God of Gamblers is a legendary gambler helped by his supernatural abilities. He undertakes to help a friend pay a debt by beating his friend's advisory at the card table. Despite being assigned a bodyguard Do San has a freak accident which leaves him with partial memory loss and at a mental stage of a child. The small time hustler Knife, his side-kick and his girl friend, being responsible for the accident takes care of the retarded Do San. After some time they discover that he has not lost all of his powers and takes him on a round at the local gambling halls. After being chased by both Knife's loan-shark and enemies closer to the home of Do San, a final showdown at the card tables may take place.", "http://moviescounter.com/wp-content/uploads/2016/04/God-of-Gamblers.jpg", "https://www.youtube.com/watch?v=mibGBsWF4E0" );
        Movie movie4 = new Movie("Borat", categoryComedy, 2006, 86, "Borat Sagdiyev is a TV reporter of a popular show in Kazakhstan as Kazakhstan's sixth most famous man and a leading journalist. He is sent from his home to America by his government to make a documentary about American society and culture. Borat takes a course in New York City to understand American humor. While watching Baywatch on TV, Borat discovers how beautiful their women are in the form of C. J. Parker, who was played by actress Pamela Anderson who hails from Malibu, California. He decides to go on a cross-country road trip to California in a quest to make her his wife and take her back to his country. On his journey Borat and his producer encounter a country full of strange and wonderful Americans, real people in real chaotic situations with hysterical consequences.", "https://upload.wikimedia.org/wikipedia/en/3/39/Borat_ver2.jpg","https://www.youtube.com/watch?v=vlnUa_dNsRQ" );

        MovieComments comment1 = new MovieComments("I absolutely love this movie!", movie1, user1);

        Actor kevinHart = new Actor("Keanu", "Reeves");

        MovieActors movieActors = new MovieActors(movie1, kevinHart);

        MovieCategories movieCategories = new MovieCategories(movie1, categoryAction);

        UserVotes userVotes = new UserVotes(movie1, user1, 1);

        UserListMovies userListMovies = new UserListMovies(movie1, user1);

        user1.save();
        categoryAction.save();
        categoryDrama.save();
        categoryComedy.save();
        movie1.save();
        movie2.save();
        movie3.save();
        movie4.save();
        comment1.save();
        kevinHart.save();
        movieActors.save();
        movieCategories.save();
        userVotes.save();
        userListMovies.save();
    }

    private boolean doesDatabaseExist(ContextWrapper context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);

        return dbFile.exists();
    }
}
