package Objects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.ArrayList;

import MapUtilities.MLatLng;

public class Line implements Parcelable, Serializable {

    //Constanta
    private static final String TAG = "Line.java";
    //Variables
    private String line, id;
    //As we know each line contains stations, so
    private ArrayList<Station> stations;
    private ArrayList<MLatLng> path;

    //Constructor
    public Line(String id, String line, ArrayList<Station> stations, ArrayList<MLatLng> path) {
        Log.d(TAG, "Line() called with: id = [" + id + "], line = [" + line + "], " +
                "stations = [" + stations + "], path = [" + path + "]");
        //Initializing out global variables
        this.id = id;
        this.line = line;
        this.stations = stations;
        this.path = path;
    }

    /**
     * --------------- GETTERS ---------------
     **/
    public String getWhichLine() {
        Log.d(TAG, "getWhichLine() called");
        Log.d(TAG, "getWhichLine() returned: " + this.line);
        return this.line;
    }

    public ArrayList<Station> getStations() {
        Log.d(TAG, "getStations() called");
        Log.d(TAG, "getStations() returned: " + this.stations);
        return this.stations;
    }

    public String getId(){
        Log.d(TAG, "getId() called");
        Log.d(TAG, "getId() returned: " + this.id);
        return this.id;
    }

    public ArrayList<MLatLng> getPath() {
        Log.d(TAG, "getPath() called");
        Log.d(TAG, "getPath() returned: " + this.path);
        return this.path;
    }

    /**
     * ---------- PARCELABLE METHODS ----------
     **/

    protected Line(Parcel in) {
        line = in.readString();
        id = in.readString();
        stations = in.createTypedArrayList(Station.CREATOR);
        path = in.createTypedArrayList(MLatLng.CREATOR);
    }

    public static final Creator<Line> CREATOR = new Creator<Line>() {
        @Override
        public Line createFromParcel(Parcel in) {
            return new Line(in);
        }

        @Override
        public Line[] newArray(int size) {
            return new Line[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(line);
        dest.writeString(id);
        dest.writeTypedList(stations);
        dest.writeTypedList(path);
    }
}
