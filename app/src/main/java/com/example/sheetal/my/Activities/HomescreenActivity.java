package com.example.sheetal.my.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheetal.my.Adapters.RecyclerViewAdapter;
import com.example.sheetal.my.R;

import java.util.ArrayList;

public class HomescreenActivity extends AppCompatActivity {

    //vars

    private ImageView backimg;

    private ArrayList<Integer> placesInDelhi = new ArrayList<>();
    private Context ctx;
    private RecyclerView recyclerView;

    ProgressDialog progressDialog;

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<Integer> mDesc = new ArrayList<>();

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        textView = findViewById(R.id.textView8);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("Top Bar Value");
        textView.setText(message);

        backimg = findViewById(R.id.imageView9);
        progressDialog = new ProgressDialog(this);
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomescreenActivity.this, MainHomeScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });

      /*  backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(HomescreenActivity.this);
                dialog.setContentView(R.layout.menudialog);
                //listView = dialog.findViewById(R.id.listView1);
                dialog.setCancelable(true);
                dialog.show();

            }
        });
*/
        // Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.item_animation_fall_down);
        //animation.setRepeatCount(Animation.INFINITE);
        // searchimg.startAnimation(animation);

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


        //Adding data into arraylist to pass
        mDesc.add(R.string.jamamasjid);
        mDesc.add(R.string.chandanichauk);
        mDesc.add(R.string.indiragandhi);
        mDesc.add(R.string.indiagate);
        mDesc.add(R.string.olddelhi);
        mDesc.add(R.string.redfort);
        mDesc.add(R.string.qutubminar);
        mDesc.add(R.string.rashtrapatibhawan);


        mNames.add("Jama Masjid");
        mNames.add("Chandani Chauk");
        mNames.add("Indira Gandhi Memorial");
        mNames.add("India Gate");
        mNames.add("Old Delhi");
        mNames.add("Red Fort");
        mNames.add("Qutub Minar");
        mNames.add("Rashtrapati Bhawan");
        mNames.add("Rashtrapati Bhawan");
        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        //   mImageUrls.add("https://unsplash.com/photos/OqtafYT5kTw");


        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");


        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");


        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");


        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");


        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");


        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");


        initRecyclerView();

    }


    private void initRecyclerView() {
        // Log.d(TAG, "initRecyclerView: init recyclerview");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, placesInDelhi);
        recyclerView.setAdapter(adapter);
        // recyclerView1.setAdapter(adapter);
//        runLayoutAnimation(recyclerView);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //  Toast.makeText(HomescreenActivity.this, "Showing Position : " + position,
                //        Toast.LENGTH_SHORT).show();
                // progressDialog.setTitle("Delhi Yatri");
                progressDialog.setMessage(" Please wait..");
                progressDialog.show();
                Intent intent = new Intent(HomescreenActivity.this, DescriptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("PlacePosition", mNames.get(position));
                bundle.putInt("PlaceDesc", mDesc.get(position));
                intent.putExtras(bundle);
                //intent.putExtra("PlacePosition",position);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                //  finish();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(HomescreenActivity.this, "Showing Position  (Long Press) : " + position,
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


//RECYCLER VIEW ONITEM TOUCH ENDS
}
