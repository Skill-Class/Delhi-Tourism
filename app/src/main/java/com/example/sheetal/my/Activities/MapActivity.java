package com.example.sheetal.my.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sheetal.my.R;
import com.skyfishjy.library.RippleBackground;

public class MapActivity extends AppCompatActivity {

    private ImageView imgripple,backimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        imgripple = findViewById(R.id.centerImage);
        backimg = findViewById(R.id.back_imageview);
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this,MainHomeScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });
        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.contentback);
        rippleBackground.startRippleAnimation();
        //
        // ImageView imageView=(ImageView)findViewById(R.id.centerImage);
        imgripple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rippleBackground.stopRippleAnimation();
            }
        });

    }
}
