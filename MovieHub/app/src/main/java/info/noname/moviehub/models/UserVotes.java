package info.noname.moviehub.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class UserVotes extends SugarRecord implements Parcelable {

    private Movie _movie;
    private User _user;
    private int _vote;

    public UserVotes() {

    }

    public UserVotes(Movie movie, User user, int vote) {
        this.set_movie(movie);
        this.set_user(user);
        this.set_vote(vote);
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

    public int get_vote() {
        return _vote;
    }

    public void set_vote(int _vote) {
        this._vote = _vote;
    }

    protected UserVotes(Parcel in) {
        _movie = (Movie) in.readValue(Movie.class.getClassLoader());
        _user = (User) in.readValue(User.class.getClassLoader());
        _vote = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(_movie);
        dest.writeValue(_user);
        dest.writeInt(_vote);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserVotes> CREATOR = new Parcelable.Creator<UserVotes>() {
        @Override
        public UserVotes createFromParcel(Parcel in) {
            return new UserVotes(in);
        }

        @Override
        public UserVotes[] newArray(int size) {
            return new UserVotes[size];
        }
    };
}