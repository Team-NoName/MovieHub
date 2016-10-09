package info.noname.moviehub.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class User extends SugarRecord implements Parcelable {

    private String _username;
    private String _password;
    private String _email;
    private String _imageUri;

    public User() {

    }

    public User(String username, String password, String email, String imageUri) {
        this.set_username(username);
        this.set_password(password);
        this.set_email(email);
        this.set_imageUri(imageUri);
    }

    // Get all user votes by current user
    public List<UserVotes> gatAllUserVotesByUser() {
        return UserVotes.find(UserVotes.class, "user = ?", String.valueOf(getId()));
    }

    // Get all list movies by current user
    public List<UserListMovies> gatAllListMoviesByUser() {
        return UserListMovies.find(UserListMovies.class, "user = ?", String.valueOf(getId()));
    }

    // Get all comments by current user
    public List<MovieComments> gatAllCommentsByUser() {
        return MovieComments.find(MovieComments.class, "user = ?", String.valueOf(getId()));
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_imageUri() {
        return _imageUri;
    }

    public void set_imageUri(String _imageUri) {
        this._imageUri = _imageUri;
    }

    protected User(Parcel in) {
        _username = in.readString();
        _password = in.readString();
        _email = in.readString();
        _imageUri = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_username);
        dest.writeString(_password);
        dest.writeString(_email);
        dest.writeString(_imageUri);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
