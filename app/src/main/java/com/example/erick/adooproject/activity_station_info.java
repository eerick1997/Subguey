package com.example.erick.adooproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;

import Adapters.AdapterStationsInfo;
import Objects.Station;

/**This class contains the view to show information about
 * each station, we use animations to give a better user experience
 * Also we use the object station to get information and obviously
 * we go to display it**/
public class activity_station_info extends AppCompatActivity implements OnMapReadyCallback, Serializable {

    //Constants
    private static final String TAG = "activity_station_info.java";
    //Variables to make the coordinator layout
    private FloatingActionButton fab_button;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private MapView mapView;
    private Station station;
    private GoogleMap googleMap;
    //We go to change the color of the toolbar so
    private Toolbar toolbar;
    private RecyclerView recyclerView;


    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_info);

        //Getting intent
        Intent intent = getIntent();
        this.station = (Station) intent.getParcelableExtra("Station");

        Log.i(TAG, "onCreate: " + station);
        Log.i(TAG, "onCreate: " + station.getServices());
        Log.i(TAG, "onCreate: " + station.getExits());
        //Getting a google map
        mapView = (MapView) findViewById(R.id.google_map_station_info);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        mapView.onResume();

        //App bar
        toolbar = (Toolbar) findViewById(R.id.station_info_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(station.getName());

        /** JUST FOR TESTING **/
        recyclerView = (RecyclerView) findViewById(R.id.station_info_recycler_view);
        //ArrayList<Station> stations = new ArrayList<>();
        //stations.add(station);
        AdapterStationsInfo adapterStationsInfo = new AdapterStationsInfo(station.getElements());
        recyclerView.setAdapter(adapterStationsInfo);
        /** END JUST FOR TESTING **/
        recyclerView.setLayoutManager(new LinearLayoutManager(this, DividerItemDecoration.VERTICAL, false));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, UIElements.DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        fab_button = (FloatingActionButton) findViewById(R.id.station_info_fab);
        fab_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (googleMap.getMapType() != GoogleMap.MAP_TYPE_SATELLITE)
                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                else googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                Snackbar.make(v, "Probando FAB", Snackbar.LENGTH_LONG).show();
            }
        });

        //CollapsingToolbarLayout
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.station_info_collapsing_layout);
        collapsingToolbarLayout.setTitle(station.getName());

        //Changing the color
        changeUIColor();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.addMarker(new MarkerOptions()
                .position(station.getPosition())).setTitle(station.getName());
        this.googleMap.setMaxZoomPreference(19);
        this.googleMap.setIndoorEnabled(true);
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(station.getPosition(), 18));
    }

    /**
     * This function changes the color of toolbar and status bar
     * depending of the line
     **/
    @SuppressLint({"ResourceAsColor", "LongLogTag"})
    public void changeUIColor() {
        Log.d(TAG, "changeUIColor() called");
        //Changing the color of the Status Bar
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        Log.i(TAG, "changeUIColor: " + station.getLine());
        switch (station.getLine()) {
            case "1":
                window.setStatusBarColor(Color.parseColor(getString(R.string.L1)));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor(getString(R.string.L1)));
                break;
            case "2":
                window.setStatusBarColor(Color.parseColor(getString(R.string.L2)));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor(getString(R.string.L2)));
                break;
            case "3":
                window.setStatusBarColor(Color.parseColor(getString(R.string.L3)));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor(getString(R.string.L3)));
                break;
            case "4":
                window.setStatusBarColor(Color.parseColor(getString(R.string.L4)));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor(getString(R.string.L4)));
                break;
            case "5":
                window.setStatusBarColor(Color.parseColor(getString(R.string.L5)));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor(getString(R.string.L5)));
                break;
            case "6":
                window.setStatusBarColor(Color.parseColor(getString(R.string.L6)));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor(getString(R.string.L6)));
                break;
            case "7":
                window.setStatusBarColor(Color.parseColor(getString(R.string.L7)));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor(getString(R.string.L7)));
                break;
            case "8":
                window.setStatusBarColor(Color.parseColor(getString(R.string.L8)));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor(getString(R.string.L8)));
                break;
            case "9":
                window.setStatusBarColor(Color.parseColor(getString(R.string.L9)));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor(getString(R.string.L9)));
                break;
            case "A":
                window.setStatusBarColor(Color.parseColor(getString(R.string.LA)));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor(getString(R.string.LA)));
                break;
            case "B":
                window.setStatusBarColor(Color.parseColor(getString(R.string.LB)));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor(getString(R.string.LB)));
                break;
            case "12":
                window.setStatusBarColor(Color.parseColor(getString(R.string.L12)));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor(getString(R.string.L12)));
                break;
                //Hola mundo :'v'
        }
    }

}