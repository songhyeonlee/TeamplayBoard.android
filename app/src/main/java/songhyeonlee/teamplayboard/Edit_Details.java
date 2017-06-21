package songhyeonlee.teamplayboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Edit_Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__details);

        Button button1 = (Button) findViewById(R.id.cancelButton);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showMessage1();
            }
        });


        Button button2 = (Button) findViewById(R.id.editButton);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showMessage3();
            }
        });

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

    private void showMessage3(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("세부사항을 수정하였습니다.");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void cancel(){
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

