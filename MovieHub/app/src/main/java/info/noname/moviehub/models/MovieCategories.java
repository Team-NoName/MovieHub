package info.noname.moviehub.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class MovieCategories extends SugarRecord implements Parcelable {
    private Movie _movie;
    private Category _category;

    public MovieCategories() {

    }

    public MovieCategories(Movie movie, Category category) {
        this.set_movie(movie);
        this.set_category(category);
    }

    public Movie get_movie() {
        return _movie;
    }

    public void set_movie(Movie _movie) {
        this._movie = _movie;
    }


    public Category get_category() {
        return _category;
    }

    public void set_category(Category _category) {
        this._category = _category;
    }

    protected MovieCategories(Parcel in) {
        _movie = (Movie) in.readValue(Movie.class.getClassLoader());
        _category = (Category) in.readValue(Category.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(_movie);
        dest.writeValue(_category);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieCategories> CREATOR = new Parcelable.Creator<MovieCategories>() {
        @Override
        public MovieCategories createFromParcel(Parcel in) {
            return new MovieCategories(in);
        }

        @Override
        public MovieCategories[] newArray(int size) {
            return new MovieCategories[size];
        }
    };
}
