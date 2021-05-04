package org.ict.choose;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FindActivity extends TabActivity {

    EditText findIdNameEdt, findIdPhoneEdt, findPwdNameEdt, findPwdIdEdt, findPwdPhoneEdt;
    Button findIdBtn, findPwdBtn;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find);
        setTitle("id/pwd찾기");

        findIdNameEdt = (EditText)findViewById(R.id.findIdNameEdt);
        findIdPhoneEdt = (EditText)findViewById(R.id.findIdPhoneEdt);
        findPwdNameEdt = (EditText)findViewById(R.id.findPwdNameEdt);
        findPwdIdEdt = (EditText)findViewById(R.id.findPwdIdEdt);
        findPwdPhoneEdt = (EditText)findViewById(R.id.findPwdPhoneEdt);

        findIdBtn = (Button)findViewById(R.id.findIdBtn);
        findPwdBtn = (Button)findViewById(R.id.findPwdBtn);

        findIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (findIdNameEdt.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                    findIdNameEdt.requestFocus();
                    return;
                }
                if (findIdPhoneEdt.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    findIdPhoneEdt.requestFocus();
                    return;
                }
                intent = new Intent(getApplicationContext(), FindIdActivity.class);
                startActivity(intent);
            }
        });

        findPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (findPwdNameEdt.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "이름 입력하세요.", Toast.LENGTH_SHORT).show();
                    findPwdNameEdt.requestFocus();
                    return;
                }
                if (findPwdIdEdt.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    findPwdIdEdt.requestFocus();
                    return;
                }
                if (findPwdPhoneEdt.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    findPwdPhoneEdt.requestFocus();
                    return;
                }
                intent = new Intent(getApplicationContext(), FindPwdActivity.class);

                startActivity(intent);
            }
        });



        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpecId = tabHost.newTabSpec("ID").setIndicator("아이디 찾기");
        tabSpecId.setContent(R.id.findId);
        tabHost.addTab(tabSpecId);
        TabHost.TabSpec tabSpecPwd = tabHost.newTabSpec("PWD").setIndicator("비밀번호 찾기");
        tabSpecPwd.setContent(R.id.findPwd);
        tabHost.addTab(tabSpecPwd);


    }
}
