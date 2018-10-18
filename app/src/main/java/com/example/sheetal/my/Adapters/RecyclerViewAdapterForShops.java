package com.example.sheetal.my.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sheetal.my.R;

import java.util.ArrayList;

public class RecyclerViewAdapterForShops extends RecyclerView.Adapter<RecyclerViewAdapterForShops.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";


    //vars
    private Context context;
    private ArrayList<String> shopNameArrayList = new ArrayList<>();
    private ArrayList<Integer> shopDescriptionArrayList = new ArrayList<>();
    private ArrayList<String> shopTimingArrayList = new ArrayList<>();

    public RecyclerViewAdapterForShops(Context context, ArrayList<String> shopName, ArrayList<Integer> shopDescription, ArrayList<String> shopTiming) {
        this.context = context;
        this.shopNameArrayList = shopName;
        this.shopDescriptionArrayList = shopDescription;
        this.shopTimingArrayList = shopTiming;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_details_custom_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");


        holder.shopName.setText(shopNameArrayList.get(position));
        holder.shopDescription.setText(shopDescriptionArrayList.get(position));
        holder.shopTiming.setText(shopTimingArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return shopNameArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView shopName, shopDescription, shopTiming;

        public ViewHolder(View itemView) {
            super(itemView);

            shopName = itemView.findViewById(R.id.shopNameTextView);
            shopDescription = itemView.findViewById(R.id.shopDescriptionTextView);
            shopTiming = itemView.findViewById(R.id.shopTimingTextView);

        }
    }

}