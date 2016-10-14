package info.noname.moviehub.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import info.noname.moviehub.IOnItemClicked;
import info.noname.moviehub.R;
import info.noname.moviehub.adapters.CategoryAdapter;
import info.noname.moviehub.models.Category;
import info.noname.moviehub.models.MovieCategories;

/**
 * Created by AKamenov on 10/13/2016.
 */

public class CategoryActivity extends BasicActivity implements IOnItemClicked {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Category> myDataset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        mRecyclerView = (RecyclerView)findViewById(R.id.category_recycle_view);

        // mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = Category.listAll(Category.class);

        // specify an adapter (see also next example)
        mAdapter = new CategoryAdapter(this, myDataset, this);
        mRecyclerView.setAdapter(mAdapter);
    }

     @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(CategoryActivity.this, MovieActivity.class);

        Category category = myDataset.get(position);

        List<MovieCategories> movies = category.getAllMoviesByCategory();

        intent.putExtra("category_name", category.get_name());
        intent.putParcelableArrayListExtra("category_movies", (ArrayList<? extends Parcelable>) movies);

        startActivity(intent);
    }
}
