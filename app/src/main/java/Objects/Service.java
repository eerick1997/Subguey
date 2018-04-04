package Objects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;

/**
 * As we know in each station we have different services so,
 * this class was created to get this information
 * **/
public class Service implements Parcelable, Serializable{

    //Constants
    private static final String TAG = "Service.java";
    //Variables
    private String name, schedule, contact, ubication;

    //Constructor
    public Service(String name, String schedule, String contact, String ubication){
        //Initializing global variables
        this.name = name;
        this.schedule = schedule;
        this.contact = contact;
        this.ubication = ubication;
    }

    protected Service(Parcel in) {
        name = in.readString();
        schedule = in.readString();
        contact = in.readString();
        ubication = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(schedule);
        dest.writeString(contact);
        dest.writeString(ubication);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Service> CREATOR = new Creator<Service>() {
        @Override
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
        }
    };

    /**--------------- GETTERS ----------------**/
    public String getName(){
        Log.d(TAG, "getName() called");
        Log.d(TAG, "getName() returned: " + this.name);
        return this.name;
    }

    public String getSchedule(){
        Log.d(TAG, "getSchedule() called");
        Log.d(TAG, "getSchedule() returned: " + this.schedule);
        return this.schedule;
    }

    public String getContact(){
        Log.d(TAG, "getContact() called");
        Log.d(TAG, "getContact() returned: " + this.contact);
        return this.contact;
    }

    public String getUbication(){
        Log.d(TAG, "getUbication() called");
        Log.d(TAG, "getUbication() returned: " + this.ubication);
        return this.ubication;
    }
}
