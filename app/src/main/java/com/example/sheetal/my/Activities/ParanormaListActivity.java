package com.example.sheetal.my.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sheetal.my.Adapters.RecyclerViewAdapterForParanoma;
import com.example.sheetal.my.R;


import java.util.ArrayList;


public class ParanormaListActivity extends AppCompatActivity {

    //ArrayLists
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<Integer> placesInDelhi = new ArrayList<>();

    //ImageView
    ImageView backtbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paranorma_list);

        backtbtn = findViewById(R.id.backbtn);
        backtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParanormaListActivity.this, MainHomeScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);

            }
        });
        getImages();

    }

    private void getImages() {
        //  Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        //   mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        //  mImageUrls.add(String.valueOf(R.drawable.bgimgone));
        placesInDelhi.add(R.drawable.bgtwo);
        placesInDelhi.add(R.drawable.bgthree);
        placesInDelhi.add(R.drawable.bgfour);
        placesInDelhi.add(R.drawable.bgfive);
        placesInDelhi.add(R.drawable.bgsix);
        placesInDelhi.add(R.drawable.bgseven);
        placesInDelhi.add(R.drawable.bgeight);
        placesInDelhi.add(R.drawable.bgnine);


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        //   mImageUrls.add("https://unsplash.com/photos/OqtafYT5kTw");
        mNames.add("Jama Masjid");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Chandani Chauk");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Raj Ghat");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("India Gate");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Old Delhi");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Red Fort");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("Qutub Minar");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Rashtrapati Bhawan");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Rashtrapati Bhawan");

        initRecyclerView();

    }


    private void initRecyclerView() {
        // Log.d(TAG, "initRecyclerView: init recyclerview");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        recyclerView.setLayoutAnimation(animation);


        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapterForParanoma adapter = new RecyclerViewAdapterForParanoma(this, placesInDelhi, mNames);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new ParanormaListActivity.RecyclerTouchListener(this,
                recyclerView, new HomescreenActivity.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Toast.makeText(ParanormaListActivity.this, "Showing Position : " + position,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ParanormaListActivity.this,PanoramaViewActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                //overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                //  finish();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(ParanormaListActivity.this, "Showing Position  (Long Press) : " + position,
                        Toast.LENGTH_SHORT).show();
            }
        }));

    }

    // RECYCLER VIEW ANIMATION STARTS
    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        int resId = R.anim.layout_animation;
        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(recyclerView.getContext(), resId);
        recyclerView.setLayoutAnimation(animationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }


    //RECYCLER VIEW ANIMATION ENDS


    //RECYCLER VIEW ONCLICK METHOND STARTS
    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private HomescreenActivity.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView,
                                     final HomescreenActivity.ClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}