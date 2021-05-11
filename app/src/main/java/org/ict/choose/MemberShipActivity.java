package org.ict.choose;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Member;

public class MemberShipActivity extends AppCompatActivity {
    //태그
    private static final String TAG = "MemberShipActivity";
    // 파이어베이스 선언
    private FirebaseAuth mAuth;
    EditText memberPwdEdt, memberPwdCheckEdt, memberEmailEdt;
    CheckBox memberBox;
    Button membershipBtn;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership);
        setTitle("회원가입");

        mAuth = FirebaseAuth.getInstance();

        memberPwdEdt = (EditText)findViewById(R.id.memberPwdEdt);
        memberPwdCheckEdt = (EditText)findViewById(R.id.memberPwdCheckEdt);
        memberEmailEdt = (EditText)findViewById(R.id.memberEmailEdt);
        memberBox = (CheckBox)findViewById(R.id.memberBox);
        membershipBtn = (Button) findViewById(R.id.membershipBtn);


//        String nameEdt = memberNameEdt.getText().toString();
//        String idEdt = memberIdEdt.getText().toString();
//        String pwdEdt = memberPwdEdt.getText().toString();
//        String numEdt = memberPhoneEdt.getText().toString();

        membershipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(memberEmailEdt.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "이메일을 입력하세요.", Toast.LENGTH_SHORT).show();
                    memberEmailEdt.requestFocus();
                    return;
                }

                if(memberPwdEdt.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    memberPwdEdt.requestFocus();
                    return;
                }
                if(memberPwdEdt.getText().toString().length() < 6) {
                    Toast.makeText(getApplicationContext(), "비밀번호는 6자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
                    memberPwdEdt.requestFocus();
                    return;
                }

                if(memberPwdCheckEdt.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 한번 더 입력하세요.", Toast.LENGTH_SHORT).show();
                    memberPwdCheckEdt.requestFocus();
                    return;
                }

                if (!memberBox.isChecked()){
                    Toast.makeText(getApplicationContext(), "개인정보 수집을 동의해주세요.", Toast.LENGTH_SHORT).show();
                    memberBox.requestFocus();
                    return;
                }

                if(!memberPwdCheckEdt.getText().toString().equals(memberPwdEdt.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show();
                    memberPwdEdt.requestFocus();
                    return;
                }

                join();


            }
        });//memberShipBtn
    }//onCreate

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }//on Start

    private void join(){
        String email = ((EditText)findViewById(R.id.memberEmailEdt)).getText().toString();
        String pwd = ((EditText)findViewById(R.id.memberPwdEdt)).getText().toString();
        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            //UI
                        } else {
                            Toast.makeText(getApplicationContext(), "이미 존재하는 이메일입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}




























