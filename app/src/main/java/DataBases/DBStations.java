package DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Objects.Station;

import static DataBases.Utilities.*;

public class DBStations extends SQLiteOpenHelper{

    //Constants
    private static final String TAG = "DBStations.java";
    //Variables
    private Context context;
    private Converter converter;
    public DBStations(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_STATIONS_nameDB, null, 1);
        Log.d(TAG, "DBStations() called with: context = [" + context + "], name = [" + name + "], " +
                "factory = [" + factory + "], version = [" + version + "]");
        this.context = context;
    }

    /**insert method.
     * Description: This function insert a register in our database
     * Parameters: A Station object
     * Returns: nothing                                         **/
    public void insert(Station station){
        Log.d(TAG, "insert() called with: station = [" + station + "]");
        try{
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DB_STATIONS_ID,
                    station.getName()+station.getLine());

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

            database.insert(DB_STATIONS_tbl,null, contentValues);
            database.close();
        } catch (Exception e){
            Log.e(TAG, "insert: ", e);
        }
    }

    public void truncateTable(){
        Log.d(TAG, "truncateTable: ");
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "DELETE FROM " + DB_STATIONS_tbl;
        database.execSQL(query);
        database.close();
    }

   /** public ArrayList<Station> getAll(){
        Log.d(TAG, "getAll: ");
        String query = "SELECT FROM";
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Station> stations = new ArrayList<>();
        //Cursor cursor = database
     //           .rawQuery("");
    }**/

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate() called with: db = [" + db + "]");
        String query = "CREATE TABLE " + DB_STATIONS_tbl + "(" +
                DB_STATIONS_ID + "  ";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade() called with: db = [" + db + "], " +
                "oldVersion = [" + oldVersion + "], newVersion = [" + newVersion + "]");
    }
}
