package info.noname.moviehub.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import info.noname.moviehub.R;
import info.noname.moviehub.models.Category;

/**
 * Created by Ivan on 10/11/2016.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextNameView;
        public RelativeLayout parent;

        public ViewHolder(View v) {
            super(v);

            mTextNameView = (TextView) v.findViewById(R.id.categoryName);
            parent = (RelativeLayout) v.findViewById(R.id.parent);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CategoryAdapter(List<Category> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_recycle_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
      //  holder.mTextNameView.setText(mDataset[position]);
        holder.mTextNameView.setText(mDataset.get(position).get_name());
        holder.parent.setBackgroundColor(Color.parseColor("#3333ff"));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

