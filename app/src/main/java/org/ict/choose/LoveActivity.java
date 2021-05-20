package org.ict.choose;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class LoveActivity<DatabaseReference> extends AppCompatActivity {

    TextView loveContent, likeCount, hateCount;
    ImageView likeImg, hateImg;
    LinearLayout contentLayout;
    private String userUid;
    private int likeCnt;
    private int hateCnt;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.love);
        userUid = user.getUid();
        likeCount = (TextView)findViewById(R.id.likeCount);
        hateCount = (TextView)findViewById(R.id.hateCount);
        loveContent = (TextView)findViewById(R.id.loveContent);
        likeImg = (ImageView)findViewById(R.id.likeImg);
        hateImg = (ImageView)findViewById(R.id.hateImg);
        contentLayout = (LinearLayout)findViewById(R.id.contentLayout);
        likeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 중복 투표 안되게 막는 로직 모름.
                LikeHateDto like = new LikeHateDto(++likeCnt, userUid);
                db.collection(user.getUid()).document("love").collection("Count").document("like").set(like);
                DocumentReference doRef = db.collection(userUid).document("love").collection("Count").document("like");
                doRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();

                            likeCount.setText(document.getDouble("like").toString());
                        } else {
                            return;
                        }


                    }
                });//contents 투표 수를 화면에 표출
            }
        });

        hateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HateDTO hate = new HateDTO(++hateCnt, userUid);

                    db.collection(user.getUid()).document("love").collection("Count").document("hate").set(hate);
                    DocumentReference doRef = db.collection(userUid).document("love").collection("Count").document("hate");
                    doRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                hateCount.setText(document.getDouble("hate").toString());
                            } else {
                                return;
                            }
                        }
                    });//contents 투표 수를 화면에 표출




            }
        });








        //contents

        DocumentReference docRef = db.collection(userUid).document("love");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    loveContent.setText(document.getString("contents"));
                } else {
                        return;
                }


            }
        });//contents

    }//onCreate

}
