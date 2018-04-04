package DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import Objects.Line;

import static DataBases.Utilities.*;

public class DBLines extends SQLiteOpenHelper{

    //Variables
    private Context context;
    private static final String TAG = "DBLines.java";

    //Constructor
    public DBLines(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_LINES_nameDB, null, 1);
        Log.d(TAG, "DBLines() called with: context = [" + context + "], name = [" + name + "], " +
                "factory = [" + factory + "], version = [" + version + "]");
        this.context = context;
    }

    /**insert method.
     * Description: This function insert a register in our database
     * Parameters: A Line object
     * Returns: nothing                                         **/
    public void insert(Line line){
        Log.d(TAG, "insert() called with: line = [" + line + "]");
        try{
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

        } catch (Exception e){
            Log.e(TAG, "insert: ", e);
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate() called with: db = [" + db + "]");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade() called with: db = [" + db + "], " +
                "oldVersion = [" + oldVersion + "], newVersion = [" + newVersion + "]");
    }

}
