package com.example.erick.adooproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import Adapters.AdapterStations;
import Objects.Station;

public class ActivityStations extends AppCompatActivity {

    //Constants
    private static final String TAG = "ActivityStations.java";

    //Variables
    private ArrayList<Station> stations;
    private Toolbar toolbar;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);
        //Getting toolbar element
        toolbar = (Toolbar) findViewById(R.id.activity_stations_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        //Setting color in the status bar
        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#303f9f"));

        //Getting FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_stations);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Getting extra information
        Intent intent = getIntent();

        stations = intent.getParcelableArrayListExtra("Stations");

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.activity_stations_recycler_view);
        AdapterStations adapterStations = new AdapterStations(stations);
        recyclerView.setAdapter(adapterStations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, DividerItemDecoration.VERTICAL, false));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, UIElements.DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        changeUIColor(intent.getStringExtra("color"));

    }

    /**
     * This function changes the color of toolbar and status bar
     * depending of the line
     **/
    @SuppressLint({"ResourceAsColor", "LongLogTag"})
    public void changeUIColor(String color) {
        Log.d(TAG, "changeUIColor() called");
        Log.d(TAG, "changeUIColor() called with: color = [" + color + "]");
        //Changing the color of the Status Bar
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        switch (color) {
            case "1":
                window.setStatusBarColor(Color.parseColor(getString(R.string.LM1)));
                toolbar.setBackgroundColor(Color.parseColor(getString(R.string.LM1)));
                break;
            case "2":
                window.setStatusBarColor(Color.parseColor(getString(R.string.LM2)));
                toolbar.setBackgroundColor(Color.parseColor(getString(R.string.LM2)));
                break;
            case "3":
                window.setStatusBarColor(Color.parseColor(getString(R.string.LM3)));
                toolbar.setBackgroundColor(Color.parseColor(getString(R.string.LM3)));
                break;
            case "4":
                window.setStatusBarColor(Color.parseColor(getString(R.string.LM4)));
                toolbar.setBackgroundColor(Color.parseColor(getString(R.string.LM4)));
                break;
            case "5":
                window.setStatusBarColor(Color.parseColor(getString(R.string.LM5)));
                toolbar.setBackgroundColor(Color.parseColor(getString(R.string.LM5)));
                break;
            case "6":
                window.setStatusBarColor(Color.parseColor(getString(R.string.LM6)));
                toolbar.setBackgroundColor(Color.parseColor(getString(R.string.LM6)));
                break;
            case "7":
                window.setStatusBarColor(Color.parseColor(getString(R.string.LM7)));
                toolbar.setBackgroundColor(Color.parseColor(getString(R.string.LM7)));
                break;
            case "8":
                window.setStatusBarColor(Color.parseColor(getString(R.string.LM8)));
                toolbar.setBackgroundColor(Color.parseColor(getString(R.string.LM8)));
                break;
            case "9":
                window.setStatusBarColor(Color.parseColor(getString(R.string.LM9)));
                toolbar.setBackgroundColor(Color.parseColor(getString(R.string.LM9)));
                break;
            case "A":
                window.setStatusBarColor(Color.parseColor(getString(R.string.LMA)));
                toolbar.setBackgroundColor(Color.parseColor(getString(R.string.LMA)));
                break;
            case "B":
                window.setStatusBarColor(Color.parseColor(getString(R.string.LMB)));
                toolbar.setBackgroundColor(Color.parseColor(getString(R.string.LMB)));
                break;
            case "12":
                window.setStatusBarColor(Color.parseColor(getString(R.string.LM12)));
                toolbar.setBackgroundColor(Color.parseColor(getString(R.string.LM12)));
                break;
        }
    }

}
