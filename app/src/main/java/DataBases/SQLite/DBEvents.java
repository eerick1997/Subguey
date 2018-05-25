package DataBases.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import Objects.Event;

import static DataBases.SQLite.Utilities.DB_EVENTS_nameDB;
import static DataBases.SQLite.Utilities.DB_EVENT_HOUR;
import static DataBases.SQLite.Utilities.DB_EVENT_POSITION;
import static DataBases.SQLite.Utilities.DB_EVENT_PUBLISHER;
import static DataBases.SQLite.Utilities.DB_EVENT_TYPE;
import static DataBases.SQLite.Utilities.DB_EVENT_tbl;

public class DBEvents extends SQLiteOpenHelper {

    //Constants
    private static final String TAG = "DBEvents.java";
    //Variables
    private Context context;
    private Converter converter = new Converter();

    //Constructor
    public DBEvents(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_EVENTS_nameDB, null, 1);
        Log.d(TAG, "DBEvents() called with: context = [" + context + "], name = [" + name + "], " +
                "factory = [" + factory + "], version = [" + version + "]");
        //Initializing variables
        this.context = context;
    }

    /**
     * --------------- ABSTRACT METHODS ---------------
     **/
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate() called with: db = [" + db + "]");
        //Making a query
        String query = "CREATE TABLE " + DB_EVENT_tbl + " (" +
                DB_EVENT_TYPE + "INTEGER, " + DB_EVENT_PUBLISHER + "TEXT, " +
                DB_EVENT_HOUR + "TEXT, " + DB_EVENT_POSITION + "BLOB)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade() called with: db = [" + db + "], " +
                "oldVersion = [" + oldVersion + "], newVersion = [" + newVersion + "]");
    }

    /**
     * --------------- OUR METHODS --------------
     **/

    /**
     * This method add an element into our database
     * Receive: an Event object
     * Returns: nothing
     **/
    public void insert(Event event) {
        Log.d(TAG, "insert() called with: event = [" + event + "]");
        try {
            //We gonna write in our database
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            //We put the data in a ContentValues object
            contentValues.put(DB_EVENT_TYPE, event.getType());
            contentValues.put(DB_EVENT_HOUR, event.getHour());
            contentValues.put(DB_EVENT_PUBLISHER, event.getHour());
            contentValues.put(DB_EVENT_POSITION, converter.toBytes(event.getPosition()));
            database.insert(DB_EVENT_tbl, null, contentValues);
            database.close();
        } catch (Exception e) {
            Log.e(TAG, "insert: ", e);
        }
    }

    /**
     * This method remove all data in our table event
     * Receive: Nothing
     * Returns: Nothing
     **/
    public void truncateTable() {
        Log.d(TAG, "truncateTable() called");
        SQLiteDatabase database = this.getWritableDatabase();
        //Making a query
        String query = "DELETE FROM " + DB_EVENT_tbl;
        //Here we execute our query
        database.execSQL(query);
        //We close the connection to our database
        database.close();
    }

    /**
     * This method gets all the data inside of our table events
     * Receive: Nothing
     * Returns: An ArrayList<Event> that contains all the registers
     * inside our table
     **/
    public ArrayList<Event> getAll() {
        Log.d(TAG, "getAll() called");
        //Making a query
        String query = "SELECT " + DB_EVENT_TYPE + ", " +
                DB_EVENT_PUBLISHER + ", " + DB_EVENT_HOUR + ", " +
                DB_EVENT_POSITION + " FROM " + DB_EVENT_tbl;
        //We need to read information inside our database
        SQLiteDatabase database = this.getReadableDatabase();
        //We create an ArrayList that will contain all the registers
        ArrayList<Event> events = new ArrayList<>();
        //This object contains each register
        Cursor cursor = database
                .rawQuery(query, null);
        //Probably we catch an exception
        try {
            //If our cursor contains something we need to move to the first element
            if (cursor.moveToFirst()) {
                //We want to get all the information inside
                do {
                    //We add a new Event object into our ArrayList
                    events.add(new Event(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            (LatLng) converter.getObject(cursor.getBlob(3))));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "getAll: ", e);
        }
        //Closing our database connection
        database.close();
        Log.d(TAG, "getAll() returned: " + events);
        return events;
    }

    /**
     * This method gets just a register of our database
     * Parameter: An String that is an identifier
     * Returns: An Event object
     **/
    public Event getElement(String id) {
        Log.d(TAG, "getElement() called with: id = [" + id + "]");
        //Making a query
        String query = "SELECT " + DB_EVENT_TYPE + ", " +
                DB_EVENT_PUBLISHER + ", " + DB_EVENT_HOUR + ", " +
                DB_EVENT_POSITION + " FROM " + DB_EVENT_tbl + " WHERE ";
        SQLiteDatabase database = this.getReadableDatabase();
        //Cursor cursor = database.query(DB_EVENT_tbl, new String[]{DB_EVENT_TYPE,
        //      DB_EVENT_PUBLISHER, DB_EVENT_HOUR, DB_EVENT_POSITION},"",new);
        return null;
    }
}
