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

import Objects.Event;

public class EventsReports implements OnClickListener {
    //Constants
    private static final String TAG = "EventsReports.java";
    private static final int ACCIDENT = 0;
    private static final int CRIME = 1;
    private static final int FAILURE = 2;
    private static final int NATURAL = 3;
    private static final int BY_USER = 4;
    //Variables
    private Context context;
    private Button BTN_accident, BTN_crime, BTN_failure,
            BTN_natural, BTN_by_users, BTN_close;
    private AlertDialog alertDialog;
    private Event event;

    public EventsReports(Context context) {
        this.context = context;
    }

    public void showDialog(Event event) {
        try {
            //This object contains information about an event
            this.event = event;
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.activity_events,null);

            alertDialog = new AlertDialog.Builder(context).create();

            BTN_accident = view.findViewById(R.id.event_accident);
            BTN_accident.setOnClickListener(this);
            BTN_crime = view.findViewById(R.id.event_crime);
            BTN_crime.setOnClickListener(this);
            BTN_failure = view.findViewById(R.id.event_failure);
            BTN_failure.setOnClickListener(this);
            BTN_natural = view.findViewById(R.id.event_natural);
            BTN_natural.setOnClickListener(this);
            BTN_by_users = view.findViewById(R.id.event_by_users);
            BTN_by_users.setOnClickListener(this);
            BTN_close = view.findViewById(R.id.BTN_close_events);
            BTN_close.setOnClickListener(this);

            alertDialog.setView(view);
            alertDialog.show();
        }catch(Exception ex){
            Log.e(TAG, "showDialog: an Exception has occurred ", ex);
        }
    }

    private void dismissDialog() {
        if (!alertDialog.isShowing())
            alertDialog.dismiss();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.event_accident:
                event.setType(ACCIDENT);
                break;

            case R.id.event_crime:
                event.setType(CRIME);
                break;

            case R.id.event_failure:
                event.setType(FAILURE);
                break;

            case R.id.event_natural:
                break;

            case R.id.event_by_users:
                break;

            case R.id.BTN_close_events:
                dismissDialog();
                break;
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
