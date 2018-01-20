package com.gmonetix.myapp;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gmonetix.myapp.adapter.MyAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter);

        MyViewModel model = ViewModelProviders.of(this).get(MyViewModel.class);
        model.getPages().observe(this, pages -> {

            adapter.setList(pages);

        });

    }

}
