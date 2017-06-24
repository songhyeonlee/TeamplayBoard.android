package songhyeonlee.teamplayboard;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    String TAG = "Login";

    EditText etEmail;
    EditText etPassword;
    String stemail;
    String stpassword;

    ProgressBar pbLogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        pbLogin = (ProgressBar) findViewById(R.id.pbLogin);

        Button bntRegister = (Button) findViewById(R.id.bntRegister);
        Button bntLogin = (Button) findViewById(R.id.bntLogin);
        Button findidpwButton = (Button)findViewById(R.id.findidpwButton);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        //회원가입 버튼
        bntRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                    Intent i = new Intent(Login.this, Join.class);
                    startActivity(i);
            }
        });


        //로그인 버튼
        bntLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                stemail = etEmail.getText().toString();
                stpassword = etPassword.getText().toString();

                if(stemail.equals("")||stemail.isEmpty()||stpassword.equals("")||stpassword.isEmpty()){
                    Toast.makeText(Login.this, "아이디나 비밀번호를 모두 입력해주세요.",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    userLogin(stemail, stpassword);
                }
            }
        });


        //아이디/비밀번호 찾기 버튼
        findidpwButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Login.this, Find_IdPw.class);
                startActivity(i);
            }
        });
    }



    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



    //로그인 성공 or 실패
    private void userLogin(String email, String password){
        pbLogin.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        pbLogin.setVisibility(View.GONE);

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(Login.this, "회원정보가 없습니다.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                        }

                    }
                });
    }





    public void toKanban1(View v){
        Intent i = new Intent(Login.this, Kanban1.class);
        startActivity(i);
    }

    public void toCreateKanban(View v){
        Intent i = new Intent(Login.this, Create_Kanban1.class);
        startActivity(i);
    }

}


