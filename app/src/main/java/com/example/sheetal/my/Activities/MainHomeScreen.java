package com.example.sheetal.my.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.sheetal.my.Adapters.RecyclerViewAdapter;
import com.example.sheetal.my.Adapters.RecyclerViewAdapterHomScreen;
import com.example.sheetal.my.Fragments.AboutDelhiFragment;
import com.example.sheetal.my.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainHomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private ViewFlipper viewFlipper;

    private ImageView imgView,ChatImageView;
    private CardView cardView;

    private TextView viewallone;
    private TextView viewalltwo,searchTextview;


    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mTime = new ArrayList<>();
    private ArrayList<Integer> placesInDelhi = new ArrayList<>();
    private ImageView searchimg;
    private ArrayList<Integer> mDesc = new ArrayList<>();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_screen);


        imgView = findViewById(R.id.imageView4);
        ChatImageView = findViewById(R.id.chatimgview);
        searchimg = findViewById(R.id.imageView);
        viewallone = findViewById(R.id.viewalltextview);
        viewalltwo = findViewById(R.id.viewalltextview2);
        searchTextview = findViewById(R.id.textViewl);

        mAuth = FirebaseAuth.getInstance();

       ChatImageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainHomeScreen.this,ChatActivity.class);
               startActivity(intent);
               overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
           }
       });

        searchTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHomeScreen.this,ParanormaListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });

        viewallone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHomeScreen.this, HomescreenActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Top Bar Value", "Showing All Places");
                // bundle.putInt("ack",1);
                // intent.putExtra("PARENT_ACTIVITY_REF",viewalltwo.toString());
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });
        viewalltwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHomeScreen.this, HomescreenActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Top Bar Value", "Restaurants");
                // bundle.putInt("ack",1);
                // intent.putExtra("PARENT_ACTIVITY_REF",viewalltwo.toString());
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });


        cardView = findViewById(R.id.cardView);
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);

        cardView.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.item_animation_fall_down));
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
        //  toolbar.setTitle(" ");

        int images[] = {R.drawable.bgseven, R.drawable.bgnine, R.drawable.bgone1};
        // viewFlipper = findViewById(R.id.view_flipper);
        searchimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHomeScreen.this, ParanormaListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });

        getImages();  // most imortant funtion call

        //  for(int image :images){
        //    flipperImages(image);
        // }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.START);
            }
        });

        //     ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //           this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //  drawer.addDrawerListener(toggle);
        // toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void getImages() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mNames.add("Jama Masjid");
        mNames.add("Chandani Chauk");
        mNames.add("Indira Gandhi Memorial");
        mNames.add("India Gate");
        mNames.add("Old Delhi");
        mNames.add("Red Fort");
        mNames.add("Qutub Minar");
        mNames.add("Rashtrapati Bhawan");
        mNames.add("Rashtrapati Bhawan");
        //Adding data into arraylist to pass
        mDesc.add(R.string.jamamasjid);
        mDesc.add(R.string.chandanichauk);
        mDesc.add(R.string.indiragandhi);
        mDesc.add(R.string.indiagate);
        mDesc.add(R.string.olddelhi);
        mDesc.add(R.string.redfort);
        mDesc.add(R.string.qutubminar);
        mDesc.add(R.string.rashtrapatibhawan);

        placesInDelhi.add(R.drawable.bgtwo);
        placesInDelhi.add(R.drawable.bgthree);
        placesInDelhi.add(R.drawable.bgfour);
        placesInDelhi.add(R.drawable.bgfive);
        placesInDelhi.add(R.drawable.bgsix);
        placesInDelhi.add(R.drawable.bgseven);
        placesInDelhi.add(R.drawable.bgeight);
        placesInDelhi.add(R.drawable.bgnine);

        mDesc.add(R.string.jamamasjid);
        mDesc.add(R.string.chandanichauk);
        mDesc.add(R.string.indiragandhi);
        mDesc.add(R.string.indiagate);
        mDesc.add(R.string.olddelhi);
        mDesc.add(R.string.redfort);
        mDesc.add(R.string.qutubminar);
        mDesc.add(R.string.rashtrapatibhawan);

        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        //   mNames.add("Zop mwLA");
        mTime.add("30 Videos");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        // mNames.add("Trondheim");
        mTime.add("01.27");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        // mNames.add("Portugal");
        mTime.add("02.27");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        // mNames.add("Rocky Mountain National Park");
        mTime.add("02.07");

        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        //mNames.add("Mahahual");
        mTime.add("10.27");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        // mNames.add("Frozen Lake");
        mTime.add("03.17");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        // mNames.add("White Sands Desert");
        mTime.add("23.14");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        //mNames.add("Austrailia");
        mTime.add("02.27");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        // mNames.add("Washington");
        mTime.add("12.22");

        initRecyclerView();

    }

    private void flipperImages(int images) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);


        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }


    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);


        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        recyclerView.setLayoutAnimation(animation);
        recyclerView1.setLayoutAnimation(animation);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapterHomScreen adapter = new RecyclerViewAdapterHomScreen(this, mNames, placesInDelhi, mTime);
        recyclerView.setAdapter(adapter);
        recyclerView1.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(new MainHomeScreen.RecyclerTouchListener(this,
                recyclerView, new MainHomeScreen.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Toast.makeText(MainHomeScreen.this, "Showing Position : " + position,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainHomeScreen.this, DescriptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("PlacePosition", mNames.get(position));
                bundle.putInt("PlaceDesc", mDesc.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                //finish();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(MainHomeScreen.this, "Showing Position  (Long Press) : " + position,
                        Toast.LENGTH_SHORT).show();
            }
        }));


    }


    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private MainHomeScreen.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView,
                                     final MainHomeScreen.ClickListener clicklistener) {

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


    //recycler ends here

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
          Toast.makeText(MainHomeScreen.this,"Hello world;",Toast.LENGTH_LONG).show();
        //  Dialog log  = new Dialog(this);
          //log.setContentView(R.layout.menudialog);
        //  log.show();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {
            mAuth.signOut();
            Intent intent = new Intent (MainHomeScreen.this,LoginActivity.class);
            startActivity(intent);
            return true;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


