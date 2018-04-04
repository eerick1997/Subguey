package com.example.erick.adooproject;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import UIElements.EventInfo;
import Objects.Exit;
import Objects.Line;
import Objects.Service;
import Objects.Station;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Constants
    private static final String TAG = "Main.java";

    //Variables
    /**
     * FragmentManager is an interface to interact with Fragments inside an Activity
     **/
    private FragmentManager fragmentManager;
    //This variable contains the las id
    private int lastId = 0;

    //private ArrayList<Line> lines = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });


        } catch (Exception e) {
            Log.e(TAG, "onCreate: ", e);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        new EventInfo(Main.this).showDialog();
        /**We go to show the Fragment that contains our google map**/
        fragmentManager = getFragmentManager();
        lastId = R.id.nav_map_main;
        fragmentManager.beginTransaction().replace(R.id.content_frame, new FrameGMap()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.d(TAG, "onNavigationItemSelected() called with: item = [" + item + "]");

        //Getting a FragmentManager
        fragmentManager = getFragmentManager();
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_map_main && lastId != R.id.nav_map_main) {
            Log.i(TAG, "onNavigationItemSelected: id " + id);
            fragmentManager.beginTransaction().replace(R.id.content_frame, new FrameGMap()).commit();
        } else if(id == R.id.nav_lines_subway){
            Log.i(TAG, "onNavigationItemSelected: ");
            justForTesting();
        }
        lastId = id;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void justForTesting(){
        ArrayList<Line> lines = new ArrayList<>();
        /***************************THIS CODE IS JUST FOR TESTING*********************************/

        /*********** JUST FOR TESTING **********/

        ArrayList<Service> services = new ArrayList<>();
        services.add(new Service("Biciestacionamiento",
                "lunes a viernes de 9:00 a 19:00 horas",
                "Teléfonos 5242-6144, 5242-6145",
                "Sobre la calle Escuadrón 201 s/n, colonia Real del Monte, frente a la terminal de Autobuses Observatorio-Poniente"));

        services.add(new Service("Central camionera", "", "",
                "Por la salida dirección Pantitlán, cruzando la calle se encuentra la Central de autobuses Poniente (también conocida como Central Observatorio)"));

        services.add(new Service("Instalación para personas con discapacidad", "", "",
                "Dos elevadores, los cuales se usan si cuenta con tarjeta Libre Acceso y rampas de acceso"));

        services.add(new Service("Ministerio público", "lunes a viernes de 9:00 a 19:00 horas",
                "teléfonos 5242-6144, 5242-6145",
                "Sobre la calle Escuadrón 201 s/n, colonia Real del Monte, frente a la terminal de Autobuses Observatorio-Poniente"));

        ArrayList<Exit> exits = new ArrayList<>();
        exits.add(new Exit("Nororiente", "Avenida Minas de Arena, Colonia Pino Suárez"));
        exits.add(new Exit("Norponiente", "Avenida Minas de Arena, Colonia Pino Suárez"));
        exits.add(new Exit("Suroriente", "Real del Monte, Colonia Pino Suárez"));
        exits.add(new Exit("Surponiente", "Real del Monte, Colonia Pino Suárez"));

        ArrayList<Station> stations = new ArrayList<>();

        //for(int i = 1; i <= 9; i++)
            stations.add(new Station("Observatorio 1", "1", new LatLng(19.3982121,-99.2005697), services, exits));
            stations.add(new Station("Observatorio 2", "2", new LatLng(19.3982121,-99.2005697), services, exits));
            stations.add(new Station("Observatorio 3", "3", new LatLng(19.3982121,-99.2005697), services, exits));
       // ArrayList<Station> stations = new ArrayList<>();


        for(int i = 1; i <= 9; i++)
            lines.add(new Line(String.valueOf(i), stations));
        lines.add(new Line("A", stations));
        lines.add(new Line("B", stations));
        lines.add(new Line("12", stations));

        Intent intent = new Intent(Main.this, LinesActivity.class);
        intent.putParcelableArrayListExtra("Lines", lines);
        startActivity(intent);

        /******************************* END JUST FOR TESTING ******************************************/
    }

}
