package org.ict.choose;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WriteActivity extends AppCompatActivity {

    Spinner title;
    EditText content;
    Button uploadBtn;
    Intent intent;



    ArrayList arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write);


        content = (EditText)findViewById(R.id.content);
        uploadBtn = (Button)findViewById(R.id.uploadBtn);
        title = (Spinner)findViewById(R.id.title);
        arrayList = new ArrayList();
        arrayList.add("--주제를 선택해주세요.--");
        arrayList.add("연애");
        arrayList.add("운동");
        arrayList.add("쇼핑");
        arrayList.add("여행");
        arrayList.add("패션");
        arrayList.add("진로");

        final String[] select_item = {""};
        ArrayAdapter<String> adapter;

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);

        title.setAdapter(adapter);

        title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select_item[0] = String.valueOf(arrayList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//selectedListener

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                write();

                if (select_item[0].equals("연애")){
                    intent = new Intent(WriteActivity.this, LoveActivity.class);
                    intent.putExtra("content", "");
                    startActivity(intent);

                }if (select_item[0].equals("운동")){
                    intent = new Intent(WriteActivity.this, HelthActivity.class);
                    startActivity(intent);

                }if (select_item[0].equals("쇼핑")){
                    intent = new Intent(WriteActivity.this, ShoppingActivity.class);
                    startActivity(intent);
                }if (select_item[0].equals("여행")){
                    intent = new Intent(WriteActivity.this, TripActivity.class);
                    startActivity(intent);
                }if (select_item[0].equals("패션")){
                    intent = new Intent(WriteActivity.this, FashionActivity.class);
                    startActivity(intent);
                }if (select_item[0].equals("진로")){
                    intent = new Intent(WriteActivity.this, DreamActivity.class);
                    startActivity(intent);
                }

            }


        });//uploadBtn

    }//onCreate
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

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "사용 불가능한 닉네임 입니다.", Toast.LENGTH_SHORT).show();
                    }
                });


    }//write


    }
