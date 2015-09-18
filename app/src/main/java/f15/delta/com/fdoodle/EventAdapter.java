package f15.delta.com.fdoodle;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

/**
 * Created by Bharath on 17-Sep-15.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {


    List<ClusterPage.Event> events;

    Context context;
    CustomItemClickListener listener;




    EventAdapter(List<ClusterPage.Event> events){

        this.events = events;


    }



    public static class EventViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView eventname;


        EventViewHolder(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);
            eventname = (TextView) itemView.findViewById(R.id.cluster_name);




        }




    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cluster_layout, viewGroup, false);
        final EventViewHolder pvh = new EventViewHolder(v);

        return pvh;
    }


    @Override
    public void onBindViewHolder(EventViewHolder clusterViewHolder, int i) {
        String name = events.get(i).name;
        String temp2="";
        if (name.contains("_")) {
            // Split it.
            String[] temp = name.split("_");
           for(int j=0;j<temp.length;j++) {
               String temp3 = temp[j].substring(0,1).toUpperCase() + temp[j].substring(1);
               temp2 = temp2 + temp3 + " ";
           }

        }else temp2=name.substring(0,1).toUpperCase() + name.substring(1);

        clusterViewHolder.eventname.setTypeface(Utilities.typeface);
        clusterViewHolder.eventname.setText(temp2);


    }

    @Override
    public int getItemCount() {
        return events.size();
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}


