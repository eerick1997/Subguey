package Objects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.erick.adooproject.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import android.R.string;

/**
 * This object contains information about an Station
 **/
public class Station implements Parcelable, Serializable {

    //Constants
    private static final String TAG = "Station.java";
    //Variables
    private LatLng position;
    private String name, line;
    //Here we store information about different services into each station
    private ArrayList<Service> services;
    //Here we store information about different exits into each station
    private ArrayList<Exit> exits;
    /*Here we store information about different the points of the path to go to the
    next station*/

    private ArrayList<LatLng> next;
    private ArrayList<LatLng> previous;

    //Constructor
    public Station(String name, String line, LatLng position,
                   ArrayList<Service> services, ArrayList<Exit> exits,
                   ArrayList<LatLng> next, ArrayList<LatLng> previous) {
        Log.d(TAG, "Station() called with: name = [" + name + "], " +
                "line = [" + line + "], position = [" + position + "], " +
                "services = [" + services + "], exits = [" + exits + "], " +
                "next = [" + next + "], previous = [" + previous + "]");
        this.name = name;
        this.line = line;
        this.position = position;
        this.services = services;
        this.exits = exits;
        this.next = next;
        this.previous = previous;
    }

    /**
     * --------------- GETTERS ---------------
     **/

    public String getName() {
        Log.d(TAG, "getName() called");
        Log.d(TAG, "getName() returned: " + this.name);
        return this.name;
    }

    public String getLine() {
        Log.d(TAG, "getLine() called");
        Log.d(TAG, "getLine() returned: " + this.line);
        return this.line;
    }

    public LatLng getPosition() {
        Log.d(TAG, "getPosition() called");
        Log.d(TAG, "getPosition() returned: " + this.position);
        return this.position;
    }

    public ArrayList<Service> getServices() {
        Log.d(TAG, "getServices() called");
        Log.d(TAG, "getServices() returned: " + this.services);
        return this.services;
    }

    public ArrayList<Exit> getExits() {
        Log.d(TAG, "getExits() called");
        Log.d(TAG, "getExits() returned: " + this.exits);
        return this.exits;
    }

    public ArrayList<ElementAdapter> getElements() {
        Log.d(TAG, "getElements() called");
        return fillElement();
    }

    public ArrayList<LatLng> getNext(){
        Log.d(TAG, "getNext() called");
        return this.next;
    }

    public ArrayList<LatLng> getPrevious() {
        Log.d(TAG, "getPrevious() called");
        return previous;
    }

    /**
     * --------------- Other methods ----------------
     **/
    public ArrayList<ElementAdapter> fillElement() {
        Log.d(TAG, "fillElement() called");
        ArrayList<ElementAdapter> elements = new ArrayList<>();

        Log.i(TAG, "fillElement: " + exits.size());
        Log.i(TAG, "fillElement: " + services.size());
        for (int i = 0; i < exits.size(); i++)
            elements.add(new ElementAdapter(exits.get(i).getName(), exits.get(i).getStreets()));
        for (int i = 0; i < services.size(); i++) {
            String description = "";

            if(!services.get(i).getUbication().isEmpty())
                description += "Ubicación: \n\n" + services.get(i).getUbication();

            if (!description.isEmpty())
                description += " \n\n ";

            if(!services.get(i).getSchedule().isEmpty())
                description += "Horario: \n\n" + services.get(i).getSchedule();

            if (!description.isEmpty())
                description += " \n\n ";

            if(!services.get(i).getContact().isEmpty())
                description += "Contacto: \n\n" + services.get(i).getContact();

            elements.add(new ElementAdapter(services.get(i).getName(), description));
        }
        Log.d(TAG, "fillElement() returned: " + elements);
        return elements;
    }

    /**
     * --------------- PARCEL METHODS ---------------
     **/


    protected Station(Parcel in) {
        position = in.readParcelable(LatLng.class.getClassLoader());
        name = in.readString();
        line = in.readString();
        services = in.createTypedArrayList(Service.CREATOR);
        exits = in.createTypedArrayList(Exit.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(position, flags);
        dest.writeString(name);
        dest.writeString(line);
        dest.writeTypedList(services);
        dest.writeTypedList(exits);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Station> CREATOR = new Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };

}
