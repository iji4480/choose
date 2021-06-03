package org.ict.choose;

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
import com.google.firestore.v1.Write;

import org.w3c.dom.Text;

public class FashionActivity extends AppCompatActivity {

    TextView fashionContent, fashionLikeText, fashionHateText;
    ImageView fashionLikeImage, fashionHateImage;
    private String userUid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    int i ;
    int j ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fashion);

        fashionContent = (TextView)findViewById(R.id.fashionContent);
        fashionLikeImage = (ImageView)findViewById(R.id.fashionLikeImage);
        fashionHateImage = (ImageView)findViewById(R.id.fashionHateImage);
        fashionLikeText = (TextView)findViewById(R.id.fashionLikeText);
        fashionHateText = (TextView)findViewById(R.id.fashionHateText);


        userUid = user.getUid();
        db.collection("Fashion").document(userUid).collection("write")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                fashionContent.setText(document.getString("contents"));
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "글이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });//contents

        fashionLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LikeHateDto like = new LikeHateDto(++i, userUid);
                db.collection("Fashion").document(userUid)
                        .collection("Count").document("like").set(like);
                DocumentReference doRef = db.collection("Fashion").document(userUid).collection("Count").document("like");
                doRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            fashionLikeText.setText(document.getDouble("like").toString());
                        } else {

                        }
                    }
                });//contents 투표 수를 화면에 표출
            }
        });//likeImage

        fashionHateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HateDTO hate = new HateDTO(++j, userUid);
                db.collection("Fashion").document(userUid).collection("Count").document("hate").set(hate);
                DocumentReference doRef2 = db.collection("Fashion").document(userUid).collection("Count").document("hate");
                doRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            fashionHateText.setText(document.getDouble("hate").toString());
                        } else {

                        }
                    }
                });//contents 투표 수를 화면에 표출
            }
        });//hateImage

    }//onCreate
}




















