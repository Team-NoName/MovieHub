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

import info.noname.moviehub.R;
import info.noname.moviehub.UserLocalStore;
import info.noname.moviehub.activities.ItemActivity;
import info.noname.moviehub.appData.UserData;
import info.noname.moviehub.models.User;

/**
 * Created by AKamenov on 10/10/2016.
 */

public class RegisterFragmentManager extends Fragment implements View.OnClickListener {

    private Button mRegisterButton;

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private EditText mUserEmail;

    private UserData mUserData;

    private UserLocalStore mUserLocalStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);

        this.mUserData = new UserData();

        this.mUserLocalStore = new UserLocalStore(getContext());

        mRegisterButton = (Button) view.findViewById(R.id.register_button);

        mUsernameEditText = (EditText) view.findViewById(R.id.username_edit_text);
        mPasswordEditText = (EditText) view.findViewById(R.id.password_edit_text);
        mUserEmail = (EditText) view.findViewById(R.id.email_edit_text);

        mRegisterButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register_button) {
            if (this.mUsernameEditText != null && this.mPasswordEditText != null && this.mUserEmail != null) {
                User addedUser =
                        this.mUserData.addUser(this.mUsernameEditText.getText().toString(),
                                this.mPasswordEditText.getText().toString(),
                                this.mUserEmail.getText().toString(),
                                "imageUri");
                if (addedUser != null) {

                    this.mUserLocalStore.setUserLoggedIn(true);
                    this.mUserLocalStore.storeUserData(addedUser);

                    Intent intent = new Intent(getContext(), ItemActivity.class);

                    startActivity(intent);
                }
            }
        }
    }
}
