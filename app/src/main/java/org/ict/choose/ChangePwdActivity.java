package org.ict.choose;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ChangePwdActivity extends AppCompatActivity {

    private static final String TAG = "ChangePwdActivity";
    // 파이어베이스 선언
    private FirebaseAuth mAuth;

    EditText sendEmailEdt;
    Button sendBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepwd);
        sendBtn = (Button)findViewById(R.id.sendBtn);
        sendEmailEdt = (EditText)findViewById(R.id.sendEmailEdt);
        mAuth = FirebaseAuth.getInstance();


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sendEmailEdt.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "이메일을 입력하세요.", Toast.LENGTH_SHORT).show();
                    sendEmailEdt.requestFocus();
                    return;
                }

                send();

            }
        });
    }

    private void send(){

        String email = ((EditText)findViewById(R.id.sendEmailEdt)).getText().toString();
        mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "메일이 전송되었습니다.", Toast.LENGTH_SHORT).show();

                    }
                }
            });
    }
}
