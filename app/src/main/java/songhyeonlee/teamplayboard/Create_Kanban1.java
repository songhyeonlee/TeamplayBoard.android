package songhyeonlee.teamplayboard;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Hashtable;

public class Create_Kanban1 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    EditText newKanban;
    Button btnAddKanban;
    Button cancelButton;
    ImageButton datepicker;
    TextView duedate_kanban;
    EditText kanban_memo;

    String email;
    String uid;
    String state;

    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    ProgressBar pbAddProjcet;

    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__kanban1);

        newKanban = (EditText)findViewById(R.id.newKanban);
        btnAddKanban = (Button) findViewById(R.id.btnAddKanban);
        cancelButton = (Button)findViewById(R.id.cancelButton);
        datepicker = (ImageButton) findViewById(R.id.datepicker);
        duedate_kanban = (TextView)findViewById(R.id.duedate_kanban);
        kanban_memo = (EditText)findViewById(R.id.kanban_memo);

        database = FirebaseDatabase.getInstance();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            email = user.getEmail();
            uid = user.getUid();
        }


        //날짜기한설정버튼
        datepicker = (ImageButton) findViewById(R.id.datepicker);
        duedate_kanban = (TextView) findViewById(R.id.duedate_kanban);

        datepicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Create_Kanban1.this, Create_Kanban1.this, year,month,day);
                datePickerDialog.show();
            }
        });

        database = FirebaseDatabase.getInstance();

        //칸반1 생성 취소 버튼
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showMessage_cancel();
            }
        });


        //프로젝트 이름
        newKanban = (EditText)findViewById(R.id.newKanban);

        //프로젝트 생성 버튼
        btnAddKanban.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //String p_name =
                String k_name = newKanban.getText().toString();
                String k_duedate = duedate_kanban.getText().toString();
                String k_memo = kanban_memo.getText().toString();

                if(k_name.equals("") || k_name.isEmpty()){
                    Toast.makeText(Create_Kanban1.this, "할일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{

                    kanban_db k = new kanban_db(k_name, k_duedate,k_memo);

                    //DatabaseReference myRef2 = database.getReference("join").child(uid).child("MY projects");
                    //String key = myRef2.push().getKey();


                    // 파이어베이스  DB에 저장
               //     DatabaseReference myRef = database.getReference("join").child(uid).child("MY projects").child(key).child(k.getKanban_duedate()+" : "+ k.getKanban_name());





//                    DatabaseReference myRef2 = database.getReference("kanban").child(uid).child("MY projects");
  //                  String key = myRef2.push().getKey();

                    DatabaseReference myRef = database.getReference("kanban").child(uid).child(k.getKanban_duedate()+" : "+ k.getKanban_name());





                    //파이어베이스에 저장된 DB 불러와서 뷰로 작성
                    //DatabaseReference myRef = database.getReference("join").child(uid).child("MY projects").child(key);


                    Hashtable<String, String> kanban
                            = new Hashtable<String, String>();
                    kanban.put("kanban_name", k_name);
                    //project.put("member",);
                    kanban.put("kanban_duedate", k_duedate);
                    kanban.put("kanban_memo",k_memo);
                    kanban.put("state","step1");

                    myRef.setValue(kanban);
                    showSuccess();
                }
            }
        });
    }



    //칸반1 생성 성공 알림박스
    private  void showSuccess(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("새로운 할 일을 추가하였습니다.");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                Intent i = new Intent(Create_Kanban1.this, Kanban1.class);
                startActivity(i);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //프로젝트 생성 취소 버튼
    private void showMessage_cancel(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("취소하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                Intent i = new Intent(Create_Kanban1.this, Kanban1.class);
                startActivity(i);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
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


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        duedate_kanban.setText(yearFinal+"-"+monthFinal+"-"+dayFinal);


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

        duedate_kanban.setText(yearFinal+"-"+monthFinal+"-"+dayFinal);

    }

}

