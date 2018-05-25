package com.example.erick.adooproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import Adapters.AdapterLines;
import Objects.Line;
import UIElements.DividerItemDecoration;

public class LinesActivity extends AppCompatActivity {

    //Constants
    private static final String TAG = "LinesActivity.java";
    //Variables
    private ArrayList<Line> lines;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines);
        //Getting toolbar element
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_lines_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#303f9f"));

        Intent intent = getIntent();
        this.lines = intent.getParcelableArrayListExtra("Lines");

        for (int i = 0; i < lines.size(); i++)
            Log.i(TAG, "onCreate: lines.getWhichLine " + lines.get(i).getWhichLine());

        FloatingActionButton fab = findViewById(R.id.fab_lines);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.activity_lines_recycler_view);

        AdapterLines adapterLines = new AdapterLines(lines);
        recyclerView.setAdapter(adapterLines);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, android.support.v7.widget.DividerItemDecoration.VERTICAL, false));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
