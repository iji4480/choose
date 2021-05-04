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

        String name = memberNameEdt.getText().toString();
        String id = memberIdEdt.getText().toString();
        String pwd = memberPwdEdt.getText().toString();
        String num = memberPhoneEdt.getText().toString();

        membershipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("insert into membertable values ('" + name + "','" + id + "','" + pwd + "','" + num + "');");

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




























