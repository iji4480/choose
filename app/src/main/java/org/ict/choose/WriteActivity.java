package org.ict.choose;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class WriteActivity extends AppCompatActivity {

    Spinner title;
    EditText content;
    Button uploadBtn;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write);

        title = (Spinner)findViewById(R.id.title);
        content = (EditText)findViewById(R.id.content);
        uploadBtn = (Button)findViewById(R.id.uploadBtn);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String love = "연애";





            }


        });
    }
//    private void nick() {
//        String nickName = ((EditText)findViewById(R.id.nickNameEdt)).getText().toString();
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        MemberDto memberDto = new MemberDto(nickName);
//            db.collection("user").document("회원 닉네임").set(memberDto)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                            startActivity(intent);
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(getApplicationContext(), "사용 불가능한 닉네임 입니다.", Toast.LENGTH_SHORT).show();                            }
//                    });
//        }
//
    private void write() {
        String content = ((EditText) findViewById(R.id.content)).getText().toString();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        WriteDto writeDto = new WriteDto(content, user.getUid());
            db.collection("write").document("작성글").set(writeDto)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "게시글이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                            intent = new Intent(getApplicationContext(), MainPageActivity.class);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "사용 불가능한 닉네임 입니다.", Toast.LENGTH_SHORT).show();
                        }
                    });


        }
    }
