package songhyeonlee.teamplayboard;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Change_PW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__pw);

 //       textView = (TextView)findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.changepwButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showMessage();
            }
        });
    }

    private  void showMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("새로운 비밀번호가 설정되었습니다.");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
               // String message = "예 버튼이 눌렸습니다.";
   //             textView.setText(message);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
