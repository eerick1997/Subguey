package com.example.erick.adooproject;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import MapUtilities.MLatLng;
import Objects.Exit;
import Objects.Line;
import Objects.Service;
import Objects.Station;
import Preferences.SubgueyPreferences;
import de.hdodenhof.circleimageview.CircleImageView;

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

    private SubgueyPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = new SubgueyPreferences(Main.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        TextView user_name = view.findViewById(R.id.TXT_nav_header_name);
        CircleImageView user_profile = view.findViewById(R.id.IMG_nav_header_user_profile);
        Log.i(TAG, "onCreate: img url " + preferences.getProfileURIIMG());
        user_name.setText("");
        user_name.setText(getString(R.string.hello_user) + " " + preferences.getNameUser());
        /**This code downloads an image, and puts in the nav bar circle image**/
        Glide.with(Main.this)
                .load(preferences.getProfileURIIMG())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_default_profile)
                .override(270,270)
                .centerCrop()
                .into(user_profile);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new FrameGMap()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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


        if (id == R.id.nav_lines_subway) {
            Log.i(TAG, "onNavigationItemSelected: ");
            //justForTesting("Metro");
        } else if (id == R.id.nav_lines_metro_bus) {
            Log.i(TAG, "onNavigationItemSelected: ");
            //justForTestingMetrobus("Metrobus");
        }
        lastId = id;

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void justForTesting(String title) {
        ArrayList<Line> lines = new ArrayList<>();
        /***************************THIS CODE IS JUST FOR TESTING*********************************/

        /*********** JUST FOR TESTING **********/

        ArrayList<Service> services = new ArrayList<>();
        services.add(new Service("Biciestacionamiento",
                "lunes a viernes de 9:00 a 19:00 horas"));

        services.add(new Service("Central camionera", ""));

        services.add(new Service("Instalación para personas con discapacidad", ""));

        services.add(new Service("Ministerio público", "lunes a viernes de 9:00 a 19:00 horas"));

        ArrayList<Exit> exits = new ArrayList<>();
        exits.add(new Exit("Nororiente", "Avenida Minas de Arena, Colonia Pino Suárez"));
        exits.add(new Exit("Norponiente", "Avenida Minas de Arena, Colonia Pino Suárez"));
        exits.add(new Exit("Suroriente", "Real del Monte, Colonia Pino Suárez"));
        exits.add(new Exit("Surponiente", "Real del Monte, Colonia Pino Suárez"));

        ArrayList<Station> stations = new ArrayList<>();
        ArrayList<MLatLng> positions = new ArrayList<>();
        Double lat = 19.3982121;
        Double lng = -99.2005697;

        for (int i = 0; i < 5; i++)
            positions.add(new MLatLng(lat += 0.05, lng += 0.05));

        stations.add(new Station("Observatorio 1", "metro 1", new MLatLng(19.3982121, -99.2005697), services, exits, /**positions, positions,**/ null));
        stations.add(new Station("Observatorio 2", "metro 2", new MLatLng(19.3982121, -99.2005697), services, exits, /**positions, positions,**/ null));
        stations.add(new Station("Observatorio 3", "metro 3", new MLatLng(19.3982121, -99.2005697), services, exits, /**positions, positions,**/ null));

        for (int i = 1; i <= 9; i++)
            lines.add(new Line("ABC", "metro " + i, stations,null));
        lines.add(new Line("metro 1", "metro a", stations,null));
        lines.add(new Line("metro 2", "metro b", stations,null));
        lines.add(new Line("metro 3", "metro 12", stations,null));

        Intent intent = new Intent(Main.this, LinesActivity.class);
        intent.putParcelableArrayListExtra("Lines", lines);
        intent.putExtra("title", title);
        startActivity(intent);

        /******************************* END JUST FOR TESTING ******************************************/
    }

    private void justForTestingMetrobus(String title) {
        ArrayList<Line> lines = new ArrayList<>();
        /***************************THIS CODE IS JUST FOR TESTING*********************************/

        /*********** JUST FOR TESTING **********/

        ArrayList<Service> services = new ArrayList<>();
        services.add(new Service("Biciestacionamiento",
                "lunes a viernes de 9:00 a 19:00 horas"));

        services.add(new Service("Central camionera", ""));

        services.add(new Service("Instalación para personas con discapacidad", ""));

        services.add(new Service("Ministerio público", "lunes a viernes de 9:00 a 19:00 horas"));

        ArrayList<Exit> exits = new ArrayList<>();
        exits.add(new Exit("Nororiente", "Avenida Minas de Arena, Colonia Pino Suárez"));
        exits.add(new Exit("Norponiente", "Avenida Minas de Arena, Colonia Pino Suárez"));
        exits.add(new Exit("Suroriente", "Real del Monte, Colonia Pino Suárez"));
        exits.add(new Exit("Surponiente", "Real del Monte, Colonia Pino Suárez"));

        ArrayList<Station> stations = new ArrayList<>();
        ArrayList<MLatLng> positions = new ArrayList<>();
        Double lat = 19.3982121;
        Double lng = -99.2005697;

        for (int i = 0; i < 5; i++)
            positions.add(new MLatLng(lat += 0.05, lng += 0.05));
        stations.add(new Station("Observatorio 1", "metrobus 1", new MLatLng(19.3982121, -99.2005697), services, exits, /**positions, positions,**/ null));
        stations.add(new Station("Observatorio 2", "metrobus 2", new MLatLng(19.3982121, -99.2005697), services, exits, /**positions, positions,**/ null));
        stations.add(new Station("Observatorio 3", "metrobus 3", new MLatLng(19.3982121, -99.2005697), services, exits, /**positions, positions,**/ null));

        for (int i = 1; i <= 6; i++)
            lines.add(new Line("ABC", "metrobus " + i, stations, null));

        Intent intent = new Intent(Main.this, LinesActivity.class);
        intent.putParcelableArrayListExtra("Lines", lines);
        intent.putExtra("title", title);
        startActivity(intent);

        /******************************* END JUST FOR TESTING ******************************************/
    }
}
