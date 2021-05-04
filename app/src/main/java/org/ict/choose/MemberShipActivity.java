package org.ict.choose;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Member;

public class MemberShipActivity extends AppCompatActivity {

    EditText memberNameEdt, memberIdEdt, memberPwdEdt, memberPwdCheckEdt, memberPhoneEdt;
    CheckBox memberBox;
    Button membershipBtn;

    myDBHelper myHelper;

    SQLiteDatabase sqlDB;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership);
        setTitle("회원가입");

        memberNameEdt = (EditText)findViewById(R.id.memberNameEdt);
        memberIdEdt = (EditText)findViewById(R.id.memberIdEdt);
        memberPwdEdt = (EditText)findViewById(R.id.memberPwdEdt);
        memberPwdCheckEdt = (EditText)findViewById(R.id.memberPwdCheckEdt);
        memberPhoneEdt = (EditText)findViewById(R.id.memberPhoneEdt);
        memberBox = (CheckBox)findViewById(R.id.memberBox);
        membershipBtn = (Button) findViewById(R.id.membershipBtn);

        myHelper = new myDBHelper(this);

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
                if (!memberPwdEdt.getText().toString().equals(memberPwdCheckEdt.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 다시 확인하세요.", Toast.LENGTH_SHORT).show();
                    memberPwdEdt.requestFocus();
                    return;
                }



                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("insert into membertable values " +
                        "('" + memberNameEdt + "','" + memberIdEdt + "','" + memberPwdEdt + "','" + memberPhoneEdt + "');");

                sqlDB.close();

            }
        });


    }

    public class myDBHelper extends SQLiteOpenHelper{


        public myDBHelper(Context context){

            super(context, "memberDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("create table membertable(mname char(20), mid char(20) primary key,  mpwd char(20), mnum char(20));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists membertable;");
            onCreate(db);

        }
    }

}




























