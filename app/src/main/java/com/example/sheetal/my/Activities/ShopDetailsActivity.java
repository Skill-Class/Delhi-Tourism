package com.example.sheetal.my.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sheetal.my.Adapters.RecyclerViewAdapter;
import com.example.sheetal.my.Adapters.RecyclerViewAdapterForParanoma;
import com.example.sheetal.my.Adapters.RecyclerViewAdapterForShops;
import com.example.sheetal.my.Adapters.RecyclerViewAdapterHomScreen;
import com.example.sheetal.my.R;

import java.util.ArrayList;

public class ShopDetailsActivity extends AppCompatActivity {

    ImageView backButton;
    FloatingActionButton addshopDetailsButton;

    private ArrayList<String> shopNames = new ArrayList<>();
    private ArrayList<Integer> shopDescription = new ArrayList<>();
    private ArrayList<String> shopTiming = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);

        backButton = findViewById(R.id.imageView9);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopDetailsActivity.this, MainHomeScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });

        addshopDetailsButton = findViewById(R.id.floatingActionButton);
        addshopDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShopDetailsActivity.this,"Details added.",Toast.LENGTH_SHORT).show();
            }
        });

        getDetails();

    }

    private void getDetails()
    {
        shopNames.add("Jama Masjid");
        shopNames.add("Chandani Chauk");
        shopNames.add("Indira Gandhi Memorial");
        shopNames.add("India Gate");
        shopNames.add("Old Delhi");
        shopNames.add("Red Fort");
        shopNames.add("Qutub Minar");
        shopNames.add("Rashtrapati Bhawan");
        shopNames.add("Rashtrapati Bhawan");
        shopNames.add("Rashtrapati Bhawan");


        shopDescription.add(R.string.dummytextshort);
        shopDescription.add(R.string.chandanichauk);
        shopDescription.add(R.string.indiragandhi);
        shopDescription.add(R.string.indiagate);
        shopDescription.add(R.string.olddelhi);
        shopDescription.add(R.string.redfort);
        shopDescription.add(R.string.qutubminar);
        shopDescription.add(R.string.rashtrapatibhawan);
        shopDescription.add(R.string.rashtrapatibhawan);
        shopDescription.add(R.string.rashtrapatibhawan);

        shopTiming.add("11 AM to 11 PM");
        shopTiming.add("11 AM to 11 PM");
        shopTiming.add("11 AM to 11 PM");
        shopTiming.add("11 AM to 11 PM");
        shopTiming.add("11 AM to 11 PM");
        shopTiming.add("11 AM to 11 PM");
        shopTiming.add("11 AM to 11 PM");
        shopTiming.add("11 AM to 11 PM");
        shopTiming.add("11 AM to 11 PM");
        shopTiming.add("11 AM to 11 PM");


        initRecyclerView();

    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewForShops);

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        recyclerView.setLayoutAnimation(animation);


        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapterForShops adapter = new RecyclerViewAdapterForShops(this, shopNames, shopDescription,shopTiming);
        recyclerView.setAdapter(adapter);
    }


}
