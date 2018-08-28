package com.example.sheetal.my.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sheetal.my.R;
import com.example.sheetal.my.dialogs.BottomSheetDiloagForReviews;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class RecyclerViewForReviews extends RecyclerView.Adapter<RecyclerViewForReviews.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    //vars

    private List<String> mMessageList;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    public RecyclerViewForReviews(BottomSheetDiloagForReviews context, List<String> mMessageList) {

        this.mMessageList = mMessageList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //  int VIEW_TYPE;
        //  if (viewType == 1) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_send_message, parent, false);
        return new ViewHolder(view);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");


    }
    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

