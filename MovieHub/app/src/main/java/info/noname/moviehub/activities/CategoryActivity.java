package info.noname.moviehub.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import info.noname.moviehub.R;
import info.noname.moviehub.adapters.CategoryAdapter;
import info.noname.moviehub.models.Category;

/**
 * Created by AKamenov on 10/13/2016.
 */

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_layout);

        mRecyclerView = (RecyclerView)findViewById(R.id.category_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Category> myDataset = Category.listAll(Category.class);

        // specify an adapter (see also next example)
        mAdapter = new CategoryAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
}
