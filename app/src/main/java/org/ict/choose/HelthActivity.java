package org.ict.choose;

import android.media.Image;
import android.os.Bundle;
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

public class HelthActivity extends AppCompatActivity {

    TextView helthContent, helthLikeText, helthHateText;
    ImageView helthLikeImage, helthHateImage;


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
        DocumentReference docRef = db.collection("Helth").document(userUid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    helthContent.setText(document.getString("contents"));
                } else {

                }

            }
        });//contents

        helthLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LikeHateDto like = new LikeHateDto(+1, userUid);
                db.collection("Helth").document(userUid).collection("Count").document("like").set(like);
                DocumentReference doRef = db.collection("Helth").document(userUid).collection("Count").document("like");
                doRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            helthLikeText.setText(document.getDouble("like").toString());
                        } else {
                            return;
                        }
                    }
                });//contents 투표 수를 화면에 표출
            }
        });

        helthHateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HateDTO hate = new HateDTO(+1, userUid);
                db.collection("Helth").document(userUid).collection("Count").document("hate").set(hate);
                DocumentReference doRef = db.collection("Helth").document(userUid).collection("Count").document("hate");
                doRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            helthHateText.setText(document.getDouble("hate").toString());
                        } else {
                            return;
                        }
                    }
                });//contents 투표 수를 화면에 표출
            }
        });

    }//onCreate
}
