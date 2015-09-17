package f15.delta.com.fdoodle;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bharath on 17-Sep-15.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {


    List<EventsActivity.Event> events;
    ArrayList<Integer> colours=new ArrayList<Integer>();
    Context context;
    //CustomItemClickListener listener;

Typeface t;


    EventAdapter(List<EventsActivity.Event> events,Context context,ArrayList<Integer> colours){

        this.events = events;
        this.context=context;
        t= Typeface.createFromAsset(context.getAssets(), "fonts/gnu.ttf");
        this.colours=colours;


    }



    public static class EventViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout r;
        CardView cv;
        TextView eventname;


        EventViewHolder(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);
            eventname = (TextView) itemView.findViewById(R.id.cluster_name);

            r=(RelativeLayout)itemView.findViewById(R.id.rl);




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
        clusterViewHolder.eventname.setText(events.get(i).name);
        clusterViewHolder.eventname.setTypeface(t);
        clusterViewHolder.r.setBackgroundColor(colours.get(i%6));
        clusterViewHolder.r.setAlpha(100f);

    }

    @Override
    public int getItemCount() {
        return events.size();
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}


