package DataBases.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    //Vatiables
    private Context context;
    private Converter converter = new Converter();

    //Constructor
    public DBEvents(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_EVENTS_nameDB, null, 1);
        Log.d(TAG, "DBEvents() called with: context = [" + context + "], name = [" + name + "], " +
                "factory = [" + factory + "], version = [" + version + "]");
        this.context = context;
    }

    /**
     * --------------- ABSTRACT METHODS ---------------
     **/
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate() called with: db = [" + db + "]");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade() called with: db = [" + db + "], " +
                "oldVersion = [" + oldVersion + "], newVersion = [" + newVersion + "]");
    }

    /**
     * --------------- OUR METHODS --------------
     **/

    public void insert(Event event) {
        Log.d(TAG, "insert() called with: event = [" + event + "]");
        try {
            //We gonna write in our database
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            //We put the id;
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
}
