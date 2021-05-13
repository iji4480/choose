package org.ict.choose;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class LoveActivity extends AppCompatActivity {


    TextView loveContent;
    ImageView likeImg, hateImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.love);

        loveContent = (TextView)findViewById(R.id.loveContent);
        likeImg = (ImageView)findViewById(R.id.likeImg);
        hateImg = (ImageView)findViewById(R.id.hateImg);


    }




}
