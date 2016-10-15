package info.noname.moviehub.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import info.noname.moviehub.R;
import info.noname.moviehub.UserLocalStore;
import info.noname.moviehub.models.User;

/**
 * Created by AKamenov on 10/14/2016.
 */

public class UserDetailsActivity extends AppCompatActivity {

    private TextView mUsername;
    private TextView mEmail;

    public UserLocalStore userLocalStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_view);

        this.userLocalStore = new UserLocalStore(this);

        this.mUsername = (TextView) findViewById(R.id.username_text_view);
        this.mEmail = (TextView) findViewById(R.id.email_text_view);

        User user = this.userLocalStore.getLoggedInUser();
        if (user != null) {
            this.mUsername.setText(user.get_username());
            this.mEmail.setText(user.get_email());
        }
    }
}
