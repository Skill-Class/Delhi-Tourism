package com.example.sheetal.my.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
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
    Button btn;
    FloatingActionButton addshopDetailsButton;
    private Dialog ThisDialog;

    private Context context;
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
                Toast.makeText(ShopDetailsActivity.this,"Wait.",Toast.LENGTH_SHORT).show();

                ThisDialog = new Dialog(ShopDetailsActivity.this);
                ThisDialog.setTitle("Save Your Name");
                ThisDialog.setContentView(R.layout.dialogforusername);
               
               // final EditText Write = (EditText)ThisDialog.findViewById(R.id.write);
               // Button SaveMyName = (Button)ThisDialog.findViewById(R.id.SaveNow);

               // Write.setEnabled(true);
               // SaveMyName.setEnabled(true);

                /*SaveMyName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  SharedPrefesSAVE(Write.getText().toString());
                        ThisDialog.cancel();
                    }
                });*/

                ThisDialog.show();

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
