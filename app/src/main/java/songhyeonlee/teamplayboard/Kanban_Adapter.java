package songhyeonlee.teamplayboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Songhyeon on 2017-06-25.
 */

public class Kanban_Adapter  extends RecyclerView.Adapter<Kanban_Adapter.ViewHolder>{
    List<kanban_db> mKanban;

    // FirebaseDatabase database;
   // DatabaseReference myRef = database.getReference("kanban");

    //database.child("users").child(state).getValue();


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button mKanbanName;
        public Button mKanbanNext;
        public Button mKanbanBack;
        public TextView mKanbandueMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            mKanbanName = (Button) itemView.findViewById(R.id.mKanbanName);
 //           mKanbanNext = (Button) itemView.findViewById(R.id.mKanbanNext);
//            mKanbanBack = (Button) itemView.findViewById(R.id.mKanbanBack);
            mKanbandueMessage = (TextView) itemView.findViewById(R.id.mKanbandueMessage);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Kanban_Adapter(List<kanban_db> mKanban) {
        this.mKanban = mKanban;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Kanban_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kanban_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mKanbanName.setText(mKanban.get(position).getKanban_name());
        holder.mKanbandueMessage.setText("마감기한 : " + mKanban.get(position).getKanban_duedate());


//        holder.mKanbanNext.setText("진행하기 >> ");
//        holder.mKanbanBack.setText("");

//        holder.mKanbanNext.setText("완료하기 >> ");
//        holder.mKanbanBack.setText(" << 시작 전으로");

//        holder.mKanbanNext.setText("");
//        holder.mKanbanBack.setText(" << 진행 중으로");


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mKanban.size();
    }
}
