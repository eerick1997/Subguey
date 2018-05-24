package UIElements;


import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.erick.adooproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Objects.Event;

import static DataBases.Firebase.FirebaseReferences.DB_REFERENCE;
import static DataBases.Firebase.FirebaseReferences.EVENT_REFERENCE;

public class EventsReports {
    //Constants
    private static final String TAG = "EventsReports.java";
    private static final int ACCIDENT = 0;
    private static final int CRIME = 1;
    private static final int FAILURE = 2;
    private static final int NATURAL = 3;
    private static final int BY_USER = 4;
    //Variables
    private Context context;
    private Event event;

    //Constructor
    public EventsReports(Context context) {
        Log.d(TAG, "EventsReports() called with: context = [" + context + "]");
        this.context = context;
    }

    //This method shows the alert dialog in display
    public void showDialog(final Event event) {
        Log.d(TAG, "showDialog() called");
        // Log.d(TAG, "showDialog() called with: event = [" + event + "]");
        try {
            //This object contains information about an event
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.activity_events, null);

            final AlertDialog alertDialog = new AlertDialog.Builder(context).create();

            Button BTN_accident = view.findViewById(R.id.event_accident);
            BTN_accident.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: ACCIDENT");
                    event.setType(ACCIDENT);
                    sendEvent(event);
                }
            });

            Button BTN_crime = view.findViewById(R.id.event_crime);
            BTN_crime.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: CRIME");
                    //Event event = new Event(CRIME, "Erick", "12:00", new LatLng(-1.342432, 1.3423));
                    event.setType(CRIME);
                    sendEvent(event);
                }
            });

            Button BTN_failure = view.findViewById(R.id.event_failure);
            BTN_failure.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: FAILURE");
                    //Event event = new Event(FAILURE, "Erick", "12:00", new LatLng(-1.342432, 1.3423));
                    event.setType(FAILURE);
                    sendEvent(event);
                }
            });

            Button BTN_natural = view.findViewById(R.id.event_natural);
            BTN_natural.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: NATURAL");
                    //Event event = new Event(NATURAL, "Erick", "12:00", new LatLng(-1.342432, 1.3423));
                    event.setType(NATURAL);
                    sendEvent(event);
                }
            });

            Button BTN_by_users = view.findViewById(R.id.event_by_users);
            BTN_by_users.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: BY USERS");
                    //Event event = new Event(ACCIDENT, "Erick", "12:00", new LatLng(-1.342432, 1.3423));
                    event.setType(BY_USER);
                    sendEvent(event);
                }
            });

            Button BTN_close = view.findViewById(R.id.BTN_close_events);
            BTN_close.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: CLOSE");
                    alertDialog.dismiss();
                    //sendEvent(event);
                }
            });
            alertDialog.setView(view);
            alertDialog.show();
        } catch (Exception ex) {
            Log.e(TAG, "showDialog: an Exception has occurred ", ex);
        }
    }

    /**
     * This method sends to our remote database using FireBase service
     * Receive: An object type Event
     * returns: nothing
     **/
    private void sendEvent(@NonNull final Event event) {
        Log.d(TAG, "sendEvent() called with: event = [" + event + "]");

        //We make instance of a FirebaseDatabase  object to create our database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //We need to pass the name of our database and
        final DatabaseReference events = database.getReference(DB_REFERENCE)
                .child(EVENT_REFERENCE);
        events.push().setValue(event);
    }

}
