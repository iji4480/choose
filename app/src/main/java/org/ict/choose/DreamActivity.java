package org.ict.choose;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class DreamActivity extends AppCompatActivity {

    TextView dreamContent, dreamLikeText, dreamHateText;
    ImageView dreamLikeCount, dreamHateCount;
    private String userUid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    int i;
    int j;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dream);

        dreamLikeCount = (ImageView)findViewById(R.id.dreamLikeCount);
        dreamHateCount = (ImageView)findViewById(R.id.dreamHateCount);
        dreamContent = (TextView)findViewById(R.id.dreamContent);
        dreamLikeText = (TextView)findViewById(R.id.dreamLikeText);
        dreamHateText = (TextView)findViewById(R.id.dreamHateText);

        userUid = user.getUid();
        db.collection("Dream").document(userUid).collection("write")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                dreamContent.setText(document.getString("contents"));
                            }
                        } else {
                        }
                    }
                });

        dreamLikeCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LikeHateDto like = new LikeHateDto(++i, userUid);
                db.collection("Dream").document(userUid)
                        .collection("Count").document("like").set(like);
                DocumentReference doRef = db.collection("Dream").document(userUid).collection("Count").document("like");
                doRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            dreamLikeText.setText(document.getDouble("like").toString());
                        } else {
                            return;
                        }
                    }
                });//contents ?????? ?????? ????????? ??????
            }
        });//


        dreamHateCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HateDTO hate = new HateDTO(++j, userUid);
                db.collection("Dream").document(userUid)
                        .collection("Count").document("hate").set(hate);
                DocumentReference doRef2 = db.collection("Dream").document(userUid).collection("Count").document("hate");
                doRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            dreamHateText.setText(document.getDouble("hate").toString());
                        } else {
                            return;
                        }
                    }
                });//contents ?????? ?????? ????????? ??????
            }
        });

    }
}
