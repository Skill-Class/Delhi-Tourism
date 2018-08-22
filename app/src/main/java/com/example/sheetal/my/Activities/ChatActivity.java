package com.example.sheetal.my.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.sheetal.my.Adapters.RecyclerViewAdapterForChat;
import com.example.sheetal.my.R;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private EditText mInputMessageView;
    private ImageView imageView;
    private ImageView backimg;
    private RecyclerView recyclerView;
    private ImageButton sendchatbtn;
    private ArrayList<String> mchat = new ArrayList<>();
    private ArrayList<String> mchatreceived = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        backimg = findViewById(R.id.imageView9);
        mInputMessageView = findViewById(R.id.chatText);


        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, MainHomeScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });
        sendchatbtn = findViewById(R.id.imageButton2);
        sendchatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mchat.add(mInputMessageView.getText().toString().trim());
                mInputMessageView.setText(" ");
                 //int value = 0;

                // hiding keyboard after message is send.
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapterForChat adapter = new RecyclerViewAdapterForChat(mchat, this,1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);



    }
}
