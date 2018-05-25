package UIElements;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class ChangeStyle {

    //Constants
    private static final String TAG = "ChangeStyle.java";
    //Variables
    private Activity activity;

    public ChangeStyle(Activity activity) {
        Log.d(TAG, "ChangeStyle() called with: activity = [" + activity + "]");
        this.activity = activity;
    }

    /**
     * This method changes the color of our collapsing toolbar
     * Receive:
     * color = An String that contains the line format
     * Returns: Nothing
     **/
    public void setColorCollapsingToolbar(String color, CollapsingToolbarLayout collapsingT) {
        Log.d(TAG, "setColorCollapsingToolbar() called with:" +
                "color = [" + color + "], " +
                "collapsingT = [" + collapsingT + "]");
        collapsingT.setContentScrimColor(Color.parseColor(getColor(color)));
    }

    /**
     *
     * **/
    public void setColorWindow(String color, Window window) {
        Log.d(TAG, "setColorWindow() called with:" +
                "color = context.getString(R.string.LM1); color = [" + color + "], " +
                "window = [" + window + "]");
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor(getColor(color)));
    }

    public void setColorToolbar(String color, Toolbar toolbar) {
        Log.d(TAG, "setColorToolbar() called with: color = [" + color + "], toolbar = [" + toolbar + "]");
        toolbar.setBackgroundColor(Color.parseColor(getColor(color)));
    }

    public String getColor(String ID) {
        Log.d(TAG, "getColor() called with: ID = [" + ID + "]");
        int color = activity.getResources()
                .getIdentifier(ID, "string", activity.getPackageName());
        Log.i(TAG, "getColor: color " + color);
        Log.d(TAG, "getColor() returned: " + activity.getString(color));
        return activity.getString(color);
    }


}
