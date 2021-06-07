package org.ict.choose;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    // 파이어베이스 선언
    private FirebaseAuth mAuth;

    EditText loginEmail, loginPwd;
    Button loginBtn;
    TextView membershipText, changePwdText;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("로그인");

        loginEmail = (EditText)findViewById(R.id.loginEmail);
        loginPwd = (EditText)findViewById(R.id.loginPwd);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        membershipText = (TextView)findViewById(R.id.membershipText);
        changePwdText = (TextView)findViewById(R.id.changePwdText);
        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loginEmail.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    loginEmail.requestFocus();
                    return;
                }
                if (loginPwd.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    loginPwd.requestFocus();
                    return;
                }
                    login();
            }
        });

        membershipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MemberShipActivity.class);
                startActivity(intent);
            }
        });

        changePwdText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ChangePwdActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        String email = ((EditText)findViewById(R.id.loginEmail)).getText().toString();
        String pwd = ((EditText)findViewById(R.id.loginPwd)).getText().toString();
        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            intent = new Intent(getApplicationContext(), MainPageActivity.class);
                            startActivity(intent);
                            loginEmail.setText("");
                            loginPwd.setText("");
                        } else {
                            if(task.getException() != null){
                                Toast.makeText
                                        (getApplicationContext(), "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }
                });
        }//login

    private long backKeyPressedTime = 0;
    @Override public void onBackPressed() { //super.onBackPressed();
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
        }
    }//onBackPressed
}








