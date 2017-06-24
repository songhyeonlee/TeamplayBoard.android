package songhyeonlee.teamplayboard;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.cert.CRLReason;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

public class Create_Project extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    String TAG = "Create_Project";

    EditText newProject;
    Button btnAddProject;
    Button cancelButton;
    ImageButton datepicker;
    TextView duedate_projcet;
    EditText addProject_member;

    String email;

    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    ProgressBar pbAddProjcet;

    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__project);

        database = FirebaseDatabase.getInstance();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            email = user.getEmail();
        }


        //날짜기한설정버튼
        datepicker = (ImageButton) findViewById(R.id.datepicker);
        duedate_projcet = (TextView) findViewById(R.id.duedate_projcet);

        datepicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Create_Project.this, Create_Project.this, year,month,day);
                datePickerDialog.show();
            }
        });

        database = FirebaseDatabase.getInstance();

        //프로젝트 생성 취소 버튼
        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showMessage1();
            }
        });


        //프로젝트 이름
        newProject = (EditText)findViewById(R.id.newProject);

        //프로젝트 생성 버튼
        btnAddProject = (Button) findViewById(R.id.btnAddProject);
        btnAddProject.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String p_name = newProject.getText().toString();
                String p_duedate = duedate_projcet.getText().toString();

                if(p_name.equals("") || p_name.isEmpty()){
                    Toast.makeText(Create_Project.this, "프로젝트 제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{

                    Project_db p = new Project_db(p_name, p_duedate,email);

                    //String formmatedNewProject = "projcet"+p.getCnt_p() ;

                    // 파이어베이스 "project" DB에 저장
                    DatabaseReference myRef = database.getReference("project").child(p.getProject_duedate()+" : "+ p.getProjectName());

                    Hashtable<String, String> project
                            = new Hashtable<String, String>();
                    project.put("project_name", p_name);
                    //project.put("member",);
                    project.put("project_duedate", p_duedate);
                    project.put("project_leader",email);

                    myRef.setValue(project);
                    showSuccess();
                }
            }
        });
    }



//프로젝트 생성 성공 알림박스
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

    //프로젝트 생성 취소 버튼
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


    //하단 버튼 메인페이지로
    public void toMain(View v){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    //하단 버튼 알람페이지로
    public void toAlarm(View v){
        Intent i = new Intent(getApplicationContext(), AlarmBox.class);
        startActivity(i);
    }


    public void cancel(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void show_details(View v){
        Intent i = new Intent(getApplicationContext(), Details.class);
        startActivity(i);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        duedate_projcet.setText(yearFinal+"-"+monthFinal+"-"+dayFinal);


        Calendar c = Calendar.getInstance();
//        hour = c.get(Calendar.HOUR_OF_DAY);
//        minute = c.get(Calendar.MINUTE);

//        TimePickerDialog timePickerDialog = new TimePickerDialog(Create_Project.this, Create_Project.this,
//                hour,minute, DateFormat.is24HourFormat(this));
//        timePickerDialog.show();
        }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;

        duedate_projcet.setText(yearFinal+"-"+monthFinal+"-"+dayFinal);


    }
}


