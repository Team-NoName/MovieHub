package info.noname.moviehub;

import android.content.Context;
import android.content.SharedPreferences;

import info.noname.moviehub.models.User;

/**
 * Created by AKamenov on 10/12/2016.
 */

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();

        userLocalDatabaseEditor.putString("username", user.get_username());
        userLocalDatabaseEditor.putString("password", user.get_password());
        userLocalDatabaseEditor.putString("email", user.get_email());
        userLocalDatabaseEditor.putString("imageUri", user.get_imageUri());

        userLocalDatabaseEditor.apply();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.apply();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.apply();
    }

    public User getLoggedInUser() {
        if (!userLocalDatabase.getBoolean("loggedIn", false)) {
            return null;
        }

        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");
        String email = userLocalDatabase.getString("email", "");
        String imageUri = userLocalDatabase.getString("imageUri", "");

        User user = new User(username, password, email, imageUri);
        return user;
    }
}
