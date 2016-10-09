package info.noname.moviehub.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class MovieActors extends SugarRecord implements Parcelable {

    private Movie _movie;
    private Actor _actor;

    public MovieActors() {

    }

    public MovieActors(Movie movie, Actor actor) {
        this.set_movie(movie);
        this.set_actor(actor);
    }

    public Movie get_movie() {
        return _movie;
    }

    public void set_movie(Movie _movie) {
        this._movie = _movie;
    }

    public Actor get_actor() {
        return _actor;
    }

    public void set_actor(Actor _actor) {
        this._actor = _actor;
    }

    protected MovieActors(Parcel in) {
        _movie = (Movie) in.readValue(Movie.class.getClassLoader());
        _actor = (Actor) in.readValue(Actor.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(_movie);
        dest.writeValue(_actor);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieActors> CREATOR = new Parcelable.Creator<MovieActors>() {
        @Override
        public MovieActors createFromParcel(Parcel in) {
            return new MovieActors(in);
        }

        @Override
        public MovieActors[] newArray(int size) {
            return new MovieActors[size];
        }
    };
}
