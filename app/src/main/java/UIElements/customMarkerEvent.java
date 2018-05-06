package UIElements;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class customMarkerEvent {

    //Constants
    private static final String TAG = "customMarkerEvent.java";
    //Variables
    private Context context;
    private GoogleMap googleMap;

    public customMarkerEvent(Context context, GoogleMap googleMap){
        Log.d(TAG, "customMarkerEvent() called with: context = [" + context + "], googleMap = [" + googleMap + "]");
        this.context = context;
        this.googleMap = googleMap;
    }

    public void set(){

        Marker marker = googleMap.addMarker(new MarkerOptions()
        .position(new LatLng(latitude, longitude))
        .icon(BitmapDescriptorFactory.fromResource(R.drawable)))
    }

}
