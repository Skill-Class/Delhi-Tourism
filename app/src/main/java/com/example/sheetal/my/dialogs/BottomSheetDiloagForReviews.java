package com.example.sheetal.my.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sheetal.my.Activities.DescriptionActivity;
import com.example.sheetal.my.Adapters.RecyclerViewAdapterForChat;
import com.example.sheetal.my.Adapters.RecyclerViewAdapterHomScreen;
import com.example.sheetal.my.Adapters.RecyclerViewForReviews;
import com.example.sheetal.my.R;

import java.util.ArrayList;

public class BottomSheetDiloagForReviews extends BottomSheetDialogFragment {


    // vars
    private TextView reviewText;
    private ImageView sendbtn;
    private ArrayList<String> mNames = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mNames.add("Nice Place :)");
        mNames.add("The best place for family.");
        mNames.add("nice food..");
        mNames.add("best place. <3");
        mNames.add("Must visit place :)");
        mNames.add("love my india :)");
        mNames.add("10/10");
        mNames.add("good place");
        mNames.add("food place in india. :)");

//        recyclerView.setAdapter(adapter);

        View view = inflater.inflate(R.layout.dialogtwo, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        //   LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        //  LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext().getApplicationContext());
        // recyclerView.setLayoutManager(layoutManager);

//        RecyclerViewForReviews adapter = new RecyclerViewForReviews(this, mNames);
        //      recyclerView.setAdapter(adapter);

        reviewText = view.findViewById(R.id.textView28);
        sendbtn = view.findViewById(R.id.imageView13);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewText.setText(mNames.get(1));
            }
        });
        return view;
    }
}