package com.example.erick.adooproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import java.util.ArrayList;

import MapUtilities.DrawPolyline;
import MapUtilities.MLatLng;
import MapUtilities.PermissionUtils;
import Objects.Event;
import Objects.Exit;
import Objects.Service;
import Objects.Station;
import UIElements.ChangeStyle;
import UIElements.EventInfo;
import UIElements.EventsReports;
import UIElements.MyImages;

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
    private static final int TIME_UPDATE = 10000;
    //Each second the location is update (if the user is moving too fast)
    private static final int TIME_FAST_UPDATE = 5000;
    //Variables
    private GoogleMap googleMap;
    private GoogleApiClient googleApiClient;
    private MapView mapView;
    private View view;
    private boolean mPermissionDenied = false;

    private LocationRequest locationRequest;

    //We need to limit the map
    private LatLngBounds CDMX = new LatLngBounds(
            new LatLng(19.339504, -99.226707), new LatLng(19.415291, -99.074272));
    //We need the initial position
    private LatLng initial_camera = new LatLng(19.380316, -99.132637);


    /**
     * - - - - - - - - - - ABSTRACT METHODS - - - - - - - - - - - - - - -
     **/
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

        FloatingActionButton fab = this.view.findViewById(R.id.FAB_send_event);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Send event FAB pressed ", Snackbar.LENGTH_SHORT).show();
                Event event = new Event(1, "Erick", "12:00", new LatLng(-1.232423, 1.421311));
                new EventsReports(getActivity()).showDialog(event);
            }
        });

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
        mapView = view.findViewById(R.id.mapview);
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
        //Implementing onMarkerClickListener interface
        this.googleMap.setOnMarkerClickListener(this);
        this.googleMap.setLatLngBoundsForCameraTarget(CDMX);
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initial_camera, 20));
        this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        this.googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(view.getContext(), R.raw.map_style_night));
        //new customMarkerEvent(getActivity(), this.googleMap).set(initial_camera);

        MyImages images = new MyImages(getActivity());

        //If we need to add a marker we can use the code below
        Marker marker = googleMap.addMarker(new MarkerOptions().position(initial_camera)
                .title("Observatorio")
                .snippet("Metro"));
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(images.createIconMarker("observatorio1")));

        ArrayList<Service> services = new ArrayList<>();
        ArrayList<Exit> exits = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            services.add(new Service("Something", "desc"));
            exits.add(new Exit("Name"+i,"Street"+i));
        }
        MLatLng MlatLng = new MLatLng(initial_camera.latitude, initial_camera.longitude);
        Station station = new Station("Observatorio", "LM1", MlatLng, services, exits, null, null, null);
        marker.setTag(station);
        Log.d(TAG, ">>>>> onMapReady: " + marker.getTag().getClass());
        drawLine1();
        Log.i(TAG, "onMapReady: Line 1 drown");
        drawLine2();
        Log.i(TAG, "onMapReady: Line 2 drown");
        enableMyLocation();
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

        Log.i(TAG, ">>>>> onMarkerClick: " + marker.getTag().getClass());
        //We need to know the type of alert dialog that we need to display
        //Marker is an event type
        if (marker.getSnippet().equals("event")) {
            EventInfo eventInfo = new EventInfo(getActivity());
            Event event = new Event(0, "eerick1997", "12:00", initial_camera);
            eventInfo.showDialog(event);
        }
        //Marker is an station type
        else {

        }
        EventInfo eventInfo = new EventInfo(getActivity());
        Event event = new Event(0, "eerick1997", "12:00", initial_camera);
        eventInfo.showDialog(event);

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

    /** - - - - - - - - - - OUR METHODS - - - - - - - - - - - - - - - **/

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

    private void showMissingPermissionError() {
        Log.d(TAG, "showMissingPermissionError() called");
        final Activity activity = (Activity) view.getContext();
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(activity.getFragmentManager(), "dialog");
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


    private void drawLine1(){
        ArrayList<LatLng> arrayLatLng = new ArrayList<>();
        arrayLatLng.add(new LatLng(19.416191, -99.074756));
        arrayLatLng.add(new LatLng(19.412380, -99.075168));
        arrayLatLng.add(new LatLng(19.411065, -99.075651));
        arrayLatLng.add(new LatLng(19.409921, -99.076541));
        arrayLatLng.add(new LatLng(19.410002, -99.076458));
        arrayLatLng.add(new LatLng(19.409780, -99.076895));
        arrayLatLng.add(new LatLng(19.409628, -99.077276));
        arrayLatLng.add(new LatLng(19.409576, -99.078073));
        arrayLatLng.add(new LatLng(19.412085, -99.082517));
        arrayLatLng.add(new LatLng(19.416610, -99.090493));
        arrayLatLng.add(new LatLng(19.419817, -99.096257));
        arrayLatLng.add(new LatLng(19.423251, -99.102583));
        arrayLatLng.add(new LatLng(19.426887, -99.109604));
        arrayLatLng.add(new LatLng(19.428868, -99.113469));
        arrayLatLng.add(new LatLng(19.429313, -99.113962));
        arrayLatLng.add(new LatLng(19.429706, -99.114200));
        arrayLatLng.add(new LatLng(19.429878, -99.114219));
        arrayLatLng.add(new LatLng(19.431272, -99.114353));
        arrayLatLng.add(new LatLng(19.431496, -99.114413));
        arrayLatLng.add(new LatLng(19.431687, -99.114541));
        arrayLatLng.add(new LatLng(19.431804, -99.114748));
        arrayLatLng.add(new LatLng(19.432184, -99.119570));
        arrayLatLng.add(new LatLng(19.432171, -99.119802));
        arrayLatLng.add(new LatLng(19.432025, -99.120236));
        arrayLatLng.add(new LatLng(19.431893, -99.120405));
        arrayLatLng.add(new LatLng(19.431720, -99.120563));
        arrayLatLng.add(new LatLng(19.431006, -99.120856));
        arrayLatLng.add(new LatLng(19.429635, -99.120885));
        arrayLatLng.add(new LatLng(19.426145, -99.120966));
        arrayLatLng.add(new LatLng(19.425955, -99.121018));
        arrayLatLng.add(new LatLng(19.425536, -99.121245));
        arrayLatLng.add(new LatLng(19.425371, -99.121418));
        arrayLatLng.add(new LatLng(19.425218, -99.121634));
        arrayLatLng.add(new LatLng(19.425047, -99.122100));
        arrayLatLng.add(new LatLng(19.425046, -99.122497));
        arrayLatLng.add(new LatLng(19.425450, -99.125004));
        arrayLatLng.add(new LatLng(19.426204, -99.130025));
        arrayLatLng.add(new LatLng(19.426164, -99.130316));
        arrayLatLng.add(new LatLng(19.425790, -99.131047));
        arrayLatLng.add(new LatLng(19.425775, -99.131359));
        arrayLatLng.add(new LatLng(19.425910, -99.132979));
        arrayLatLng.add(new LatLng(19.426340, -99.137891));
        arrayLatLng.add(new LatLng(19.426864, -99.143366));
        arrayLatLng.add(new LatLng(19.427284, -99.148287));
        arrayLatLng.add(new LatLng(19.427116, -99.148922));
        arrayLatLng.add(new LatLng(19.425649, -99.154506));
        arrayLatLng.add(new LatLng(19.423624, -99.163261));
        arrayLatLng.add(new LatLng(19.421825, -99.171087));
        arrayLatLng.add(new LatLng(19.420541, -99.176998));
        arrayLatLng.add(new LatLng(19.420371, -99.178170));
        arrayLatLng.add(new LatLng(19.420002, -99.178895));
        arrayLatLng.add(new LatLng(19.412808, -99.182092));
        arrayLatLng.add(new LatLng(19.402792, -99.187033));
        arrayLatLng.add(new LatLng(19.402691, -99.187161));
        arrayLatLng.add(new LatLng(19.402544, -99.187296));
        arrayLatLng.add(new LatLng(19.402473, -99.187419));
        arrayLatLng.add(new LatLng(19.402390, -99.187556));
        arrayLatLng.add(new LatLng(19.402301, -99.187789));
        arrayLatLng.add(new LatLng(19.402261, -99.188025));
        arrayLatLng.add(new LatLng(19.402261, -99.188339));
        arrayLatLng.add(new LatLng(19.402418, -99.192370));
        arrayLatLng.add(new LatLng(19.402392, -99.192504));
        arrayLatLng.add(new LatLng(19.402357, -99.192612));
        arrayLatLng.add(new LatLng(19.402329, -99.192671));
        arrayLatLng.add(new LatLng(19.402279, -99.192738));
        arrayLatLng.add(new LatLng(19.400853, -99.193847));
        arrayLatLng.add(new LatLng(19.400432, -99.194043));
        arrayLatLng.add(new LatLng(19.398238, -99.200318));
        DrawPolyline draw = new DrawPolyline(getActivity(), googleMap);
        draw.draw(arrayLatLng, new ChangeStyle(getActivity()).getLineColor("metro_1"));
    }

    private void drawLine2(){
        ArrayList<LatLng> arrayLatLng = new ArrayList<>();
        arrayLatLng.add(new LatLng(19.416208, -99.074779));
        arrayLatLng.add(new LatLng(19.412397, -99.075194));
        arrayLatLng.add(new LatLng(19.411082, -99.075677));
        arrayLatLng.add(new LatLng(19.409938, -99.076567));
        arrayLatLng.add(new LatLng(19.410019, -99.076484));
        arrayLatLng.add(new LatLng(19.409797, -99.076921));
        arrayLatLng.add(new LatLng(19.409645, -99.077305));
        arrayLatLng.add(new LatLng(19.409592, -99.078099));
        arrayLatLng.add(new LatLng(19.412102, -99.082543));
        arrayLatLng.add(new LatLng(19.416627, -99.090519));
        arrayLatLng.add(new LatLng(19.419834, -99.096283));
        arrayLatLng.add(new LatLng(19.423268, -99.102609));
        arrayLatLng.add(new LatLng(19.426904, -99.109630));
        arrayLatLng.add(new LatLng(19.428885, -99.113495));
        arrayLatLng.add(new LatLng(19.429330, -99.113988));
        arrayLatLng.add(new LatLng(19.429723, -99.114226));
        arrayLatLng.add(new LatLng(19.429895, -99.114245));
        arrayLatLng.add(new LatLng(19.431289, -99.114379));
        arrayLatLng.add(new LatLng(19.431513, -99.114439));
        arrayLatLng.add(new LatLng(19.431704, -99.114567));
        arrayLatLng.add(new LatLng(19.431821, -99.114774));
        arrayLatLng.add(new LatLng(19.432201, -99.119596));
        arrayLatLng.add(new LatLng(19.432188, -99.119828));
        arrayLatLng.add(new LatLng(19.432042, -99.120262));
        arrayLatLng.add(new LatLng(19.431910, -99.120431));
        arrayLatLng.add(new LatLng(19.431737, -99.120589));
        arrayLatLng.add(new LatLng(19.431023, -99.120882));
        arrayLatLng.add(new LatLng(19.429652, -99.120911));
        arrayLatLng.add(new LatLng(19.426162, -99.120992));
        arrayLatLng.add(new LatLng(19.425972, -99.121044));
        arrayLatLng.add(new LatLng(19.425553, -99.121271));
        arrayLatLng.add(new LatLng(19.425388, -99.121444));
        arrayLatLng.add(new LatLng(19.425235, -99.121660));
        arrayLatLng.add(new LatLng(19.425064, -99.122126));
        arrayLatLng.add(new LatLng(19.425063, -99.122523));
        arrayLatLng.add(new LatLng(19.425467, -99.125030));
        arrayLatLng.add(new LatLng(19.426221, -99.130051));
        arrayLatLng.add(new LatLng(19.426181, -99.130342));
        arrayLatLng.add(new LatLng(19.425807, -99.131073));
        arrayLatLng.add(new LatLng(19.425792, -99.131385));
        arrayLatLng.add(new LatLng(19.425927, -99.133005));
        arrayLatLng.add(new LatLng(19.426357, -99.137917));
        arrayLatLng.add(new LatLng(19.426881, -99.143392));
        arrayLatLng.add(new LatLng(19.427301, -99.148313));
        arrayLatLng.add(new LatLng(19.427133, -99.148948));
        arrayLatLng.add(new LatLng(19.425666, -99.154532));
        arrayLatLng.add(new LatLng(19.423641, -99.163287));
        arrayLatLng.add(new LatLng(19.421842, -99.171113));
        arrayLatLng.add(new LatLng(19.420558, -99.177024));
        arrayLatLng.add(new LatLng(19.420388, -99.178196));
        arrayLatLng.add(new LatLng(19.420019, -99.178921));
        arrayLatLng.add(new LatLng(19.412825, -99.182118));
        arrayLatLng.add(new LatLng(19.402809, -99.187059));
        arrayLatLng.add(new LatLng(19.402708, -99.187187));
        arrayLatLng.add(new LatLng(19.402561, -99.187322));
        arrayLatLng.add(new LatLng(19.402490, -99.187445));
        arrayLatLng.add(new LatLng(19.402407, -99.187582));
        arrayLatLng.add(new LatLng(19.402318, -99.187815));
        arrayLatLng.add(new LatLng(19.402278, -99.188051));
        arrayLatLng.add(new LatLng(19.402278, -99.188365));
        arrayLatLng.add(new LatLng(19.402435, -99.192396));
        arrayLatLng.add(new LatLng(19.402409, -99.192530));
        arrayLatLng.add(new LatLng(19.402374, -99.192638));
        arrayLatLng.add(new LatLng(19.402346, -99.192697));
        arrayLatLng.add(new LatLng(19.402296, -99.192764));
        arrayLatLng.add(new LatLng(19.400870, -99.193873));
        arrayLatLng.add(new LatLng(19.400449, -99.194069));
        arrayLatLng.add(new LatLng(19.398255, -99.200344));
        DrawPolyline draw = new DrawPolyline(getActivity(), googleMap);
        draw.draw(arrayLatLng, new ChangeStyle(getActivity()).getLineColor("metro_1"));
    }
}
