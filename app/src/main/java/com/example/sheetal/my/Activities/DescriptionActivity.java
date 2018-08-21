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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sheetal.my.Adapters.RecyclerViewAdapterDesc;
import com.example.sheetal.my.Adapters.RecyclerViewAdapterForNearby;
import com.example.sheetal.my.R;
import com.example.sheetal.my.dialogs.BottomSheetDialog;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

public class DescriptionActivity extends AppCompatActivity {

    /*
     * Sheetal kumar | Delhi Yatri |  Software engineering project | Date -  18 aug 2018
     * */
    //vars
    private Context ctx;
    private ImageView backbtn;

    private ImageView imageView;
    private ImageButton mapbutton;

    private TextView placeName, placeDesc, title;

    private RecyclerView recyclerView;

    private ArrayList<Integer> mChandniChauk = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mplacesName = new ArrayList<>();
    private ArrayList<Integer> mplacesImage = new ArrayList<>();
    private String message = " ";
    Integer message1 = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);


        mapbutton = findViewById(R.id.imageButton);
        title = findViewById(R.id.textView8);
        backbtn = findViewById(R.id.imageView9);
        //imageView = findViewById(R.id.imageView6);
        placeName = findViewById(R.id.textView3);
        placeDesc = findViewById(R.id.textView7);
        // sample code snippet to set the text content on the ExpandableTextView
      // ExpandableTextView expTv1 = findViewById(R.id.expand_text_view);
      // expTv1.setText(getString(R.string.dummytextshort));

// IMPORTANT - call setText on the ExpandableTextView to set the text content to display
        //expTv1.setText(getString(R.string.dummytextshort));


        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DescriptionActivity.this,MapsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DescriptionActivity.this, MainHomeScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });

        title.setText("Delhi Yatri");

      //  String activityName = getIntent().getStringExtra("PARENT_ACTIVITY_REF");
   /*     if (activityName.equals("parent")){
            Bundle bundle = getIntent().getExtras();
            String message = bundle.getString("PlacePosition");
            placeName.setText(message);
        }else {
*/            //getting data from homescreen activity
            Bundle bundle = getIntent().getExtras();
            String message = bundle.getString("PlacePosition");
            Integer message1 = bundle.getInt("PlaceDesc");

                placeName.setText(message);
                placeDesc.setText(message1);

  //      }
        // getting images data
        getImages();
    }


    private void getImages() {

        mChandniChauk.add(R.drawable.bgone1);
        mChandniChauk.add(R.drawable.bgone2);
        mChandniChauk.add(R.drawable.bgone3);
        mChandniChauk.add(R.drawable.bgone4);
        mChandniChauk.add(R.drawable.bgone5);
        mChandniChauk.add(R.drawable.bgone6);
        mChandniChauk.add(R.drawable.bgone7);
        mChandniChauk.add(R.drawable.bgone8);
        mChandniChauk.add(R.drawable.bgone9);


        mplacesImage.add(R.drawable.ic_location_city_black_24dp);
        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Chandani Chauk");
        mplacesName.add("Hotel");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mplacesImage.add(R.drawable.ic_local_dining_black_24dp);
        mNames.add("Jama Masjid");
        mplacesName.add("Food Places");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mplacesName.add("ATM");
        mplacesImage.add(R.drawable.ic_local_atm_black_24dp);
        mNames.add("Kamla Nagar");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Raj Ghat");
        mplacesName.add("Shopping");
        mplacesImage.add(R.drawable.ic_shopping_cart_black_24dp);

        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("India Gate");
        mplacesName.add("Food");
        mplacesImage.add(R.drawable.ic_local_cafe_black_24dp);

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Old Delhi");
        mplacesName.add("Hospital");
        mplacesImage.add(R.drawable.ic_local_hospital_black_24dp);


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("Red Fort");
        mplacesName.add("Parking");
        mplacesImage.add(R.drawable.ic_local_parking_black_24dp);

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Qutub Minar");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Rashtrapati Bhawan");

        // calling funtion

        initRecyclerView();

    }


    private void initRecyclerView() {
        // Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        RecyclerView recyclerView1 = findViewById(R.id.recyclerView3);

        //this is for animation in recycler view.
        int resId = R.anim.layout_animation_fall_down;

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);

        recyclerView.setLayoutAnimation(animation);
        recyclerView1.setLayoutAnimation(animation);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapterDesc adapter = new RecyclerViewAdapterDesc(this, mChandniChauk);
        RecyclerViewAdapterForNearby adapter1 = new RecyclerViewAdapterForNearby(mplacesName, mplacesImage, this);

        recyclerView.setAdapter(adapter);
        recyclerView1.setAdapter(adapter1);

        recyclerView1.addOnItemTouchListener(new DescriptionActivity.RecyclerTouchListener(this,
                recyclerView, new HomescreenActivity.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                // Toast.makeText(DescriptionActivity.this, "Showing Position : " + position,
                //       Toast.LENGTH_SHORT).show();

                // sending data to another activity
                Bundle bundle = new Bundle();

                bundle.putString("PlacePosition", mplacesName.get(position));
                bundle.putInt("PlaceImage", mplacesImage.get(position));
                // bundle.putInt("PlaceDesc",mplacesImage.get(position));

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
                bottomSheetDialog.setArguments(bundle);

                bottomSheetDialog.show(getSupportFragmentManager(), "examplebottmsheetal");
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(DescriptionActivity.this, "Showing Position  (Long Press) : " + position,
                        Toast.LENGTH_SHORT).show();
                //     Intent intent = new Intent(DescriptionActivity.this, HomescreenActivity.class);
                //   startActivity(intent);
                // overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        }));


        recyclerView.addOnItemTouchListener(new DescriptionActivity.RecyclerTouchListener(this,
                recyclerView, new HomescreenActivity.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Toast.makeText(DescriptionActivity.this, "Showing Position : " + position,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(DescriptionActivity.this, "Showing Position  (Long Press) : " + position,
                        Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(DescriptionActivity.this, HomescreenActivity.class);
                //startActivity(intent);
               // overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        }));

    }

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

