package org.ict.choose;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Member;

public class MemberInfoActivity extends AppCompatActivity {

    EditText nickNameEdt;
    Button finishBtn;
    private static final String collectionUser = "user";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memberinfo);

        nickNameEdt = (EditText)findViewById(R.id.nickNameEdt);
        finishBtn = (Button)findViewById(R.id.finishBtn);

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nickNameEdt.getText().toString().length() < 2){
                    Toast.makeText(getApplicationContext(), "닉네임은 2글자 이상이어야 합니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (nickNameEdt.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "닉네임을 입력해주세요.", Toast.LENGTH_LONG).show();
                    return;
                }

                nick();

            }
        });
    }//onCreate

    private void nick() {
        String nickName = ((EditText)findViewById(R.id.nickNameEdt)).getText().toString();

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            MemberDto memberDto = new MemberDto(nickName,user.getUid());
            if (user != null){
                db.collection(collectionUser).document(user.getUid()).set(memberDto)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "사용 불가능한 닉네임 입니다.", Toast.LENGTH_SHORT).show();                            }
                        });
            }
    }
}
