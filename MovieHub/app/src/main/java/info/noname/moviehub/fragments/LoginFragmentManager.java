package info.noname.moviehub.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import info.noname.moviehub.AlertDialogManager;
import info.noname.moviehub.R;
import info.noname.moviehub.UserLocalStore;
import info.noname.moviehub.activities.CategoryActivity;
import info.noname.moviehub.appData.UserData;
import info.noname.moviehub.models.User;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class LoginFragmentManager extends Fragment implements View.OnClickListener {

    private AlertDialogManager mDialogManager;

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    private UserLocalStore mUserLocalStore;

    private UserData mUserData;

    private RegisterFragmentManager mRegFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        this.mDialogManager = new AlertDialogManager();

        this.mUserLocalStore = new UserLocalStore(getContext());

        this.mUserData = new UserData();

        this.mRegFragment = new RegisterFragmentManager();

        Button mLoginButton = (Button) view.findViewById(R.id.login_button);

        mUsernameEditText = (EditText) view.findViewById(R.id.username_edit_text);
        mPasswordEditText = (EditText) view.findViewById(R.id.password_edit_text);

        TextView mRegisterLink = (TextView) view.findViewById(R.id.register_link);

        mLoginButton.setOnClickListener(this);
        mRegisterLink.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.login_button) {

            if (this.mUsernameEditText != null && this.mPasswordEditText != null) {

                User user = this.mUserData.getUser(this.mUsernameEditText.getText().toString(),
                        this.mPasswordEditText.getText().toString());
                if (user != null) {

                    this.mUserLocalStore.setUserLoggedIn(true);
                    this.mUserLocalStore.storeUserData(user);

                    Intent intent = new Intent(getContext(), CategoryActivity.class);

                    startActivity(intent);
                } else {
                    this.mDialogManager.showAlert(getContext(), "Incorrect user account", "Your username or password is incorrect!");
                }
            }
        }

        if (v.getId() == R.id.register_link) {
            getFragmentManager().beginTransaction().replace(R.id.fragment_content, this.mRegFragment).commit();
        }
    }
}
