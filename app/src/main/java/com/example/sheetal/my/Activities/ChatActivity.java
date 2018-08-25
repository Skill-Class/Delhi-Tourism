package com.example.sheetal.my.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sheetal.my.Adapters.RecyclerViewAdapterForChat;
import com.example.sheetal.my.Model.ChatData;

import com.example.sheetal.my.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private EditText mInputMessageView;
    private ImageView imageView;
    private ImageView backimg;
    private RecyclerView recyclerView;
    private ImageButton sendchatbtn;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String chatuserid;
    private ImageView sendname;

    private EditText userName;

    private DatabaseReference mRootRef;
    private FirebaseDatabase firebaseDatabase;
    private ArrayList<String> mchat = new ArrayList<>();
    // private List<ChatData> chatDataList;
    private String mCurrentUserId;
    private List<ChatData> mMessagesList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapterForChat mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);



        mRootRef = FirebaseDatabase.getInstance().getReference();
        backimg = findViewById(R.id.imageView9);
        mInputMessageView = findViewById(R.id.chatText);
        userName = findViewById(R.id.textView8);

      //  Dialog dialog = new Dialog(this);
       // dialog.setContentView(R.layout.menudialog);
       // dialog.show();
      //  final String username = userName.getText().toString();
      //  sendname = findViewById(R.id.name_edittext_btn);

        //Bundle bundle = getIntent().getExtras();
       // final String currentusername = bundle.getString("UserName");
        //textView.setText(message);
       //userName.setText(currentusername);

// adding dialog


       // View view  = LayoutInflater.from(getApplication(),R.layout.dialogforusername,null);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
      //  alertDialog.setContentView(R.layout.menudialog);
        alertDialog.setTitle("Delhi Yatri");
        alertDialog.setIcon(R.drawable.icon);
        alertDialog.setCancelable(true);
        alertDialog.setMessage("Hello, Welcome in the chatting section of Delhi Yatri. We request you to be humble to everyone. Please do not use any abusive language here.For better experience, we suggest you to enter your name before posting anything . Press agree  to accept our terms and conditions. thank you ! ");
        alertDialog.setButton(alertDialog.BUTTON_POSITIVE, "Agree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.cancel();
            }
        });
        alertDialog.setButton(alertDialog.BUTTON_NEGATIVE, "Not Agree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ChatActivity.this,"Sorry. we can not allow you to post anything until you do not accept our terms and conditions.!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ChatActivity.this,MainHomeScreen.class);
                startActivity(intent);
                finish();
            }
        });

        alertDialog.show();

        // dialog ends here

        mAdapter = new RecyclerViewAdapterForChat(mMessagesList);
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadmessages();


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mCurrentUserId = mAuth.getCurrentUser().getUid();
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
                // String chatMessage = mInputMessageView.getText().toString();
                // mchat.add(chatMessage);
                sendMessage();
                mInputMessageView.setText(" ");
                // hiding keyboard after message is send.
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


            }
        });

        // working code before firebase database

     //   LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    //    LinearLayout layoutManager = new LinearLayout(this);
     //   RecyclerView recyclerView = findViewById(R.id.recyclerView);
      //  RecyclerViewAdapterForChat adapter = new RecyclerViewAdapterForChat(mchat, this, chatuserid);
      //  recyclerView.setAdapter(adapter);
       // recyclerView.setLayoutManager(layoutManager);

    }

    private void loadmessages() {

       mRootRef.child("messages").addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               mMessagesList.add(dataSnapshot.getValue(ChatData.class));
               mAdapter.notifyDataSetChanged();
               recyclerView.scrollToPosition(mMessagesList.size()-1);
           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
    }

    private void sendMessage() {
        String message = mInputMessageView.getText().toString();
        if (!TextUtils.isEmpty(message)) {

            DatabaseReference user_message_push = mRootRef.child("messages").push();
            String push_id = user_message_push.getKey();

         //   DatabaseReference current_user_emailid = mRootRef.child("Users").child(mCurrentUserId);
          //  DatabaseReference u

            ChatData chatData = new ChatData("chatMessage", "userId","userName","timestamp");

            DatabaseReference newPost = user_message_push;

            Map<String, String> DataToSave = new HashMap<>();
            DataToSave.put("chatMessage", message);
            DataToSave.put("userId", mUser.getUid());
            DataToSave.put("userName", userName.getText().toString());
            DataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));

            user_message_push.setValue(DataToSave);
            Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();

        }
    }


}
