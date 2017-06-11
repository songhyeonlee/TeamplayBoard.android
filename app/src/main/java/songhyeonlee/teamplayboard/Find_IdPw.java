package songhyeonlee.teamplayboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Find_IdPw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__id_pw);
    }

    public void test_pw(View v){
        Intent i = new Intent(getApplicationContext(), Change_PW.class);
        startActivity(i);
    }
}
