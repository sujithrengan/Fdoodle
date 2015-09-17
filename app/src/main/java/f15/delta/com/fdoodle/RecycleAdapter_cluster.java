package f15.delta.com.fdoodle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
public class RecycleAdapter_cluster extends RecyclerView.Adapter<RecycleAdapter_cluster.ClusterViewHolder> {


        List<EventsActivity.Cluster> clusters;
    ArrayList<Integer> colours=new ArrayList<Integer>();

        Context context;
    Typeface t;
    //CustomItemClickListener listener;




    RecycleAdapter_cluster(List<EventsActivity.Cluster> clusters,Context context,ArrayList<Integer> colours){

        this.clusters = clusters;
        t=Typeface.createFromAsset(context.getAssets(),"fonts/gnu.ttf");
        this.colours=colours;
        this.context=context;



    }



        public static class ClusterViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView clustername;
            RelativeLayout r;
            //SeeThroughTextView st;



            ClusterViewHolder(View itemView) {
                super(itemView);

                cv = (CardView) itemView.findViewById(R.id.cv);
                clustername = (TextView) itemView.findViewById(R.id.cluster_name);
                r=(RelativeLayout)itemView.findViewById(R.id.rl);




            }




        }

    @Override
    public ClusterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cluster_layout, viewGroup, false);
        final ClusterViewHolder pvh = new ClusterViewHolder(v);

        return pvh;
    }


    @Override
    public void onBindViewHolder(ClusterViewHolder clusterViewHolder, int i) {
        clusterViewHolder.clustername.setText(clusters.get(i).name);


        clusterViewHolder.clustername.setTypeface(t);
       // clusterViewHolder.cv.setCardBackgroundColor(colours.get(i));
        clusterViewHolder.r.setBackgroundColor(colours.get(i%6));
        clusterViewHolder.r.setAlpha(100f);

        /*
        if(i==0||i==3||i==4||i==7||i==8)
        clusterViewHolder.cv.getLayoutParams().height=300;
        else
            clusterViewHolder.cv.getLayoutParams().height=200;

*/



    }

    @Override
    public int getItemCount() {
        return clusters.size();
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }




}


