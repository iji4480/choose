package org.ict.choose;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainPageActivity extends AppCompatActivity {

    Button writeBtn, loveBtn, helthBtn, tripBtn, shopBtn, fashionBtn, dreamBtn;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        writeBtn = (Button)findViewById(R.id.writeBtn);
        loveBtn = (Button)findViewById(R.id.loveBtn);
        helthBtn = (Button)findViewById(R.id.helthBtn);
        tripBtn = (Button)findViewById(R.id.tripBtn);
        shopBtn = (Button)findViewById(R.id.shopBtn);
        fashionBtn = (Button)findViewById(R.id.fashionBtn);
        dreamBtn = (Button)findViewById(R.id.dreamBtn);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), WriteActivity.class);

                startActivity(intent);

            }
        });
        loveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), LoveActivity.class);

                startActivity(intent);
            }
        });
        helthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), HelthActivity.class);

                startActivity(intent);
            }
        });
        tripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), TripActivity.class);

                startActivity(intent);
            }
        });
        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ShoppingActivity.class);

                startActivity(intent);
            }
        });
        fashionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), FashionActivity.class);
                startActivity(intent);
            }
        });
        dreamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), DreamActivity.class);

                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.mywrite, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.myWrite) {
            intent = new Intent(getApplicationContext(), MyWriteActivity.class);

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
