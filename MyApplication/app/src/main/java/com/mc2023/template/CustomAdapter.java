package com.mc2023.template;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    private List<MyModel> list;

    public CustomAdapter(Context context, List<MyModel> list){
        this.context =  context;
        this.list = list;
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textList.setText(list.get(position).getTextContent());
        holder.find_meaning_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("edttext", list.get(position).meaning);
                Fragment_Meaning fragment_first_page = new Fragment_Meaning();
                FragmentTransaction fragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragementcontainer,fragment_first_page);
                fragment_first_page.setArguments(bundle);
                fragmentTransaction.commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
