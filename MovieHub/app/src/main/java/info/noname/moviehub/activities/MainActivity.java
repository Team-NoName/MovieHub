package info.noname.moviehub.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import info.noname.moviehub.AlertDialogManager;
import info.noname.moviehub.R;
import info.noname.moviehub.UserLocalStore;
import info.noname.moviehub.fragments.LoginFragmentManager;
import info.noname.moviehub.models.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public final Context mCtx = this;

    private AlertDialogManager mDialogManager;

    private Button mGoToLogin;

    private UserLocalStore mUserLocalStore;

    private LoginFragmentManager mLoginManager = new LoginFragmentManager();

    @Override
    protected void onStart() {
        super.onStart();

        User getAuthenticatedUser = this.authenticate();
        if (getAuthenticatedUser == null) {
            getFragmentManager().beginTransaction().replace(R.id.fragment_content, this.mLoginManager).commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle(getString(R.string.menu));

        mDialogManager = new AlertDialogManager();
        mGoToLogin = (Button) findViewById(R.id.logout_button);

        mGoToLogin.setOnClickListener(this);

        this.mUserLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.logout_button) {
            this.mUserLocalStore.clearUserData();
            this.mUserLocalStore.setUserLoggedIn(false);
        }
    }

    private User authenticate() {
        return this.mUserLocalStore.getLoggedInUser();
    }
}
