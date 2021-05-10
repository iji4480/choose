package org.ict.choose;

import android.app.Activity;
import android.content.Context;
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

    EditText memberNameEdt, memberIdEdt, memberPwdEdt, memberPwdCheckEdt, memberPhoneEdt;
    CheckBox memberBox;
    Button membershipBtn;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership);
        setTitle("회원가입");

        mAuth = FirebaseAuth.getInstance();

        memberNameEdt = (EditText)findViewById(R.id.memberNameEdt);
        memberIdEdt = (EditText)findViewById(R.id.memberIdEdt);
        memberPwdEdt = (EditText)findViewById(R.id.memberPwdEdt);
        memberPwdCheckEdt = (EditText)findViewById(R.id.memberPwdCheckEdt);
        memberPhoneEdt = (EditText)findViewById(R.id.memberPhoneEdt);
        memberBox = (CheckBox)findViewById(R.id.memberBox);
        membershipBtn = (Button) findViewById(R.id.membershipBtn);


//        String nameEdt = memberNameEdt.getText().toString();
//        String idEdt = memberIdEdt.getText().toString();
//        String pwdEdt = memberPwdEdt.getText().toString();
//        String numEdt = memberPhoneEdt.getText().toString();


        membershipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(memberNameEdt.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                    memberNameEdt.requestFocus();
                    return;
                }
                if(memberIdEdt.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    memberIdEdt.requestFocus();
                    return;
                }
                if(memberPwdEdt.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    memberPwdEdt.requestFocus();
                    return;
                }
                if(memberPwdCheckEdt.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 한번 더 입력하세요.", Toast.LENGTH_SHORT).show();
                    memberPwdCheckEdt.requestFocus();
                    return;
                }
                if(memberPhoneEdt.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "전화번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    memberPhoneEdt.requestFocus();
                    return;
                }
                if (!memberBox.isChecked()){
                    Toast.makeText(getApplicationContext(), "개인정보 수집을 동의해주세요.", Toast.LENGTH_SHORT).show();
                    memberBox.requestFocus();
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
        String phone = ((EditText)findViewById(R.id.memberPhoneEdt)).getText().toString();
        String pwd = ((EditText)findViewById(R.id.memberPwdEdt)).getText().toString();
        mAuth.createUserWithEmailAndPassword(phone, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //UI
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //UI
                        }
                    }
                });

    }

}




























