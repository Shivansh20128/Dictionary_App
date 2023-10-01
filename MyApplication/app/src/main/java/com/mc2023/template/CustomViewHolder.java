package com.mc2023.template;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public TextView textList;
    public Button find_meaning_button;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        textList = itemView.findViewById(R.id.textList);
        find_meaning_button = itemView.findViewById(R.id.button);

    }
}
