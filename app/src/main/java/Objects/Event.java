package Objects;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class Event {

    //Constants
    private static final String TAG = "Event.java";

    //Variables
    private int type;
    private String user, hour;
    private LatLng position;

    public Event() {
    }

    public Event(int type, String user, String hour, LatLng position) {
        Log.d(TAG, "Event() called with: type = [" + type + "], user = [" + user + "], hour = [" + hour + "]");
        this.type = type;
        this.user = user;
        this.hour = hour;
        this.position = position;
    }

    public int getType(){
        Log.d(TAG, "getType() called");
        Log.d(TAG, "getType() returned: " + this.type);
        return this.getType();
    }

    public String getUser() {
        Log.d(TAG, "getUser() called");
        Log.d(TAG, "getUser() returned: " + this.user);
        return this.user;
    }

    public String getHour(){
        Log.d(TAG, "getHour() called");
        Log.d(TAG, "getHour() returned: " + hour);
        return this.hour;
    }

    public LatLng getPosition() {
        Log.d(TAG, "getPosition() called");
        Log.d(TAG, "getPosition() returned: " + this.position);
        return this.position;
    }
}
