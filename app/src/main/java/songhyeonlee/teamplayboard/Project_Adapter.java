package songhyeonlee.teamplayboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Songhyeon on 2017-06-23.
 */

public class Project_Adapter extends RecyclerView.Adapter<Project_Adapter.ViewHolder>  {

    private String[] mDataset;
    List<Project_db> mProject;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.mTextView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Project_Adapter(List<Project_db> mProject) {
        this.mProject = mProject;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Project_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        // ...
        Project_Adapter.ViewHolder vh = new Project_Adapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(Project_Adapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mProject.get(position).getProjectName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mProject.size();
    }
}
