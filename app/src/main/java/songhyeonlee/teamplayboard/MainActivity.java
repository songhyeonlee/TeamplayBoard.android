package songhyeonlee.teamplayboard;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    TextView defualt_addproject;
    TextView delete_explain;
    Button btnDeleteProjct;

    FirebaseDatabase database;

    List<Project_db> mProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defualt_addproject = (TextView)findViewById(R.id.defualt_addproject);
        delete_explain = (TextView)findViewById(R.id.delete_explain);
        btnDeleteProjct = (Button)findViewById(R.id.btnDeleteProjct);

        database = FirebaseDatabase.getInstance();

        mRecyclerView  = (RecyclerView) findViewById(R.id.projcet_listview);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mProject = new ArrayList<>();

        // specify an adapter (see also next example)
        mAdapter = new Project_Adapter(mProject);
        mRecyclerView.setAdapter(mAdapter);


        //파이어베이스에 저장된 DB 불러와서 뷰로 작성
        DatabaseReference myRef = database.getReference("project");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(mProject != null){
                    defualt_addproject.setVisibility(View.INVISIBLE);
                    btnDeleteProjct.setVisibility(View.VISIBLE);
                    delete_explain.setVisibility(View.VISIBLE);
                }

                // A new comment has been added, add it to the displayed list
                Project_db project = dataSnapshot.getValue(Project_db.class);

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

        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Kanban1.class);
                startActivity(i);
            }
        });

        //프로젝트 삭제 버튼
        btnDeleteProjct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


            }
        });

    }

    public void addProject(View v){
        Intent i = new Intent(getApplicationContext(), Create_Project.class);
        startActivity(i);
    }


    public void test2(View v){
        Intent i = new Intent(getApplicationContext(), Create_Kanban1.class);
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

    public void toKanban1(View v){
        Intent i = new Intent(getApplicationContext(), Kanban1.class);
        startActivity(i);
    }



}
