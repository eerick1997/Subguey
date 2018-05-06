package Objects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class Line implements Parcelable, Serializable {

    //Constanta
    private static final String TAG = "Line.java";
    //Variables
    private String whichLine, id;
    //As we know each line contains stations, so
    private ArrayList<Station> stations;

    //Constructor
    public Line(String whichLine, ArrayList<Station> stations) {
        //Initializing out global variables
        this.id = id;
        this.whichLine = whichLine;
        this.stations = stations;
    }


    /**
     * --------------- GETTERS ---------------
     **/
    public String getWhichLine() {
        Log.d(TAG, "getWhichLine() called");
        Log.d(TAG, "getWhichLine() returned: " + this.whichLine);
        return this.whichLine;
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

    /**
     * ---------- PARCELABLE METHODS ----------
     **/

    protected Line(Parcel in) {
        whichLine = in.readString();
        stations = in.createTypedArrayList(Station.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(whichLine);
        dest.writeTypedList(stations);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
