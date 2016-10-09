package info.noname.moviehub.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class MovieComment extends SugarRecord implements Parcelable {

    private String _comment;
    private Movie _movie;

    public MovieComment() {

    }

    public MovieComment(String commnent, Movie movie) {
        this.set_comment(commnent);
        this.set_movie(movie);
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

    protected MovieComment(Parcel in) {
        _comment = in.readString();
        _movie = (Movie) in.readValue(Movie.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_comment);
        dest.writeValue(_movie);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieComment> CREATOR = new Parcelable.Creator<MovieComment>() {
        @Override
        public MovieComment createFromParcel(Parcel in) {
            return new MovieComment(in);
        }

        @Override
        public MovieComment[] newArray(int size) {
            return new MovieComment[size];
        }
    };
}
