package info.noname.moviehub.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class UserListMovies extends SugarRecord implements Parcelable {

    private Movie _movie;
    private User _user;

    public UserListMovies() {

    }

    public UserListMovies(Movie movie, User user) {
        this.set_movie(movie);
        this.set_user(user);
    }

    public Movie get_movie() {
        return _movie;
    }

    public void set_movie(Movie _movie) {
        this._movie = _movie;
    }

    public User get_user() {
        return _user;
    }

    public void set_user(User _user) {
        this._user = _user;
    }

    protected UserListMovies(Parcel in) {
        _movie = (Movie) in.readValue(Movie.class.getClassLoader());
        _user = (User) in.readValue(User.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(_movie);
        dest.writeValue(_user);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserListMovies> CREATOR = new Parcelable.Creator<UserListMovies>() {
        @Override
        public UserListMovies createFromParcel(Parcel in) {
            return new UserListMovies(in);
        }

        @Override
        public UserListMovies[] newArray(int size) {
            return new UserListMovies[size];
        }
    };
}