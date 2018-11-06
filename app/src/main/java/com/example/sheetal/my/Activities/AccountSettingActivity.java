package com.example.sheetal.my.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sheetal.my.R;

public class AccountSettingActivity extends AppCompatActivity {

    private ImageView backImageView;
    private TextView userEmailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);


        backImageView = findViewById(R.id.back_imageview);
        userEmailTextView = findViewById(R.id.accoutuseremailtext);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSettingActivity.this,MainHomeScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("userEmail");
        if(message.equals(null)){
            userEmailTextView.setText("TEST LOGIN");
        }else{
            userEmailTextView.setText(message);
        }


    }
}
