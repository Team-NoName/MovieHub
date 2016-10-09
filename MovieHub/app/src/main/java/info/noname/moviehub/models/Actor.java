package info.noname.moviehub.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class Actor extends SugarRecord implements Parcelable {

    private String _firstName;
    private String _lastname;

    public Actor() {

    }

    public Actor(String firstName, String lastName) {
        this.set_firstName(firstName);
        this.set_lastname(lastName);
    }

    // Get all movies by current actor
    public List<MovieActors> gatAllMoviesActor() {
        return MovieActors.find(MovieActors.class, "actor = ?", String.valueOf(getId()));
    }

    public String get_firstName() {
        return _firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String get_lastname() {
        return _lastname;
    }

    public void set_lastname(String _lastname) {
        this._lastname = _lastname;
    }

    protected Actor(Parcel in) {
        _firstName = in.readString();
        _lastname = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_firstName);
        dest.writeString(_lastname);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Actor> CREATOR = new Parcelable.Creator<Actor>() {
        @Override
        public Actor createFromParcel(Parcel in) {
            return new Actor(in);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };
}
