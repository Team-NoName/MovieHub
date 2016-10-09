package info.noname.moviehub;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnLogin;
    private AlertDialogManager mDialogManager;
    public final Context ctx = this;

    private EditText mTxtUsername;
    private EditText mTextUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle(getString(R.string.menu));

        mDialogManager = new AlertDialogManager();

        mBtnLogin = (Button) findViewById(R.id.login_button);

        mTxtUsername = (EditText) findViewById(R.id.username_edit_text);
        mTextUserPassword = (EditText) findViewById(R.id.password_edit_text);

        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button) {

        }
    }
}
