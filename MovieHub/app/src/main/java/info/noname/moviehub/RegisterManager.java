package info.noname.moviehub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by AKamenov on 10/10/2016.
 */

public class RegisterManager extends AppCompatActivity implements View.OnClickListener {

    private Button mRegisterButton;
    private EditText mUsernameEditText;
    private EditText mPasswrodEditText;
    private EditText mUserEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        mRegisterButton = (Button) findViewById(R.id.login_button);
        mUsernameEditText = (EditText) findViewById(R.id.username_edit_text);
        mPasswrodEditText = (EditText) findViewById(R.id.password_edit_text);
        mUserEmail = (EditText) findViewById(R.id.email_edit_text);

        mRegisterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register_button) {

        }
    }
}
