package UIElements;


import android.app.AlertDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.erick.adooproject.R;

import Objects.Event;

public class EventsReports {
    private static final String TAG = "EventsReports.java";
    private Context context;

    public EventsReports(Context context) {
        this.context = context;
    }

    public void showDialog(Event event){
        final AlertDialog alertDialog;
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.activity_events,null);

            alertDialog = new AlertDialog.Builder(context).create();

            TextView txt_event_natural = (TextView) view.findViewById(R.id.event_natural);
            TextView txt_event_accident = (TextView) view.findViewById(R.id.event_accident);
            TextView txt_event_by_user = (TextView) view.findViewById(R.id.event_by_users);
            TextView txt_event_crime = (TextView) view.findViewById(R.id.event_crime);
            TextView txt_event_failure = (TextView) view.findViewById(R.id.event_failure);

            Button btn_cerrar = (Button)view.findViewById(R.id.BTN_close_events);

            btn_cerrar.setOnClickListener(new View.OnClickListener() {
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
}
