package info.noname.moviehub.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class MovieComments extends SugarRecord implements Parcelable {

    private String _comment;
    private Movie _movie;
    private User _user;

    public MovieComments() {

    }

    public MovieComments(String commnent, Movie movie, User user) {
        this.set_comment(commnent);
        this.set_movie(movie);
        this.set_user(user);
    }

    public Movie get_movie() {
        return _movie;
    }

    public void set_movie(Movie _movie) {
        this._movie = _movie;
    }

    public String get_comment() {
        return _comment;
    }

    public void set_comment(String _comment) {
        this._comment = _comment;
    }

    public User get_user() {
        return _user;
    }

    public void set_user(User _user) {
        this._user = _user;
    }

    protected MovieComments(Parcel in) {
        _comment = in.readString();
        _movie = (Movie) in.readValue(Movie.class.getClassLoader());
        _user = (User) in.readValue(User.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_comment);
        dest.writeValue(_movie);
        dest.writeValue(_user);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieComments> CREATOR = new Parcelable.Creator<MovieComments>() {
        @Override
        public MovieComments createFromParcel(Parcel in) {
            return new MovieComments(in);
        }

        @Override
        public MovieComments[] newArray(int size) {
            return new MovieComments[size];
        }
    };
}
