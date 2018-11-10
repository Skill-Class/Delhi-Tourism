package com.example.sheetal.my.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheetal.my.Animation.AnimationUtils;
import com.example.sheetal.my.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private ArrayList<String> mTime = new ArrayList<>();
    private Context mContext;
    private int previousPosition = 0;

    public RecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<Integer> imageUrls) {
        mNames = names;
        mImageUrls = imageUrls;
        // mTime = time;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        //  holder.image.setImageResource(mImageUrls.get(position));

        holder.name.setText(mNames.get(position));
        // holder.time.setText(mTime.get(position));
        // Glide.with(mContext).asBitmap().load(mImageUrls.get(position)).into(holder.image);
        holder.image.setImageResource(mImageUrls.get(position));

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image: " + mNames.get(position));
                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        /*animation starts
        if(position > previousPosition){ // We are scrolling DOWN

            AnimationUtils.animate(holder, true);

        }else{ // We are scrolling UP

            AnimationUtils.animate(holder, false);


        }

        previousPosition = position;


        final int currentPosition = position;
      // mNames.get(position);
        //mNames  = mNames.get(position);
        // final Information infoData = data.get(position);
      *///  animation ends
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        //TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.textView2);
            //  time = itemView.findViewById(R.id.textView3);
        }
    }

}