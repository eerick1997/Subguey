package MapUtilities;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class DrawPolyline {

    //Constants
    private static final String TAG = "DrawPolyline.java";
    //Variables
    private Context context;
    private GoogleMap googleMap;

    public DrawPolyline(Context context, GoogleMap googleMap) {
        Log.d(TAG, "DrawPolyline() called with: context = [" + context + "], " +
                "googleMap = [" + googleMap + "]");
        this.context = context;
        this.googleMap = googleMap;
    }

    public void draw(ArrayList<MLatLng> points, String color) {
        Log.d(TAG, "draw() called with: points = [" + points + "]");
        PolylineOptions polylineOptions = new PolylineOptions();

        //for(int i = 0; i < points.size(); i++){
           // for(int j = 0; j < points.get(i).size(); j++){

            //}
        //}
        //polylineOptions.addAll(points);

        polylineOptions.color(Color.parseColor(color));
        googleMap.addPolyline(polylineOptions);
    }

}
