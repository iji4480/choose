package org.ict.choose;

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

public class TripActivity extends AppCompatActivity {

    TextView tripContent, tripLikeText, tripHateText;
    ImageView tripLikeImage, tripHateImage;

    private String userUid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip);

        tripContent = (TextView)findViewById(R.id.tripContent);
        tripLikeText = (TextView)findViewById(R.id.tripLikeText);
        tripHateText = (TextView)findViewById(R.id.tripHateText);
        tripLikeImage = (ImageView)findViewById(R.id.tripLikeImage);
        tripHateImage = (ImageView)findViewById(R.id.tripHateImage);

        userUid = user.getUid();
        DocumentReference docRef = db.collection("Trip").document(userUid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    tripContent.setText(document.getString("contents"));
                } else {
                }
            }
        });//contents

        tripLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LikeHateDto like = new LikeHateDto(+1, userUid);
                db.collection("Trip").document(userUid).collection("Count").document("like").set(like);
                DocumentReference doRef = db.collection("Trip").document(userUid).collection("Count").document("like");
                doRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            tripLikeText.setText(document.getDouble("like").toString());
                        } else {
                            return;
                        }
                    }
                });//contents 투표 수를 화면에 표출
            }
        });//likeImage

        tripHateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HateDTO hate = new HateDTO(+1, userUid);
                db.collection("Trip").document(userUid).collection("Count").document("hate").set(hate);
                DocumentReference doRef = db.collection("Trip").document(userUid).collection("Count").document("hate");
                doRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();

                            tripHateText.setText(document.getDouble("hate").toString());
                        } else {
                            return;
                        }
                    }
                });//contents 투표 수를 화면에 표출
            }
        });

    }//onCreate
}
