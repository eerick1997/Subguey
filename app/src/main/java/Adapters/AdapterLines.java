package Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erick.adooproject.ActivityStations;
import com.example.erick.adooproject.R;

import java.util.ArrayList;

import Objects.Line;

public class AdapterLines extends Adapter<AdapterLines.LinesViewHolder> {

    //Constants
    private static final String TAG = "Adapterlines.java";
    //Variables
    private View.OnClickListener listener;
    private ArrayList<Line> lines;

    public class LinesViewHolder extends RecyclerView.ViewHolder{

        //Constants
        private static final String TAG = "LinesViewHolder.java";
        //Variables
        private TextView TXT_line;



        public LinesViewHolder(View view){
            super(view);
            //Getting interface elements
            TXT_line = (TextView)view.findViewById(R.id.line_info_text);
        }

        //This method manipulates interface
        public void bindName(Line line){
            Log.d(TAG, "bindName() called with: line = [" + line + "]");
            TXT_line.setText("Linea " + line.getWhichLine());

        }
    }

    //Constructor super class
    public AdapterLines(ArrayList<Line> lines){
        this.lines = lines;
    }

    @Override
    public LinesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.line_info_element, viewGroup, false);
        LinesViewHolder linesViewHolder = new LinesViewHolder(itemView);

        return linesViewHolder;
    }

    @Override
    public void onBindViewHolder(LinesViewHolder viewHolder, final int position){
        Log.d(TAG, "onBindViewHolder() called with: viewHolder = [" + viewHolder + "], position = [" + position + "]");
        final Line line = lines.get(position);
        viewHolder.bindName(line);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityStations.class);
                intent.putParcelableArrayListExtra("Stations", line.getStations());
                intent.putExtra("color", line.getWhichLine());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount(){
        Log.d(TAG, "getItemCount() returned: " + lines.size());
        return lines.size();
    }

}