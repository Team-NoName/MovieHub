package info.noname.moviehub;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class LoginManager {
    // With this instance we are going to save data to the local sotrage
    private SharedPreferences _preferences;

    // With this we gonna commit each new action to the editor
    private SharedPreferences.Editor _preferenceEditor;

    private Context _context;
    int PRIVATE_MODE = 0;

    // Is-logged KEY
    private static final String LOGIN_KEY = "login";

    // prefs name -> With thi we are going to load out xml preference file from system
    private static final String prefsName = "MovieHub";
}
