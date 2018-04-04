package Adapters;


import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erick.adooproject.R;
import com.example.erick.adooproject.activity_station_info;

import java.util.ArrayList;

import Objects.Station;

public class AdapterStations extends RecyclerView.Adapter<AdapterStations.StationsViewHolder>{

    //Constants
    private static final String TAG = "AdapterStations.java";

    //Variables
    private View.OnClickListener listener;
    private ArrayList<Station> stations;

    public class StationsViewHolder extends RecyclerView.ViewHolder{
        //Constants
        private static final String TAG = "StationViewHolder.java";
        //Variables
        private TextView TXT_Station;
        private ImageView IMG_Station;

        //Constructor
        public StationsViewHolder(View view){
            super(view);
            //Here we get interface elements
            TXT_Station = (TextView)view.findViewById(R.id.station_element_text_view);
            IMG_Station = (ImageView)view.findViewById(R.id.station_element_image_view);
        }

        //Here we put data in the interface
        public void bindName(Station station){
            Log.d(TAG, "bindName() called with: station = [" + station + "]");
            TXT_Station.setText(station.getName());
            //This need to be dynamic
            IMG_Station.setBackgroundResource(R.mipmap.ic_launcher);
        }
    }

    //Constructor super class
    public AdapterStations(ArrayList<Station> stations){
        this.stations = stations;
    }

    @Override
    public StationsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.station_element, viewGroup, false);
        StationsViewHolder stationsViewHolder = new StationsViewHolder(itemView);
        return stationsViewHolder;
    }

    @Override
    public void onBindViewHolder(StationsViewHolder viewHolder, int position){
        Log.d(TAG, "onBindViewHolder() called with: viewHolder = [" + viewHolder + "], position = [" + position + "]");
        final Station station = stations.get(position);
        viewHolder.bindName(station);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), activity_station_info.class);
                intent.putExtra("Station", (Parcelable) station);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return stations.size();
    }

}
