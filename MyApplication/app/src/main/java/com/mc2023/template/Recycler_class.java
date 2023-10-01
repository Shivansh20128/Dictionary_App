package com.mc2023.template;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Recycler_class extends AppCompatActivity {
    RecyclerView recyclerView;
    List<MyModel> myModelList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_meaning);

        recyclerView = findViewById(R.id.recycler);
        Intent intent = getIntent();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String str = intent.getStringExtra("message_key");
        myModelList = new ArrayList<>();

        customAdapter = new CustomAdapter(this, myModelList);
        recyclerView.setAdapter(customAdapter);


    }
}
