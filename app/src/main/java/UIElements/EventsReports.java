package UIElements;


import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.erick.adooproject.R;

import Objects.Event;

public class EventsReports {
    //Constants
    private static final String TAG = "EventsReports.java";
    private static final int ACCIDENT = 0;
    private static final int CRIME = 1;
    private static final int FAILURE = 2;
    private static final int NATURAL = 3;
    private static final int BY_USER = 4;
    //Variable
    private Context context;

    public EventsReports(Context context) {
        this.context = context;
    }

    public void showDialog() {
        final AlertDialog alertDialog;
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.activity_events,null);

            alertDialog = new AlertDialog.Builder(context).create();

            Button BTN_accident = view.findViewById(R.id.event_accident);
            Button BTN_crime = view.findViewById(R.id.event_crime);
            Button BTN_failure = view.findViewById(R.id.event_failure);
            Button BTN_natural = view.findViewById(R.id.event_natural);
            Button BTN_by_users = view.findViewById(R.id.event_by_users);

            Button BTN_close = view.findViewById(R.id.BTN_close_events);

            BTN_accident.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            BTN_crime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            BTN_failure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            BTN_natural.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            BTN_by_users.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            BTN_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Button pressed", Snackbar.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            });

            alertDialog.setView(view);
            alertDialog.show();
        }catch(Exception ex){
            Log.e(TAG, "showDialog: an Exception has occurred ", ex);
        }
    }

    /**
     * This method sends to our remote database using FireBase service
     * Receive: An object type Event
     * returns: nothing
     **/
    private void sendEvent(@NonNull final Event event) {

    }

}
