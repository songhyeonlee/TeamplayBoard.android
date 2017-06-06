package songhyeonlee.teamplayboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void toJoin(View v){
        Intent i = new Intent(getApplicationContext(), Join.class);
        startActivity(i);
    }

    public void findIDPW(View v){
        Intent i = new Intent(getApplicationContext(), Find_IdPw.class);
        startActivity(i);
    }


}
