package songhyeonlee.teamplayboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Kanban1 extends AppCompatActivity {

    private Spinner spinner;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Button addKanban;
    TextView defualt_addkanban;
    Button btnDeleteKanban;

    String email;
    String uid;

    FirebaseDatabase database;
    List<kanban_db> mKanban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanban1);

        spinner = (Spinner) findViewById(R.id.spinner);

        final ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        String[] list2 = new String[2];
        list2[0] = "A";
        list2[1] = "B";

        ArrayAdapter spinnerAdapter;
        spinnerAdapter = new ArrayAdapter(Kanban1.this, R.layout.support_simple_spinner_dropdown_item,list2);
        spinner.setAdapter(spinnerAdapter);

        //event listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Kanban1.this,"선택된 아이템 : "+spinner.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //새 할일 만들기 버튼
        addKanban = (Button)findViewById(R.id.addKanban);
        addKanban.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Kanban1.this, Create_Kanban1.class);
                startActivity(i);
            }
        });


        // 할일 삭제 버튼
        btnDeleteKanban = (Button)findViewById(R.id.btnDeleteKanban);
        btnDeleteKanban.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });


        defualt_addkanban = (TextView) findViewById(R.id.defualt_addkanban);


        database = FirebaseDatabase.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            email = user.getEmail();
            uid = user.getUid();
        }

        mRecyclerView  = (RecyclerView) findViewById(R.id.kanban1_listview);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mKanban = new ArrayList<>();

        // specify an adapter (see also next example)
        mAdapter = new Kanban_Adapter(mKanban, Kanban1.this);
        mRecyclerView.setAdapter(mAdapter);



//파이어베이스에 저장된 DB 불러와서 뷰로 작성
        DatabaseReference myRef = database.getReference("user").child(uid).child("MY projects");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(mKanban != null){
                    defualt_addkanban.setVisibility(View.INVISIBLE);
                    btnDeleteKanban.setVisibility(View.VISIBLE);
                }

                // A new comment has been added, add it to the displayed list
                kanban_db kanban = dataSnapshot.getValue(kanban_db.class);

                mKanban.add(kanban);
                mAdapter.notifyItemInserted(mKanban.size() - 1);
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

    public void test2(View v){
        Intent i = new Intent(getApplicationContext(), Create_Kanban1.class);
        startActivity(i);
    }

    public void toKanban2(View v){
        Intent i = new Intent(getApplicationContext(), Kanban2.class);
        startActivity(i);
    }

    public void toKanban3(View v){
        Intent i = new Intent(getApplicationContext(), Kanban3.class);
        startActivity(i);
    }

    public void show_details(View v){
        Intent i = new Intent(getApplicationContext(), Details.class);
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


