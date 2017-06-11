package songhyeonlee.teamplayboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Kanban2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanban2);
    }

    public void toKanban1(View v) {
        Intent i = new Intent(getApplicationContext(), Kanban1.class);
        startActivity(i);
    }

    public void toKanban3(View v) {
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
