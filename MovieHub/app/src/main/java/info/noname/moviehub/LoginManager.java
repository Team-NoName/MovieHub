package info.noname.moviehub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class LoginManager extends AppCompatActivity implements View.OnClickListener{
//    // With this instance we are going to save data to the local sotrage
//    private SharedPreferences _preferences;
//
//    // With this we gonna commit each new action to the editor
//    private SharedPreferences.Editor _preferenceEditor;
//
//    private Context _context;
//    int PRIVATE_MODE = 0;
//
//    // Is-logged KEY
//    private static final String LOGIN_KEY = "login";
//
//    // prefs name -> With thi we are going to load out xml preference file from system
//    private static final String prefsName = "MovieHub";

    private Button mLoginButton;
    private EditText mUsernameEditText;
    private EditText mPasswrodEditText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        mLoginButton = (Button) findViewById(R.id.login_button);
        mUsernameEditText = (EditText) findViewById(R.id.username_edit_text);
        mPasswrodEditText = (EditText) findViewById(R.id.password_edit_text);

        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button) {

        }
    }
}
