package info.noname.moviehub.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class Movie extends SugarRecord implements Parcelable {

    private String _name;
    private String _description;
    private String _urlTrailer;
    private String _poster;
    private Category _category;

    public Movie() {

    }

    public Movie(String name, String description, String urlTriller, String poster, Category category) {
        this.set_name(name);
        this.set_description(description);
        this.set_urlTrailer(urlTriller);
        this.set_poster(poster);
        this.set_category(category);
    }

    // Get all actors by current movie
    public List<MovieActors> gatAllActorsByMovie() {
        return MovieActors.find(MovieActors.class, "movie = ?", String.valueOf(getId()));
    }

    // Get all user votes by current movie
    public List<UserVotes> gatAllUserVotesByMovie() {
        return UserVotes.find(UserVotes.class, "movie = ?", String.valueOf(getId()));
    }

    // Get all comments by current movie
    public List<MovieComments> gatAllCommentsByMovie() {
        return MovieComments.find(MovieComments.class, "movie = ?", String.valueOf(getId()));
    }

    // Get all user lists by current movie
    public List<UserListMovies> gatAllUserListMoviesByMovie() {
        return UserListMovies.find(UserListMovies.class, "movie = ?", String.valueOf(getId()));
    }

    // Get all categories by current movie
    public List<MovieCategories> getAllCategoriesByMovie() {
        return MovieCategories.find(MovieCategories.class, "movie = ?", String.valueOf(getId()));
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_urlTrailer() {
        return _urlTrailer;
    }

    public void set_urlTrailer(String _urlTriller) {
        this._urlTrailer = _urlTriller;
    }

    public String get_poster() {
        return _poster;
    }

    public void set_poster(String _poster) {
        this._poster = _poster;
    }

    public Category get_category() {
        return _category;
    }

    public void set_category(Category _category) {
        this._category = _category;
    }

    protected Movie(Parcel in) {
        _name = in.readString();
        _description = in.readString();
        _urlTrailer = in.readString();
        _poster = in.readString();
        _category = (Category) in.readValue(Category.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_name);
        dest.writeString(_description);
        dest.writeString(_urlTrailer);
        dest.writeString(_poster);
        dest.writeValue(_category);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}