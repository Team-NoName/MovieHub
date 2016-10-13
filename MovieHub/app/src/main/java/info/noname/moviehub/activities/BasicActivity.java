package info.noname.moviehub.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import info.noname.moviehub.AlertDialogManager;
import info.noname.moviehub.R;
import info.noname.moviehub.UserLocalStore;
import info.noname.moviehub.fragments.LoginFragmentManager;

/**
 * Created by AKamenov on 10/13/2016.
 */

public class BasicActivity extends AppCompatActivity {

    private final Context mCtx = this;

    public UserLocalStore userLocalStore;

    public AlertDialogManager dialogManager;

    private LoginFragmentManager mLoginManager = new LoginFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle(getString(R.string.menu));

        this.userLocalStore = new UserLocalStore(this);
        this.dialogManager = new AlertDialogManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_logout) {
            this.userLocalStore.setUserLoggedIn(false);
            this.userLocalStore.clearUserData();

            Intent intent = new Intent(BasicActivity.this, MainActivity.class);

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
