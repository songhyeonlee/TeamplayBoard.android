package songhyeonlee.teamplayboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Button button2 = (Button) findViewById(R.id.pushButton);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showMessage9();
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

    public void edit_details(View v){
        Intent i = new Intent(getApplicationContext(), Edit_Details.class);
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
