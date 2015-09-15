package f15.delta.com.fdoodle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;




public class UpcomingActivity extends AppCompatActivity {

    /*{"students":[
    {"UserId":12, "Registered":{"cat","cow"},"Following":{"cat","cow","rat"}}
    ,...
    ]}

    {"events":[
    {"Eventname":"cat", "time":{18,18,0,18,21,15}, "location":"barn", "cate":"GL"e}, "description":"adsadgshsrthbrsgn"},
    ....
    ]}
    */



    String[][] events = {{"cat","barn","GL"},
            {"dog","sac","W"},
            {"rat","lhc","GL"},
            {"horse","eee","P"},
            {"chicken","barn","GL"},
            {"rabbit","sac","W"},
            {"dragon","lhc","GL"},
            {"sheep","eee","P"},
            {"snake","oat","P"},
            {"tiger","sac","W"},
            {"monkey","lhc","GL"},
            {"boar","lhc","GL"},
            {"cow","oat","P"}};

    Calendar timeday=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));

    int date=timeday.get(Calendar.DATE),month=8;
    int[][] time = {{date, 10, 0, date, 12, 15},
            {date, 13, 30, date, 18, 45},
            {date, 12, 30, date, 14, 30},
            {date,23,15,(date+1),0,30},
            {date, 20, 0, date, 23, 15},
            {date, 8, 30, date, 10, 45},
            {date, 16, 30, date, 20, 30},
            {date,23,15,(date+1),0,30},
            {date,14,30,date,16,30},
            {date, 16, 30, date, 20, 30},
            {date,19,15,(date+1),0,30},
            {date,17,30,date,21,30},
            {date,11,30,date,13,30}};
    String[] Number;
    String[][] present;
    int[][] prtime;
    String[][] register={events[0],events[4]};
    int[][] rtime={time[0],time[4]};
    String[][] follow={events[0],events[2],events[4]};
    int[][] ftime={time[0],time[2],time[4]};
    int n = events.length,m=register.length,t,p=follow.length,ch=0,catech=0;
    int timelimit=1;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;




    protected void sort(String[][] tevents,  int[][] ttime,  int o){

        Calendar timenow=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
        String[] temp;
        int[] tem;
        prtime=new int[o][6];
        String[][] store=new String[o][3];

        for(int i=0;i<o;i++){

            for(int j=i;j<o-1-i;j++){
                Calendar time1=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
                Calendar time2=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
                time1.set(timenow.get(Calendar.YEAR), 8, ttime[j][0], ttime[j][1], ttime[j][2]);
                time2.set(timenow.get(Calendar.YEAR), 8, ttime[j + 1][0], ttime[j + 1][1], ttime[j + 1][2]);

                if(time1.after(time2)){
                    temp=tevents[j];
                    tevents[j]=tevents[j+1];
                    tevents[j+1]=temp;

                    tem=ttime[j];
                    ttime[j]=ttime[j+1];
                    ttime[j+1]=tem;


                }
            }
        }

        Calendar time3=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
        time3.set(Calendar.YEAR,timenow.get(Calendar.YEAR));
        time3.set(Calendar.MONTH,timenow.get(Calendar.MONTH));
        time3.set(Calendar.DATE,timenow.get(Calendar.DATE));
        time3.set(Calendar.HOUR_OF_DAY,timenow.get(Calendar.HOUR_OF_DAY)+timelimit);
        time3.set(Calendar.MINUTE,timenow.get(Calendar.MINUTE));

        if(time3.get(Calendar.HOUR_OF_DAY)>23){
            time3.set(Calendar.HOUR_OF_DAY,((timenow.get(Calendar.HOUR_OF_DAY))-24));
            time3.set(Calendar.DATE,(timenow.get(Calendar.DATE)+1));
        }

        t=0;

        for(int k=0;k<o;k++) {

            Calendar time4=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
            time4.set(timenow.get(Calendar.YEAR), 8, ttime[k][0], ttime[k][1], ttime[k][2]);

            Calendar time5=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
            time5.set(timenow.get(Calendar.YEAR), 8, ttime[k][3], ttime[k][4], ttime[k][5]);

            if((time4.before(time3))&&timenow.before(time5)){

                switch(catech){

                    case 0:store[t]=tevents[k];
                        prtime[t]=ttime[k];
                        t++;
                        break;

                    case 1:if(tevents[k][2].equals("GL")) {

                        store[t]=tevents[k];
                        prtime[t]=ttime[k];
                        t++;
                    }

                        break;

                    case 2:if(tevents[k][2].equals("W")) {

                        store[t]=tevents[k];
                        prtime[t]=ttime[k];
                        t++;
                    }

                        break;

                    case 3:if(tevents[k][2].equals("P")) {

                        store[t]=tevents[k];
                        prtime[t]=ttime[k];
                        t++;
                    }

                        break;
                }
            }
        }

        present=new String[t][3];
        Number=new String [t];

        for(int q=0;q<t;q++) {

            present[q] = store[q];
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);

        setTitle("Upcoming Events Schedule");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sort(events,  time,  n);

        RecycleList adapter = new
                RecycleList(UpcomingActivity.this, present,prtime,t,Number,register,m);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclelist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        Spinner spinner = (Spinner) findViewById(R.id.recyclespinner);
        String[] items = new String[] { "All", "Registered", "Following" };
        ArrayAdapter<String> spadapter = new ArrayAdapter<String>(
                this, R.layout.spinnerstyle, items);


        spinner.setAdapter(spadapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {

                    case 0:
                        ch = 0;
                        sort(events, time, n);
                        break;

                    case 1:
                        ch = 1;
                        sort(register,  rtime, m);
                        break;

                    case 2:
                        ch = 2;
                        sort(follow, ftime, p);
                        break;
                }

                RecycleList adapter = new
                        RecycleList(UpcomingActivity.this, present, prtime, t, Number,register, m);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

                sort(events,  time, n);
            }


        });

        Spinner spinnertime = (Spinner) findViewById(R.id.recyclespinnertime);
        String[] itemstime = new String[] { "1 hour", "2 hours"};
        ArrayAdapter<String> tiadapter = new ArrayAdapter<String>(
                this, R.layout.spinnerstyle, itemstime);
        spinnertime.setAdapter(tiadapter);
        spinnertime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                switch (position) {

                    case 0:
                        timelimit=1;
                        break;

                    case 1:
                        timelimit=2;
                        break;
                }

                switch(ch) {

                    case 0:sort(events, time, n);
                        break;

                    case 1:sort(register,rtime,m);
                        break;

                    case 2:sort(follow,ftime,p);
                }

                RecycleList adapter = new
                        RecycleList(UpcomingActivity.this, present,prtime,t,Number,register,m);
                mRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

                sort(events, time, n);
            }
        });

        Spinner spinnercate = (Spinner) findViewById(R.id.recyclespinnercate);
        String[] itemscate = new String[] { "All","Guest Lectures", "Workshops", "Proshows"};
        ArrayAdapter<String> caadapter = new ArrayAdapter<String>(
                this, R.layout.spinnerstyle, itemscate);
        spinnercate.setAdapter(caadapter);
        spinnercate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                switch (position) {

                    case 0:
                        catech = 0;
                        break;

                    case 1:
                        catech = 1;
                        break;

                    case 2:
                        catech = 2;
                        break;

                    case 3:
                        catech = 3;
                        break;
                }

                switch (ch) {

                    case 0:
                        sort(events, time, n);
                        break;

                    case 1:
                        sort(register,rtime,m);
                        break;

                    case 2:
                        sort(follow,ftime,p);
                }

                RecycleList adapter = new
                        RecycleList(UpcomingActivity.this, present, prtime, t, Number, register, m);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

                sort(events,time,n);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
