package com.example.sheetal.my.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sheetal.my.R;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    // vars
    private TextView topPlace;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialogone, container, false);

        // Getting current clicked images and places name from description activity.!

        Bundle bundle = getArguments();
        String message = bundle.getString("PlacePosition");
        Integer image = bundle.getInt("PlaceImage");

        TextView topPlace = view.findViewById(R.id.textView16);
        imageView = view.findViewById(R.id.imageView10);
        imageView.setImageResource(image);

        topPlace.setText(message);
        return view;
    }
}
