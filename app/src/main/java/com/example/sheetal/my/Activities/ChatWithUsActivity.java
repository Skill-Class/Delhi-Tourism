package com.example.sheetal.my.Activities;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sheetal.my.R;

import im.crisp.sdk.Crisp;

public class ChatWithUsActivity extends AppCompatActivity {


    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_us);


       // backBtn = findViewById(R.id.imageView9);
       /* backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatWithUsActivity.this,MainHomeScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });*/
        Crisp.User.setEmail("sheetalneo@gmail.com");
        Crisp.User.setNickname("John Doe");


    }



}
