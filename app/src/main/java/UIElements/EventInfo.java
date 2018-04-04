package UIElements;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.erick.adooproject.R;

/**This class contains the code to show a custom alert dialog
 * which show us information about an event, the information provided is:
 * Who publish it
 * When (Hour)
 * **/
public class EventInfo {

    //Constants
    private static final String TAG = "EventInfo.java";
    //Variables
    private Context context;

    //Constructor
    public EventInfo(Context context){
        //Initializing our global variable context
        this.context = context;
    }

    /**This method show an customized alert dialog, so we need some data
     * that we go to use to display information, (Probably we can use an
     * Event object instead all this parameters)**/
    public void showDialog(){
        //As we said in the description we gonna use an Alert Dialog
        final AlertDialog alertDialog;
        //Probably we catch an exception
        try{
            //We need to inflate a layout to customize an Alert Dialog
            LayoutInflater inflater = LayoutInflater.from(context);
            /**Method inflate(here_we_have_the_name_of_the_layout_to_inflate, view_group)**/
            View view = inflater.inflate(R.layout.dialog_event_info, null);
            //We make an instance of an alertDialog
            alertDialog = new AlertDialog.Builder(context).create();
            TextView txt_title = (TextView)view.findViewById(R.id.TXT_event_info_title);
            TextView txt_publisher = (TextView)view.findViewById(R.id.TXT_event_info_publisher);
            RatingBar ratingBar = (RatingBar)view.findViewById(R.id.rating_event_info_user);
            ratingBar.setRating(3.5f);
            /**
             *
             * Here we get each element that we want to display
             *
             * **/

            //We add all the elements in the view object in the AlertDialog
            alertDialog.setView(view);
            alertDialog.show();
        } catch (Exception e) {
            Log.e(TAG, "showDialog: an Exception has occurred ", e);
        }
    }
}
