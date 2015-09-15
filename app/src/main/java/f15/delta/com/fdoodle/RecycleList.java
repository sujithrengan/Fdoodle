package f15.delta.com.fdoodle;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Lenovo on 9/8/2015.
 */
public class RecycleList extends RecyclerView.Adapter<RecycleList.CustomViewHolder> {
    public final Context context;
    public final String[][] present;
    //private final String[] location;
    public final int[][] time;
    //private final String[] cate;
    public final String[] Number;
    public final String[][] register;
    public final int m;

    Calendar timenow=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));

    int n=3,a,b,c,d;

    public RecycleList(Context context, String[][] present, int[][] time, int o,/*String[] location,String[] cate,*/String[] Number, String[][] register, int m) {

        this.context = context;
        this.present = present;
        //this.location=location;
        this.time=time;
        //this.cate=cate;
        this.Number=Number;
        this.register=register;
        this.m=m;
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView Event,Time,Location,Cate,text;
        public RelativeLayout lay;

        public CustomViewHolder(View view) {
            super(view);

            this.Event = (TextView) view.findViewById(R.id.Event);
            this.Time = (TextView) view.findViewById(R.id.Time);
            this.Location=(TextView) view.findViewById(R.id.Location);
            this.Cate = (TextView) view.findViewById(R.id.Cate);
            this.lay=(RelativeLayout)itemView.findViewById(R.id.singlelistlayout);
            this.text=(TextView)view.findViewById(R.id.textView);
            //Event.setBackground(@color/festember_orange);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        int position=i;
        for(int j=0;j<m;j++) {
            if(present[position].equals(register[j])) {
                //Toast.makeText(customViewHolder.itemView.getContext(), "Position: ", Toast.LENGTH_LONG).show();
               // Toast.makeText(getApplicationContext(), "Entered choice", Toast.LENGTH_LONG).show();
                customViewHolder.lay.setBackgroundColor(Color.parseColor("#80ff6600"));//rowView.setBackgroundColor(Color.GREEN);//((ToggleButton) view).
                customViewHolder.Event.setTextColor(Color.WHITE);
                customViewHolder.Time.setTextColor(Color.WHITE);
                customViewHolder.Location.setTextColor(Color.WHITE);
                customViewHolder.Cate.setTextColor(Color.WHITE);
                customViewHolder.text.setBackgroundColor(Color.parseColor("#86ff4400"));
                break;

            }
        }

        Calendar time5 = new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
        time5.set(timenow.get(Calendar.YEAR), 8, time[position][0], time[position][1], time[position][2]);
        Calendar time6 = new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
        time6.set(timenow.get(Calendar.YEAR), 8, time[position][3], time[position][4], time[position][5]);
        //TextView txtTitle = (TextView) rowView.findViewById(R.id.Event);
        customViewHolder.Event.setText(present[position][0]);
        //txtTitle = (TextView) rowView.findViewById(R.id.Location);
        customViewHolder.Location.setText(present[position][1]);
        //txtTitle = (TextView) rowView.findViewById(R.id.Cate);
        customViewHolder.Cate.setText(present[position][2]);
        //txtTitle = (TextView) rowView.findViewById(R.id.Time);

        if (time5.after(timenow)) {
            if ((timenow.get(Calendar.MINUTE) <= time[position][2])) {
                a = (-timenow.get(Calendar.HOUR_OF_DAY) + time[position][1]);
                b = (time[position][2] - timenow.get(Calendar.MINUTE));

            } else {
                a = (-timenow.get(Calendar.HOUR_OF_DAY) + time[position][1] - 1);
                b = (60 - timenow.get(Calendar.MINUTE) + time[position][2]);

            }
            if ((timenow.get(Calendar.HOUR) > time[position][1])) {
                a+= 24;
                //b = (time[position][2] - timenow.get(Calendar.MINUTE));
            }

            customViewHolder.Time.setText("begins in " + a/*timenow.get(Calendar.YEAR)*/ + " hours " + b + " mins ");
        } else {
            if ((timenow.get(Calendar.MINUTE) <= time[position][5])) {
                c = (-timenow.get(Calendar.HOUR_OF_DAY) + time[position][4]);
                d = (time[position][5] - timenow.get(Calendar.MINUTE));
            } else {
                c = (-timenow.get(Calendar.HOUR_OF_DAY) + time[position][4] - 1);
                d = (60 - timenow.get(Calendar.MINUTE) + time[position][5]);
            }
            if ((timenow.get(Calendar.HOUR) > time[position][4])) {
                c+= 24;
                //b = (time[position][2] - timenow.get(Calendar.MINUTE));
            }
            customViewHolder.Time.setText("ends in " + c/*timenow.get(Calendar.YEAR)*/ + " hours " + d + " mins ");
        }
    }

    @Override
    public int getItemCount() {
        return (null != Number ? Number.length : 0);
    }
}