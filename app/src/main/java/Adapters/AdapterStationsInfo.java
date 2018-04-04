package Adapters;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erick.adooproject.R;

import java.util.ArrayList;

import Objects.ElementAdapter;

public class AdapterStationsInfo extends Adapter<AdapterStationsInfo.StationsInfoViewHolder> implements View.OnClickListener{


    //Variables
    private View.OnClickListener listener;
    private ArrayList<ElementAdapter> elementsAdapter;

    public static class StationsInfoViewHolder extends RecyclerView.ViewHolder{

        //Constants
        private static final String TAG = "AdapterStationsInfo.java";
        //Variables
        private TextView TXT_title, TXT_Description;
        private ImageView IV_Icon;

        public StationsInfoViewHolder(View view){
            super(view);
            TXT_title = (TextView)view.findViewById(R.id.station_info_element_title);
            TXT_Description = (TextView)view.findViewById(R.id.station_info_element_description);
            IV_Icon = (ImageView)view.findViewById(R.id.station_info_element_image);
        }

        //This method sets text in our TextView;
        @SuppressLint("LongLogTag")
        public void bindName(ElementAdapter elementsAdapter){
            Log.d(TAG, "bindName() called with: elementsAdapter = [" + elementsAdapter + "]");
            TXT_title.setText(elementsAdapter.getTitle());
            TXT_Description.setText(elementsAdapter.getDescription());
            IV_Icon.setBackgroundResource(R.mipmap.ic_women_module);
        }
    }

    //Constructor
    public AdapterStationsInfo(ArrayList<ElementAdapter> elementsAdapter){
        this.elementsAdapter = elementsAdapter;
    }

    @Override
    public StationsInfoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.station_info_title, viewGroup, false);
        itemView.setOnClickListener(this);
        StationsInfoViewHolder stationsViewHolder = new StationsInfoViewHolder(itemView);
        return stationsViewHolder;
    }

    @Override
    public void onBindViewHolder(StationsInfoViewHolder viewHolder, int position) {

        ElementAdapter elementAdapter = elementsAdapter.get(position);
        viewHolder.bindName(elementAdapter);
    }

    @Override
    public int getItemCount() {
        return elementsAdapter.size();
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public void setOnClickLister(View.OnClickListener lister){
        this.listener = lister;
    }
}
