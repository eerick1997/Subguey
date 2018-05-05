package Objects;

import android.util.Log;

public class Event {

    //Constants
    private static final String TAG = "Event.java";

    //Variables
    private int type;
    private String user, hour;

    public Event(int type, String user, String hour){
        Log.d(TAG, "Event() called with: type = [" + type + "], user = [" + user + "], hour = [" + hour + "]");
        this.type = type;
        this.user = user;
        this.hour = hour;
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
}
