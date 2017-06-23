package songhyeonlee.teamplayboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.cert.CRLReason;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

public class Create_Project extends AppCompatActivity {

    String TAG = "Create_Project";

    EditText newProject;
    Button btnAddProject;
    Button cancelButton;

    ProgressBar pbAddProjcet;

    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__project);

        database = FirebaseDatabase.getInstance();

        //프로젝트 생성 취소 버튼
        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showMessage1();
            }
        });

        newProject = (EditText)findViewById(R.id.newProject);


        //프로젝트 생성 버튼
        btnAddProject = (Button) findViewById(R.id.btnAddProject);
        btnAddProject.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String p_name = newProject.getText().toString();

                if(p_name.equals("") || p_name.isEmpty()){
                    Toast.makeText(Create_Project.this, "프로젝트 제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{

                    Project_db p = new Project_db(p_name);
                    //String formmatedNewProject = "projcet"+p.getCnt_p() ;

                    // Write a message to the database
                    DatabaseReference myRef = database.getReference("project").child(p.getProjectName());

                    Hashtable<String, String> project
                            = new Hashtable<String, String>();
                    project.put("project", p_name);

                    myRef.setValue(project);

                    showSuccess();
                }
            }
        });
    }




    private  void showSuccess(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("새로운 프로젝트가 생성되었습니다.");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                Intent i = new Intent(Create_Project.this, MainActivity.class);
                startActivity(i);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showMessage1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("취소하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                cancel();
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                // String message = "아니오 버튼이 눌렸습니다.";
                //         textView.setText(message);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void toMain(View v){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void toAlarm(View v){
        Intent i = new Intent(getApplicationContext(), AlarmBox.class);
        startActivity(i);
    }

    public void cancel(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
