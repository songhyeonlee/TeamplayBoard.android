package songhyeonlee.teamplayboard;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addProject(View v){
        Intent i = new Intent(getApplicationContext(), Create_Project.class);
        startActivity(i);
    }

    public void test1(View v){
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }

    public void test2(View v){
        Intent i = new Intent(getApplicationContext(), Create_Kanban1.class);
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
