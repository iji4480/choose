package org.ict.choose;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.protobuf.Value;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class LoveActivity<DatabaseReference> extends AppCompatActivity {

    TextView loveContent, loveLikeCount, loveHateCount;
    ImageView likeImg, hateImg;
    private String userUid;
    int i;
    int j;


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.love);
        userUid = user.getUid();
        loveContent = (TextView)findViewById(R.id.loveContent);
        loveLikeCount = (TextView)findViewById(R.id.loveLikeText);
        loveHateCount = (TextView)findViewById(R.id.loveHateText);
        likeImg = (ImageView)findViewById(R.id.loveLikeImage);
        hateImg = (ImageView)findViewById(R.id.loveHateImage);


        userUid = user.getUid();
        db.collection("Love").document(userUid).collection("write")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                loveContent.setText(document.getString("contents"));
                            }
                        } else {
                        }
                    }
                });//contents

        likeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LikeHateDto like = new LikeHateDto(++i, userUid);
                db.collection("Love").document(userUid)
                        .collection("Count").document("like").set(like);
                DocumentReference doRef = db.collection("Love").document(userUid).collection("Count").document("like");
                doRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            loveLikeCount.setText(document.getDouble("like").toString());
                        }
                    }
                });//contents 투표 수를 화면에 표출
            }
        });//likeImage

        hateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HateDTO hate = new HateDTO(++j, userUid);
                db.collection("Love").document(userUid).collection("Count").document("hate").set(hate);
                DocumentReference doRef2 = db.collection("Love").document(userUid).collection("Count").document("hate");
                doRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            loveHateCount.setText(document.getDouble("hate").toString());
                        }
                    }
                });//contents 투표 수를 화면에 표출
            }
        });//hateImage

    }//onCreate

}
