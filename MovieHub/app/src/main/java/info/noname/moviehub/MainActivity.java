package info.noname.moviehub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import info.noname.moviehub.models.Category;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final Context mCtx = this;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button mBtnLogin;

    private AlertDialogManager mDialogManager;

    private Button mLoginButton;

    private EditText mUsernameEditText;
    private EditText mPasswrodEditText;

    private TextView mRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle(getString(R.string.menu));

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);

        // mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Category> myDataset = Category.listAll(Category.class);

        // specify an adapter (see also next example)
        mAdapter = new CategoryAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        mDialogManager = new AlertDialogManager();

        mLoginButton = (Button) findViewById(R.id.login_button);

        mUsernameEditText = (EditText) findViewById(R.id.username_edit_text);
        mPasswrodEditText = (EditText) findViewById(R.id.password_edit_text);

        mRegisterLink = (TextView) findViewById(R.id.register_link);

        //comment just for test categories

        //mLoginButton.setOnClickListener(this);
        //mRegisterLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button) {

        }

        if (v.getId() == R.id.register_link) {
            Intent intent = new Intent(this, RegisterManager.class);

            startActivity(intent);
        }
    }
}
