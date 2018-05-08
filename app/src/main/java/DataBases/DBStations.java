package DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import Objects.Exit;
import Objects.Service;
import Objects.Station;

import static DataBases.Utilities.DB_STATIONS_ID;
import static DataBases.Utilities.DB_STATIONS_Line;
import static DataBases.Utilities.DB_STATIONS_Name;
import static DataBases.Utilities.DB_STATIONS_exits;
import static DataBases.Utilities.DB_STATIONS_nameDB;
import static DataBases.Utilities.DB_STATIONS_next;
import static DataBases.Utilities.DB_STATIONS_position;
import static DataBases.Utilities.DB_STATIONS_previous;
import static DataBases.Utilities.DB_STATIONS_services;
import static DataBases.Utilities.DB_STATIONS_tbl;

public class DBStations extends SQLiteOpenHelper {

    //Constants
    private static final String TAG = "DBStations.java";

    //Variables
    private Context context;
    private Converter converter;

    public DBStations(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, DB_STATIONS_nameDB, null, 1);
        Log.d(TAG, "DBStations() called with: context = [" + context + "], name = [" + name + "], " +
                "factory = [" + factory + "], version = [" + version + "]");
        //Initializing our variables
        this.context = context;
        //Instancing a converter Object
        converter = new Converter();

    }

    /**
     * insert method.
     * Description: This function insert a register in our database
     * Parameters: A Station object
     * Returns: nothing
     **/
    public void insert(Station station) {
        Log.d(TAG, "insert() called with: station = [" + station + "]");
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DB_STATIONS_ID,
                    station.getName() + station.getLine());

            contentValues.put(DB_STATIONS_Name, station.getName());

            contentValues.put(DB_STATIONS_Line, station.getLine());

            contentValues.put(DB_STATIONS_position,
                    converter.toBytes(station.getPosition()));

            contentValues.put(DB_STATIONS_exits,
                    converter.toBytes(station.getExits()));

            contentValues.put(DB_STATIONS_services,
                    converter.toBytes(station.getServices()));

            contentValues.put(DB_STATIONS_next,
                    converter.toBytes(station.getNext()));

            contentValues.put(DB_STATIONS_previous,
                    converter.toBytes(station.getPrevious()));

            database.insert(DB_STATIONS_tbl, null, contentValues);
            database.close();
        } catch (Exception e) {
            Log.e(TAG, "insert: ", e);
        }
    }

    public void truncateTable() {
        Log.d(TAG, "truncateTable: ");
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "DELETE FROM " + DB_STATIONS_tbl;
        database.execSQL(query);
        database.close();
    }

    /**
     * This method returns all the data in our table
     * stations. This method just return an ArrayList
     * of Station objects
     **/
    public ArrayList<Station> getAll() {
        Log.d(TAG, "getAll: ");
        String query = "SELECT " + DB_STATIONS_ID + ", " +
                DB_STATIONS_Name + ", " +
                DB_STATIONS_Line + ", " +
                DB_STATIONS_position + ", " +
                DB_STATIONS_services + ", " +
                DB_STATIONS_exits + ", " +
                DB_STATIONS_next + ", " +
                DB_STATIONS_previous + " FROM " +
                DB_STATIONS_tbl;

        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Station> stations = new ArrayList<>();
        Cursor cursor = database
                .rawQuery(query, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    stations.add(new Station(cursor.getString(1),
                            cursor.getString(2),
                            (LatLng) converter.getObject(cursor.getBlob(3)),
                            (ArrayList<Service>) converter.getObject(cursor.getBlob(4)),
                            (ArrayList<Exit>) converter.getObject(cursor.getBlob(5)),
                            (ArrayList<LatLng>) converter.getObject(cursor.getBlob(6)),
                            (ArrayList<LatLng>) converter.getObject(cursor.getBlob(7))));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "getAll: ", e);
        }
        database.close();
        return stations;
    }

    private void setMarkers(GoogleMap googleMap) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT " + DB_STATIONS_ID + ", " +
                DB_STATIONS_position + " FROM " +
                DB_STATIONS_tbl;
        Cursor cursor = database.rawQuery(query, null);
        try {
            //If cursor contains something
            if (cursor.moveToFirst()) {
                do {
                    LatLng position = (LatLng) converter.getObject(cursor.getBlob(1));
                    String id = cursor.getString(0);

                    String uri = "@drawable/ic_" + id;
                    int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
                    Drawable drawable = context.getResources().getDrawable(imageResource);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    //Creating a new marker
                    Marker marker = googleMap.addMarker(new MarkerOptions().position(position)
                            .title(id)
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "setMarkers: ", e);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate() called with: db = [" + db + "]");
        String query = "CREATE TABLE " + DB_STATIONS_tbl + "(" +
                DB_STATIONS_ID + " TEXT, " +
                DB_STATIONS_Name + " TEXT, " +
                DB_STATIONS_Line + " TEXT, " +
                DB_STATIONS_position + " BLOB, " +
                DB_STATIONS_services + " BLOB, " +
                DB_STATIONS_exits + " BLOB, " +
                DB_STATIONS_next + " BLOB, " +
                DB_STATIONS_previous + " BLOB)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade() called with: db = [" + db + "], " +
                "oldVersion = [" + oldVersion + "], newVersion = [" + newVersion + "]");
    }
}
