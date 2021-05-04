package org.ict.choose;

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

public class MainActivity extends AppCompatActivity {

    EditText loginId, loginPwd;
    Button loginBtn;
    TextView membershipText, findText;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("로그인");

        loginId = (EditText)findViewById(R.id.loginId);
        loginPwd = (EditText)findViewById(R.id.loginPwd);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        membershipText = (TextView)findViewById(R.id.membershipText);
        findText = (TextView)findViewById(R.id.findText);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loginId.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    loginId.requestFocus();
                    return;
                }
                if (loginPwd.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    loginPwd.requestFocus();
                    return;
                }

                intent = new Intent(getApplicationContext(), MainPageActivity.class);

                startActivity(intent);


            }
        });

        membershipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MemberShipActivity.class);

                startActivity(intent);
            }
        });

        findText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), FindActivity.class);

                startActivity(intent);
            }
        });

    }

}








