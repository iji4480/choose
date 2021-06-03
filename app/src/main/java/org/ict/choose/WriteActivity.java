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
import java.lang.reflect.Member;
import java.util.ArrayList;

public class WriteActivity extends AppCompatActivity {

    Spinner title;
    EditText content;
    Button uploadBtn;
    Intent intent;
    private static final String love = "love";
    private static final String helth = "helth";
    private static final String shop = "shop";
    private static final String trip = "trip";
    private static final String fashion = "fashion";
    private static final String dream = "dream";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write);


        content = (EditText)findViewById(R.id.content);
        uploadBtn = (Button)findViewById(R.id.uploadBtn);
        title = (Spinner)findViewById(R.id.title);
        arrayList = new ArrayList();
        arrayList.add("--주제를 선택하세요.--");
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

                String content = ((EditText) findViewById(R.id.content)).getText().toString();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                WriteDto writeDto = new WriteDto(content, user.getUid(), 0, 0);
                MyActivityDTO myDTO = new MyActivityDTO(content);
                String uid = user.getUid();


                if (select_item[0].equals("연애")){
                    intent = new Intent(WriteActivity.this, LoveActivity.class);
                    startActivity(intent);
                    db.collection("Love").document(uid).collection("write").document().set(writeDto);
                    db.collection("Love").document(uid).set(myDTO);
                }if (select_item[0].equals("운동")){
                    intent = new Intent(WriteActivity.this, HelthActivity.class);
                    startActivity(intent);
                    db.collection("Helth").document(uid).collection("write").document().set(writeDto);
                    db.collection("Helth").document(uid).set(myDTO);
                }if (select_item[0].equals("쇼핑")){
                    intent = new Intent(WriteActivity.this, ShoppingActivity.class);
                    startActivity(intent);
                    db.collection("Shopping").document(uid).collection("write").document().set(writeDto);
                    db.collection("Shopping").document(uid).set(myDTO);
                }if (select_item[0].equals("여행")){
                    intent = new Intent(WriteActivity.this, TripActivity.class);
                    startActivity(intent);
                    db.collection("Trip").document(uid).collection("write").document().set(writeDto);
                    db.collection("Trip").document(uid).set(myDTO);
                }if (select_item[0].equals("패션")){
                    intent = new Intent(WriteActivity.this, FashionActivity.class);
                    startActivity(intent);
                    db.collection("Fashion").document(uid).collection("write").document().set(writeDto);
                    db.collection("Fashion").document(uid).set(myDTO);
                }if (select_item[0].equals("진로")){
                    intent = new Intent(WriteActivity.this, DreamActivity.class);
                    startActivity(intent);
                    db.collection("Dream").document(uid).collection("write").document().set(writeDto);
                    db.collection("Dream").document(uid).set(myDTO);
                }else if (select_item[0].equals("--주제를 선택하세요.--")){
                    Toast.makeText(getApplicationContext(), "주제를 선택해주세요.", Toast.LENGTH_LONG).show();
                    return;
                }

                finish();

            }//onClick
        });//uploadBtn

    }//onCreate


    private long backKeyPressedTime = 0;
    @Override public void onBackPressed() { //super.onBackPressed();

        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();

        }

    }//onBackPressed




    }
