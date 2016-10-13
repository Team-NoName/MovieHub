package info.noname.moviehub.activities;

import android.content.Context;
import android.os.Bundle;

import info.noname.moviehub.R;
import info.noname.moviehub.fragments.LoginFragmentManager;
import info.noname.moviehub.models.User;

public class MainActivity extends BasicActivity {
    public final Context mCtx = this;



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
    }

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.logout_button) {
//            this.mUserLocalStore.clearUserData();
//            this.mUserLocalStore.setUserLoggedIn(false);
//        }
//    }
//
    private User authenticate() {
        return this.userLocalStore.getLoggedInUser();
    }
}
