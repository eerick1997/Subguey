package com.example.erick.adooproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;

import MapUtilities.PermissionUtils;

/**This class contains the code to manipulate a google map
 * which we go to show the lines of subway and metro bus**/

public class FrameGMap extends Fragment implements OnMyLocationButtonClickListener,
        OnMyLocationClickListener, OnMapReadyCallback, OnRequestPermissionsResultCallback {

    //Constants
    private static final String TAG = "FrameGMap.java";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    //Variables
    private GoogleMap googleMap;
    private MapView mapView;
    private View view;
    private boolean mPermissionDenied = false;
    //We need to limit the map
    private LatLngBounds CDMX = new LatLngBounds(
            new LatLng(19.339504, -99.226707), new LatLng(19.415291, -99.074272));
    //We need the initial position
    private LatLng initial_camera = new LatLng(19.380316, -99.132637);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called with: inflater = [" + inflater + "], " +
                "container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");
        view = inflater.inflate(R.layout.frame_gmap, container, false);
        mapView = (MapView) view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        mapView.onResume();
        return view;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Initializing our googleMap variable
        this.googleMap = googleMap;

        this.googleMap.setOnMyLocationButtonClickListener(this);
        //
        this.googleMap.setOnMyLocationClickListener(this);
        //We can see 3D models of buildings
        this.googleMap.setBuildingsEnabled(true);

        this.googleMap.setLatLngBoundsForCameraTarget(CDMX);
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initial_camera, 20));
        this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        this.googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(view.getContext(), R.raw.map_style_night));
        enableMyLocation();
    }

    /**
     * This method enables the My location layer if the fine location permission has been granted
     **/
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //Permission to access the location is missing
            PermissionUtils.requestPermission((AppCompatActivity) view.getContext(), LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (googleMap != null) {
            googleMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }
        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            //Enable the my location layer if the permission has been granted
            enableMyLocation();
        } else {
            //Display the missing permission error dialog when the fragments resume
            mPermissionDenied = true;
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPermissionDenied) {
            //Permission was not granted, display error dialog
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    private void showMissingPermissionError() {
        final Activity activity = (Activity) view.getContext();
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(activity.getFragmentManager(), "dialog");
    }
}
