package info.noname.moviehub.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class Movie extends SugarRecord implements Parcelable {

    private String _title;
    private Category _category;
    private int _year;
    private int _duration;
    private String _description;
    private String _poster;
    private String _urlTrailer;

    public Movie() {

    }

    public Movie(String title, Category category, int year, int duration, String description, String poster, String urlTrailer) {
        this.set_title(title);
        this.set_category(category);
        this.set_year(year);
        this.set_duration(duration);
        this.set_description(description);
        this.set_poster(poster);
        this.set_urlTrailer(urlTrailer);
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

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public Category get_category() {
        return _category;
    }

    public void set_category(Category _category) {
        this._category = _category;
    }

    public int get_year() {
        return _year;
    }

    public void set_year(int _year) {
        this._year = _year;
    }

    public int get_duration() {
        return _duration;
    }

    public void set_duration(int _duration) {
        this._duration = _duration;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_poster() {
        return _poster;
    }

    public void set_poster(String _poster) {
        this._poster = _poster;
    }

    public String get_urlTrailer() {
        return _urlTrailer;
    }

    public void set_urlTrailer(String _urlTrailer) {
        this._urlTrailer = _urlTrailer;
    }


    protected Movie(Parcel in) {
        _title = in.readString();
        _category = (Category) in.readValue(Category.class.getClassLoader());
        _year = in.readInt();
        _duration = in.readInt();
        _description = in.readString();
        _poster = in.readString();
        _urlTrailer = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_title);
        dest.writeValue(_category);
        dest.writeInt(_year);
        dest.writeInt(_duration);
        dest.writeString(_description);
        dest.writeString(_poster);
        dest.writeString(_urlTrailer);
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