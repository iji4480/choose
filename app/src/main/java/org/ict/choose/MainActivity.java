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

public class MainActivity extends AppCompatActivity {

    EditText loginId, loginPwd;
    Button loginBtn;
    TextView membershipText, findText;
    myDBHelper myHelper;
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

        myHelper = new myDBHelper(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


































