package org.ict.choose;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

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
                intent = new Intent(getApplicationContext(), FindIdActivity.class);
                startActivity(intent);
            }
        });

        findPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
