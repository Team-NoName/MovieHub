package info.noname.moviehub.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import info.noname.moviehub.IOnItemClicked;
import info.noname.moviehub.R;
import info.noname.moviehub.models.Category;

/**
 * Created by Ivan on 10/11/2016.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> mDataset;
    private Context mCtx;
    private static IOnItemClicked callback;

    public void setCallback(IOnItemClicked callback){
        this.callback = callback;
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextNameView;
        public ImageView mCategoryImage;
        public RelativeLayout parent;

        public ViewHolder(View v) {
            super(v);

            mTextNameView = (TextView) v.findViewById(R.id.categoryName);
            parent = (RelativeLayout) v.findViewById(R.id.parent);
            mCategoryImage = (ImageView) v.findViewById(R.id.categoryImage);

            parent.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if (callback != null){
                        callback.onItemClicked(getAdapterPosition());
                    }
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CategoryAdapter(Context ctx, List<Category> myDataset, IOnItemClicked callback) {
        this.mCtx = ctx;
        this.mDataset = myDataset;
        this.setCallback(callback);
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
        Picasso.with(mCtx).load(mDataset.get(position).get_categoryImage()).fit().into(holder.mCategoryImage);
        holder.parent.setBackgroundColor(Color.parseColor(mDataset.get(position).get_categoryBackground()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

