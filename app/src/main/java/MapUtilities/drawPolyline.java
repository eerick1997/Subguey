package MapUtilities;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class drawPolyline {

    //Constants
    private static final String TAG = "drawPolyline.java";
    //Variables
    private Context context;
    private GoogleMap googleMap;

    public drawPolyline(Context context, GoogleMap googleMap) {
        Log.d(TAG, "drawPolyline() called with: context = [" + context + "], " +
                "googleMap = [" + googleMap + "]");
        this.context = context;
        this.googleMap = googleMap;
    }

    public void draw(ArrayList<LatLng> points, String color) {
        Log.d(TAG, "draw() called with: points = [" + points + "]");
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.addAll(points);
        polylineOptions.color(Color.parseColor(color));
        googleMap.addPolyline(polylineOptions);
    }

}
