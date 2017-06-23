package songhyeonlee.teamplayboard;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    FirebaseDatabase database;

    List<Project_db> mProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.project_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mProject = new ArrayList<>();

        // specify an adapter (see also next example)
        mAdapter = new Project_Adapter(mProject);
        mRecyclerView.setAdapter(mAdapter);


        //파이어베이스 "프로젝트" DB에 저장
        DatabaseReference myRef3 = database.getReference("project");
        myRef3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                // A new comment has been added, add it to the displayed list
                Project_db project = dataSnapshot.getValue(Project_db.class);

                // [START_EXCLUDE]
                // Update RecyclerView
                //  mCommentIds.add(dataSnapshot.getKey());
                mProject.add(project);
                mAdapter.notifyItemInserted(mProject.size() - 1);
                // [END_EXCLUDE]
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void addProject(View v){
        Intent i = new Intent(getApplicationContext(), Create_Project.class);
        startActivity(i);
    }

//    public void test1(View v){
//        Intent i = new Intent(getApplicationContext(), Login.class);
//        startActivity(i);
//    }

    public void test2(View v){
        Intent i = new Intent(getApplicationContext(), Create_Kanban1.class);
        startActivity(i);
    }

    public void test3(View v){
        Intent i = new Intent(getApplicationContext(), Kanban1.class);
        startActivity(i);
    }


    public void toMain(View v){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void toAlarm(View v){
        Intent i = new Intent(getApplicationContext(), AlarmBox.class);
        startActivity(i);
    }


}
