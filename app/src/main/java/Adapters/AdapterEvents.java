package Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.*;

import com.example.erick.adooproject.Events;
import com.example.erick.adooproject.R;

import java.util.ArrayList;

import Objects.Event;

public class AdapterEvents extends RecyclerView.Adapter<AdapterEvents.ViewHolderEvents> {
    /*
    * This adapter is going to provide information
    * the activity XML activity_events: In other words,
    * about of graphics elements that we'll have.
    * */

    ArrayList<Events> events;

    public AdapterEvents(ArrayList<Events> events) {
        this.events = events;

    }

    @Override
    public ViewHolderEvents onCreateViewHolder(ViewGroup parent, int viewType) {
        /*
        * We are going to inflame
        * */
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_events,null,false);
        return new ViewHolderEvents(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderEvents holder, int position) {
        holder.desc_event.setText(events.get(position).getDesc_event());
        holder.image_event.setImageResource(events.get(position).getImage_event());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolderEvents extends RecyclerView.ViewHolder {
        TextView desc_event;
        ImageView image_event;

        public ViewHolderEvents(View itemView) {
            super(itemView);
            desc_event = (TextView)itemView.findViewById(R.id.txt_event);
            image_event = (ImageView) itemView.findViewById(R.id.image_event);
        }
    }
}
