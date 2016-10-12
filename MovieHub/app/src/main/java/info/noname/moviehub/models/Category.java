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
    private String _categoryImage;
    private String _categoryBackground;

    public Category() {

    }

    public Category(String name, String categoryImage, String categoryBackground) {

        this.set_name(name);
        this.set_categoryImage(categoryImage);
        this.set_categoryBackground(categoryBackground);
    }

    // Get all movies by current category
    public List<MovieCategories> getAllMoviesByCategory() {
        return MovieCategories.find(MovieCategories.class, "_category = ?", String.valueOf(getId()));
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_categoryImage(){ return _categoryImage; }

    public void set_categoryImage(String _categoryImage){ this._categoryImage = _categoryImage; }

    public String get_categoryBackground(){
        return _categoryBackground;
    }

    public void set_categoryBackground(String _categoryBackground){
        this._categoryBackground = _categoryBackground;
    }

    protected Category(Parcel in) {

        _name = in.readString();
        _categoryImage = in.readString();
        _categoryBackground = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_name);
        dest.writeString(_categoryImage);
        dest.writeString(_categoryBackground);
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