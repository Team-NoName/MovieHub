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

import info.noname.moviehub.adapters.CategoryAdapter;
import info.noname.moviehub.IOnItemClicked;
import info.noname.moviehub.R;
import info.noname.moviehub.appData.CommentData;
import info.noname.moviehub.models.Category;
import info.noname.moviehub.models.Movie;
import info.noname.moviehub.models.MovieComments;

/**
 * Created by Ivan on 10/13/2016.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<MovieComments> mDataset;
    private List<String> strList;
    private Context mCtx;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public TextView mUsername;
        public RelativeLayout commentParent;

        public ViewHolder(View v) {
            super(v);
            commentParent = (RelativeLayout) v.findViewById(R.id.commentParent);
            mTextView = (TextView) v.findViewById(R.id.commentsContent);
            mUsername = (TextView) v.findViewById(R.id.userCommentName);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CommentAdapter(Context ctx, List<MovieComments> myDataset) {
        this.mCtx = ctx;
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comments_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters

        CommentAdapter.ViewHolder vh = new CommentAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //  holder.mTextNameView.setText(mDataset[position]);





        MovieComments mc = mDataset.get(position);
            holder.mTextView.setText(mc.get_comment());
            holder.mUsername.setText(mc.get_user().get_username());
            holder.commentParent.setBackgroundColor(Color.parseColor("#ccccb3"));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}