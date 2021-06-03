package org.ict.choose;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class HelthActivity extends AppCompatActivity {

    TextView helthContent, helthLikeText, helthHateText;
    ImageView helthLikeImage, helthHateImage;

    int i;
    int j;
    private String userUid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helth);

        helthContent = (TextView)findViewById(R.id.helthContent);
        helthLikeImage = (ImageView)findViewById(R.id.helthLikeImage);
        helthHateImage = (ImageView)findViewById(R.id.helthHateImage);
        helthLikeText = (TextView)findViewById(R.id.helthLikeText);
        helthHateText = (TextView)findViewById(R.id.helthHateText);

        userUid = user.getUid();
        db.collection("Helth").document(userUid).collection("write")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                helthContent.setText(document.getString("contents"));
                            }
                        } else {
                        }
                    }
                });

        // like 이미지 클릭시 카운트
        helthLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LikeHateDto like = new LikeHateDto( ++i, userUid);
                db.collection("Helth").document(userUid).collection("Count").document("like").set(like);
                DocumentReference doRef = db.collection("Helth").document(userUid).collection("Count").document("like");
                doRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            helthLikeText.setText(document.getDouble("like").toString());
                        }else {
                            Toast.makeText(getApplicationContext(), "글이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });//contents 투표 수를 화면에 표출
            }
        });//likeImage


        // hate 이미지 클릭시 카운트
        helthHateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HateDTO hate = new HateDTO(++j, userUid);
                db.collection("Helth").document(userUid).collection("Count").document("hate").set(hate);
                DocumentReference doRef2 = db.collection("Helth").document(userUid).collection("Count").document("hate");
                doRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            helthHateText.setText(document.getDouble("hate").toString());
                        }else {
                            Toast.makeText(getApplicationContext(), "글이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });//contents 투표 수를 화면에 표출
            }
        });//HateImage


    }//onCreate
}
