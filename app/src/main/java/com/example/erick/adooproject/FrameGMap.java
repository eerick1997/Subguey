package com.example.erick.adooproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import DataBases.DBStations;
import MapUtilities.PermissionUtils;
import UIElements.customMarkerEvent;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

/**
 * This class contains the code to manipulate a google map
 * which we go to show the lines of subway and metro bus
 **/

public class FrameGMap extends Fragment implements LocationListener,
        ConnectionCallbacks, OnConnectionFailedListener, OnMarkerClickListener,
        OnMapReadyCallback, OnMyLocationButtonClickListener {

    //Constants
    private static final String TAG = "FrameGMap.java";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 101;
    //Each 5 seconds the location is updated
    private static final int TIME_UPDATE = 5000;
    //Each second the location is update (if the user is moving too fast)
    private static final int TIME_FAST_UPDATE = 1000;
    //Variables
    private GoogleMap googleMap;
    private GoogleApiClient googleApiClient;
    private MapView mapView;
    private View view;
    private boolean mPermissionDenied = false;

    private LocationRequest locationRequest;
    private DBStations dbStations;

    //We need to limit the map
    private LatLngBounds CDMX = new LatLngBounds(
            new LatLng(19.339504, -99.226707), new LatLng(19.415291, -99.074272));
    //We need the initial position
    private LatLng initial_camera = new LatLng(19.380316, -99.132637);

    //This method is called just when the view has been created
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //We need to make an instance of a GoogleApiClient but
        //this object needs to be custom
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)//We add the Location Service API
                .addConnectionCallbacks(this)//We indicate that in this activity the interface ConnectionCallbacks has been implemented
                .addOnConnectionFailedListener(this) //We indicate that OnConnectionFailedListener has been implemented
                .build();//We make the GoogleApiClient
        startLocationUpdates();
        dbStations = new DBStations(getActivity(), "", null, 1);

    }

    //This method is called just when the view hasn't been created
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called with: inflater = [" + inflater + "], " +
                "container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");
        //We get a view element from a layout
        view = inflater.inflate(R.layout.frame_gmap, container, false);
        //We make an instance of a MapView
        mapView = (MapView) view.findViewById(R.id.mapview);
        //We create the MapView
        mapView.onCreate(savedInstanceState);
        //We call to getMapAsync method
        mapView.getMapAsync(this);
        //We show the map in the display
        mapView.onResume();
        //We return a View object to create new UI elements
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
        Log.d(TAG, "onMapReady() called with: googleMap = [" + googleMap + "]");
        //Initializing our googleMap variable
        this.googleMap = googleMap;
        this.googleMap.setOnMyLocationButtonClickListener(this);
        //We can see 3D models of buildings
        this.googleMap.setBuildingsEnabled(true);

        this.googleMap.setLatLngBoundsForCameraTarget(CDMX);
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initial_camera, 20));
        this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        this.googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(view.getContext(), R.raw.map_style_night));
        //new customMarkerEvent(getActivity(), this.googleMap).set(initial_camera);


        /**Marker marker = googleMap.addMarker(new MarkerOptions().position(initial_camera)
                .title("losreyeslinea1")
                .snippet("linea 1"));
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(oneImage()));**/

        enableMyLocation();
    }


    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        Log.d(TAG, "startLocationUpdates() called");
        //We need to create an object to start receiving location request
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(TIME_UPDATE);
        locationRequest.setFastestInterval(TIME_FAST_UPDATE);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        getFusedLocationProviderClient(getActivity()).requestLocationUpdates(locationRequest,
                new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        onLocationChanged(locationResult.getLastLocation());
                    }
                },
                Looper.myLooper());
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        FusedLocationProviderClient locationProviderClient = getFusedLocationProviderClient(getActivity());
        locationProviderClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            onLocationChanged(location);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: Error trying to get last GPS location");
                        Log.e(TAG, "onFailure: ", e);
                    }
                });
    }

    /**
     * This method enables the My location layer if the fine location permission has been granted
     **/
    private void enableMyLocation() {
        Log.d(TAG, "enableMyLocation() called");
        if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //Permission to access the location is missing
            PermissionUtils.requestPermission((AppCompatActivity) view.getContext(), LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (googleMap != null) {
            googleMap.setMyLocationEnabled(true);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult() called with: requestCode " +
                "= [" + requestCode + "], permissions = [" + permissions + "], grantResults = [" + grantResults + "]");
        //User hasn't granted permission
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }
        //permission granted
        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            //Enable the my location layer if the permission has been granted
            enableMyLocation();
            //current_location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        } else {
            //Display the missing permission error dialog when the fragments resume
            mPermissionDenied = true;
        }
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume() called");
        super.onResume();
        if (mPermissionDenied) {
            //Permission was not granted, display error dialog
            showMissingPermissionError();
            mPermissionDenied = false;
        } else {
            startLocationUpdates();
            enableMyLocation();
        }
    }

    private void showMissingPermissionError() {
        Log.d(TAG, "showMissingPermissionError() called");
        final Activity activity = (Activity) view.getContext();
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(activity.getFragmentManager(), "dialog");
    }

    @Override
    public void onLowMemory() {
        Log.d(TAG, "onLowMemory() called");
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onLocationChanged(Location location) {
        String msg = "Updated location: " +
                Double.toString(location.getLatitude()) + ","
                + Double.toString(location.getLongitude());
        try {
            //Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (Exception e){
            Log.e(TAG, "onLocationChanged: ", e);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG, "onStatusChanged() called with: provider = [" + provider + "], status = [" + status + "], extras = [" + extras + "]");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "onProviderEnabled() called with: provider = [" + provider + "]");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "onProviderDisabled() called with: provider = [" + provider + "]");
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnected() called with: bundle = [" + bundle + "]");
        Log.d(TAG, "onConnected: " + googleApiClient.isConnected());
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended() called with: i = [" + i + "]");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed() called with: connectionResult = [" + connectionResult + "]");
    }

    /**This method gets a marker and we can display information about this
     * if we get the ID of the marker or something like that**/
    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d(TAG, "onMarkerClick() called with: marker = [" + marker + "]");


        marker.hideInfoWindow();
        return true;
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Log.d(TAG, "onMyLocationButtonClick() called");
        return false;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart() called");
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop() called");
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause() called");
        super.onPause();
    }

    /**private void stopLocationUpdates() throws Exception{
        Log.d(TAG, "stopLocationUpdates() called");
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }**/

}
