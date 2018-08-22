package com.example.sheetal.my.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheetal.my.R;

import java.util.ArrayList;

public class RecyclerViewAdapterForChat extends RecyclerView.Adapter<RecyclerViewAdapterForChat.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    //vars
    private ArrayList<String> mChat = new ArrayList<>();
    private Context mContext;
    private int value;
    private View view;

    public RecyclerViewAdapterForChat(ArrayList<String> mChat, Context mContext, int value) {
        this.mChat = mChat;
        this.value = value;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mChat.size() == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_send_message, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_received_message, parent, false);
            return new ViewHolder(view);
        }

        //  return new ViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.chattext.setText(mChat.get(position));

    }


    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // ImageView image;
        ImageView left_image, right_image;
        TextView chattext;
        //   TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            //left_image = itemView.findViewById(R.id.image_right);
            chattext = itemView.findViewById(R.id.textView19);
            //     right_image = itemView.findViewById(R.id.image_right);
            //   time = itemView.findViewById(R.id.textView3);
        }
    }
}

