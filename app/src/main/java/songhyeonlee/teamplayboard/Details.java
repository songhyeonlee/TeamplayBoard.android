package songhyeonlee.teamplayboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONObject;
import songhyeonlee.teamplayboard.Comment;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

import static java.sql.DriverManager.println;

public class Details extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
//    String[] myDataset = {"안녕하세요","빨리빨리!","자료조사하셨나요?"};

    EditText messageInput;
    TextView messageOutput;

    Button sendButton;
    Button btnEdit;
    String email;
  //  String name;
    List<Comment> mComments;

    FirebaseDatabase database;

    TextView log;

    String regId;

    RequestQueue queue;

    @Override
    protected  void onNewIntent(Intent intent){
        println("onNewIntent() 호출");

        if (intent != null){
            processIntent(intent);
        }
        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){
        String from = intent.getStringExtra("from");
        if(from == null){
            println("from is null");
            return;
        }
        String contents = intent.getStringExtra("contents");

        println("DATA : " + from + ", " + contents);
        messageOutput.setText("[" + from + "]로부터 수신한 데이터 : " + contents);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        database = FirebaseDatabase.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // Name, email address, and profile photo Url
            // name = user.getDisplayName();
            email = user.getEmail();
            //  username = user.getDisplayName();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }

        //댓글 전송버튼 -> 댓글 작성자 이메일, 내용이 파이어베이스에 저장됨
        messageInput = (EditText)findViewById(R.id.messageInput);
        sendButton = (Button)findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               String stText = messageInput.getText().toString();

               if(stText.equals("") || stText.isEmpty()){
                   Toast.makeText(Details.this, "내용을 입력해 주세요.", Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(Details.this, "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show();

                   Calendar c = Calendar.getInstance();
                   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                   String formattedDate = df.format(c.getTime());

                   // Write a message to the database
                   DatabaseReference myRef = database.getReference("comments").child(formattedDate);

                   Hashtable<String, String> comment
                           = new Hashtable<String, String>();
                   comment.put("email", email);
                 //  comment.put("name", name);

                   comment.put("text", stText);

                   myRef.setValue(comment);

               }


           }
        });


        //저장된 데이터베이스를 리사이클러뷰로
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mComments = new ArrayList<>();

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(mComments);
        mRecyclerView.setAdapter(mAdapter);


        DatabaseReference myRef2 = database.getReference("comments");
        myRef2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                // A new comment has been added, add it to the displayed list
                Comment comment = dataSnapshot.getValue(Comment.class);

                // [START_EXCLUDE]
                // Update RecyclerView
              //  mCommentIds.add(dataSnapshot.getKey());
                mComments.add(comment);
                mRecyclerView.scrollToPosition(mComments.size() - 1);
                mAdapter.notifyItemInserted(mComments.size() - 1);
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


//        messageInput = (EditText) findViewById(R.id.messageInput);
//        messageOutput = (TextView) findViewById(R.id.messageOutput);
        //log = (TextView) findViewById(R.id.log);




        Button button2 = (Button) findViewById(R.id.pushButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage9();
            }
        });


        //수정버튼
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Details.this, Edit_Details.class);
                startActivity(i);
            }
        });
    }



    private void showMessage9(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("재촉 알람을 보냈습니다.");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
