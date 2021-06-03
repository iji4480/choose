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

public class ShoppingActivity extends AppCompatActivity {


    TextView shopContent, shopLikeText, shopHateText;
    ImageView shopLikeImage, shopHateImage;
    int i;
    int j;
    private String userUid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping);

        shopContent = (TextView)findViewById(R.id.shopContent);
        shopLikeText = (TextView)findViewById(R.id.shopLikeText);
        shopHateText = (TextView)findViewById(R.id.shopHateText);
        shopLikeImage = (ImageView)findViewById(R.id.shopLikeImage);
        shopHateImage = (ImageView)findViewById(R.id.shopHateImage);

        userUid = user.getUid();
        db.collection("Shopping").document(userUid).collection("write")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                shopContent.setText(document.getString("contents"));

                            }
                        } else {
                        }
                    }
                });// contents

        shopLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LikeHateDto like = new LikeHateDto(++i, userUid);
                db.collection("Shopping").document(userUid).collection("Count").document("like").set(like);
                DocumentReference doRef = db.collection("Shopping").document(userUid).collection("Count").document("like");
                doRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            shopLikeText.setText(document.getDouble("like").toString());
                        } else {
                            return;
                        }
                    }
                });//contents 득표 수를 화면에 표출
            }
        });//LikeImage
        shopHateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HateDTO hate = new HateDTO(++j, userUid);
                db.collection("Shopping").document(userUid).collection("Count").document("hate").set(hate);
                DocumentReference doRef2 = db.collection("Shopping").document(userUid).collection("Count").document("hate");
                doRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            shopHateText.setText(document.getDouble("hate").toString());
                        } else {
                            return;
                        }
                    }
                });//contents 득표 수를 화면에 표출
            }
        });//hateImage
    }
}
