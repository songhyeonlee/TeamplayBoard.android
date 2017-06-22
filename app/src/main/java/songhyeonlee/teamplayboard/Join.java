package songhyeonlee.teamplayboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Hashtable;

public class Join extends AppCompatActivity {

    String TAG = "Login";

    Button bntjoin;
    Button bntCancel;
    EditText input_name;
    EditText input_email;
    EditText input_id;
    EditText input_pw;
    EditText input_re_pw;

    FirebaseDatabase database;
    //static int cnt = 1;

    ProgressBar pbJoin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        database = FirebaseDatabase.getInstance();


        bntjoin = (Button)findViewById(R.id.bntjoin);
        bntjoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String n = input_name.getText().toString();
                String e = input_email.getText().toString();
                String i = input_id.getText().toString();
                String p = input_pw.getText().toString();
                String rp = input_re_pw.getText().toString();

                if(n.equals("")||n.isEmpty()||e.equals("")||e.isEmpty()||i.equals("")||i.isEmpty()||p.equals("")||p.isEmpty()||rp.equals("")||rp.isEmpty()){
                    showM1();
                }
                //     else if(p!=rp){
                //       showM2();
                //  }
                else if(p.equals(rp)){
                    registerUser(e, p);

                    // Join_db join = new Join_db(n, e, i, p);

                    Join_db j = new Join_db(n,e,i,p);
                   // String formmatedJoin = "join"+j.getCnt() ;
                    //  cnt++;

                    // Write a message to the database
                    DatabaseReference myRef = database.getReference("join").child(j.getId());

                    Hashtable<String, String> join
                            = new Hashtable<String, String>();
                    join.put("name", n);
                    join.put("email", e);
                    join.put("id", i);
                    join.put("password", p);

                    myRef.setValue(join);
                }

                else{
                    showM2();
                }
            }
        });


        bntCancel = (Button)findViewById(R.id.bntCancel);
        bntCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showCancel();
            }
        });

        input_name = (EditText)findViewById(R.id.join_name);
        input_email = (EditText)findViewById(R.id.join_email);
        input_id = (EditText)findViewById(R.id.join_id);
        input_pw = (EditText)findViewById(R.id.join_pw);
        input_re_pw = (EditText)findViewById(R.id.join_pwpw);

        pbJoin = (ProgressBar) findViewById(R.id.pbJoin);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


    }

    public void registerUser(String email, String password){

        pbJoin.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());


                        pbJoin.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.



                        if (!task.isSuccessful()) {
                            Toast.makeText(Join.this, "Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            showSuccess();
                        }

                        // ...
                    }
                });
    }

    private  void showM1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("모두 입력해 주세요");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private  void showM2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("비밀번호를 확인해주세요");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private  void showSuccess(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("가입이 완료되었습니다.");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                Intent i = new Intent(Join.this, Login.class);
                startActivity(i);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showCancel(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("가입을 취소하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                Intent i = new Intent(Join.this, Login.class);
                startActivity(i);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                Toast.makeText(Join.this, "회원가입을 계속 진행합니다.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}