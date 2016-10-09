package info.noname.moviehub.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class Category extends SugarRecord implements Parcelable {

    private String _name;
    private String _title;

    public Category() {

    }

    public Category(String name, String title) {
        this.set_name(name);
        this.set_title(title);
    }


    // Get all movies by current category
    public List<Movie> gatAllMoviesCategory() {
        return Movie.find(Movie.class, "category = ?", String.valueOf(getId()));
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    protected Category(Parcel in) {
        _name = in.readString();
        _title = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_name);
        dest.writeString(_title);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}