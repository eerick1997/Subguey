package UIElements;


import android.app.AlertDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.erick.adooproject.R;

public class EventsReports {
    private static final String TAG = "EventsReports.java";
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
            Button BTN_crime = view.findViewById(R.id.event_accident);
            Button BTN_failure = view.findViewById(R.id.event_failure);
            Button BTN_natural = view.findViewById(R.id.event_natural);
            Button BTN_by_users = view.findViewById(R.id.event_by_users);

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
