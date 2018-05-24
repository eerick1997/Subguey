package UIElements;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
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
                "color = context.getString(R.string.LM1); color = [" + color + "], " +
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

    private String getColor(String ID) {
        Log.d(TAG, "getColor() called with: ID = [" + ID + "]");
        int color = activity.getResources()
                .getIdentifier(ID, "strings", activity.getPackageName());
        Log.d(TAG, "getColor() returned: " + ID);
        return activity.getString(color);
        /**String color = "";
        switch (ID) {
            case "LM1":
                color = context.getString(R.string.LM1);
                break;
            case "LM2":
                color = context.getString(R.string.LM2);
                break;
            case "LM3":
                color = context.getString(R.string.LM3);
                break;
            case "LM4":
                color = context.getString(R.string.LM4);
                break;
            case "LM5":
                color = context.getString(R.string.LM5);
                break;
            case "LM6":
                color = context.getString(R.string.LM6);
                break;
            case "LM7":
                color = context.getString(R.string.LM7);
                break;
            case "LM8":
                color = context.getString(R.string.LM8);
                break;
            case "LM9":
                color = context.getString(R.string.LM9);
                break;
            case "LMA":
                color = context.getString(R.string.LMA);
                break;
            case "LMB":
                color = context.getString(R.string.LMB);
                break;
            case "LM12":
                color = context.getString(R.string.LM12);
                break;
            case "LMB1":
                color = context.getString(R.string.LMB1);
                break;
            case "LMB2":
                color = context.getString(R.string.LMB2);
                break;
            case "LMB3":
                color = context.getString(R.string.LMB3);
                break;
            case "LMB4":
                color = context.getString(R.string.LMB4);
                break;
            case "LMB5":
                color = context.getString(R.string.LMB5);
                break;
            case "LMB6":
                color = context.getString(R.string.LMB6);
                break;
        }
         return color;**/
    }


}
