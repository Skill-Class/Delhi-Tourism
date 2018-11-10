package com.example.sheetal.my.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sheetal.my.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    ImageView searchimg;
    TextView textView;
    ProgressDialog progressDialog;
    FirebaseUser mUser;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);
        searchimg = findViewById(R.id.imageView);
        //

        mAuth = FirebaseAuth.getInstance();

        //getActionBar().setDisplayHomeAsUpEnabled(true);

        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadetext);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.movesearch);
        searchimg.startAnimation(animation);
        textView.startAnimation(animation1);


    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            senttomain();
            finish();
        } else {
            final Intent intent = new Intent(this, IntroActivity.class);
            Thread timer = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {

                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                    }
                }
            };
            timer.start();

        }

    }

    private void senttomain() {
        Intent intent = new Intent(MainActivity.this, MainHomeScreen.class);
        startActivity(intent);
        finish();
    }
}
