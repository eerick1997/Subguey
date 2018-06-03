package DataBases.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import Objects.Event;
import Objects.Station;

import static DataBases.SQLite.Utilities.DB_NAME;
import static DataBases.SQLite.Utilities.E_CREA;
import static DataBases.SQLite.Utilities.E_DROP;
import static DataBases.SQLite.Utilities.E_GETA;
import static DataBases.SQLite.Utilities.E_GETO;
import static DataBases.SQLite.Utilities.E_ID;
import static DataBases.SQLite.Utilities.E_TRUN;
import static DataBases.SQLite.Utilities.S_CREA;
import static DataBases.SQLite.Utilities.S_DROP;
import static DataBases.SQLite.Utilities.S_GETA;
import static DataBases.SQLite.Utilities.S_ID;
import static DataBases.SQLite.Utilities.S_OBJE;
import static DataBases.SQLite.Utilities.S_TRUN;
import static DataBases.SQLite.Utilities.TBL_EV;
import static DataBases.SQLite.Utilities.TBL_ST;

public class DataBases extends SQLiteOpenHelper {

    //Constants
    private static final String TAG = "DataBases.java";

    //Variables
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private Converter converter = new Converter();

    //Constructor
    public DataBases(Context context) {
        super(context, DB_NAME, null, 1);
        Log.d(TAG, "DataBases() called with: context = [" + context + "]");
        this.context = context;
    }

    public void insertStation(String id, Station station) {
        Log.d(TAG, "insertStation() called with: id = [" + id + "], station = [" + station + "]");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(S_ID, id);
        contentValues.put(S_OBJE, converter.toBytes(station));
        db.insert(TBL_ST, null, contentValues);
        db.close();
    }

    public void insertEvent(String id, Event event) {
        Log.d(TAG, "insertEvent() called with: id = [" + id + "], event = [" + event + "]");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(E_ID, id);
        contentValues.put(S_OBJE, converter.toBytes(event));
        db.insert(TBL_EV, null, contentValues);
        db.close();
    }

    public ArrayList<Station> getStations() {
        Log.d(TAG, "getStations() called");
        ArrayList<Station> stations = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //Probably we catch an Exception
        try {
            Cursor cursor = db.rawQuery(S_GETA, null);
            if (cursor.moveToFirst()) {
                do {
                    stations.add((Station) converter.getObject(cursor.getBlob(1)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "getStations: ", e);
        }
        return stations;
    }

    public ArrayList<Event> getEvents() {
        Log.d(TAG, "getEvents() called");
        ArrayList<Event> events = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //Probably we catch an exception
        try {
            Cursor cursor = db.rawQuery(E_GETA, null);
            if (cursor.moveToFirst()) {
                do {
                    events.add((Event) converter.getObject(cursor.getBlob(1)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "getEvents: ", e);
        }
        return events;
    }

    public Station getStation(String id) {
        Log.d(TAG, "getStation() called with: id = [" + id + "]");
        Station station = null;
        SQLiteDatabase db = this.getReadableDatabase();
        //Probably we catch an exception
        try {
            String QUERY = E_GETO + "'" + id + "'";
            Log.i(TAG, "getStation: QUERY " + QUERY);
            Cursor cursor = db.rawQuery(QUERY, null);
            station = (Station) converter.getObject(cursor.getBlob(0));
        } catch (Exception e) {
            Log.e(TAG, "getStation: ", e);
        }
        Log.d(TAG, "getStation() returned: " + station);
        return station;
    }

    public Event getEvent(String id) {
        Log.d(TAG, "getEvent() called with: id = [" + id + "]");
        Event event = null;
        SQLiteDatabase db = this.getReadableDatabase();
        //Probably we catch an exception
        try {
            String QUERY = E_GETO + "'" + id + "'";
            Cursor cursor = db.rawQuery(E_GETO, null);
            event = (Event) converter.getObject(cursor.getBlob(0));
        } catch (Exception e) {
            Log.e(TAG, "getEvent: ", e);
        }
        Log.d(TAG, "getEvent() returned: " + event);
        return event;
    }

    public void truncateStations() {
        Log.d(TAG, "truncateStations() called");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(S_TRUN);
        db.close();
    }

    public void truncateEvents() {
        Log.d(TAG, "truncateEvents() called");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(E_TRUN);
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate() called with: db = [" + db + "]");
        //We create our table Station
        db.execSQL(S_CREA);
        //We create our table Events
        db.execSQL(E_CREA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade() called with: db = [" + db + "]," +
                " oldVersion = [" + oldVersion + "], newVersion = [" + newVersion + "]");
        //Dropping the last database events
        db.execSQL(E_DROP);
        //Dropping the last database stations
        db.execSQL(S_DROP);
    }
}
