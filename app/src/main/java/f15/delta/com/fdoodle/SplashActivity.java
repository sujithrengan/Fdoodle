
package f15.delta.com.fdoodle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SplashActivity extends Activity {
    int screen_width;
    int screen_height;
    TextView bm=null;
    SharedPreferences prefs;
    String o="#BreakTheMonotony";
    Handler myHandler;
    Runnable r=new Runnable() {
        @Override
        public void run() {

            Log.e("state5",String.valueOf(Utilities.status));
            Intent i;
            if(Utilities.status==1) {
                i= new Intent(SplashActivity.this, Hscreen.class);

            }

            else
            {
                i= new Intent(SplashActivity.this, LoginActivity.class);
            }
            startActivity(i);
            finish();

        }
    };


    public void state2(final LinearLayout l2)
    {
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                l2.setBackgroundResource(R.drawable.logo2);
                state3(l2);
            }
        }, 500);
    }
    public void state3(final LinearLayout l2)
    {
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                l2.setBackgroundResource(R.drawable.logoin);

                state4();

            }
        }, 400);
    }


    public void Startmonotomy(final int i) {

        //Bitmaphandle.loadBitmap(this.getResources().getIdentifier("f"+String.valueOf(i+1), "drawable",this.getPackageName()),f[i]);

        String s="";
        int t=0;
        while(++t<17-(i%17))s+=" ";
        bm.setText(o.substring((16-(i%17)),17));
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (i<50) Startmonotomy((i + 1));
                else
                    state5();
            }
        }, 300-15*(i%16));

    }

    public void state4()
    {
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RelativeLayout r=(RelativeLayout)findViewById(R.id.loadingPanel);
                r.setVisibility(View.VISIBLE);
                Startmonotomy(0);

            }
        }, 250);
    }
    public void state5()
    {
        myHandler.postDelayed(r, 1000);
    }


    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screen_height = displaymetrics.heightPixels;
        screen_width = displaymetrics.widthPixels;
        myHandler = new Handler();
        bm=(TextView)findViewById(R.id.bmtext);

        Typeface f = Typeface.createFromAsset(bm.getContext().getAssets(),
                "fonts/gnu.ttf");
        bm.setTypeface(f);
        //Cache
/*
        Bitmaphandle.maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);


        // Use 1/8th of the available memory for this memory cache.
        Bitmaphandle.cacheSize = Bitmaphandle.maxMemory / 8;

        Log.e("ll",String.valueOf(Bitmaphandle.maxMemory));
        Log.e("ll",String.valueOf(Bitmaphandle.cacheSize));

       Bitmaphandle.mMemoryCache = new LruCache<String, Bitmap>(Bitmaphandle.cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };



        BitmapWorkerTask task = new BitmapWorkerTask();
        BitmapWorkerTask task2 = new BitmapWorkerTask();
        BitmapWorkerTask task3 = new BitmapWorkerTask();
        BitmapWorkerTask task4 = new BitmapWorkerTask();
        BitmapWorkerTask task5 = new BitmapWorkerTask();
        BitmapWorkerTask task6 = new BitmapWorkerTask();
        task.execute(R.drawable.f1);
        task3.execute(R.drawable.f2);
        task2.execute(R.drawable.f3);
        task4.execute(R.drawable.f4);
        task5.execute(R.drawable.f5);
        task6.execute(R.drawable.f6);

*/

        new Utilstask().execute();

        StartAnimations();

    }

    private void updateUtils() {

        prefs = getSharedPreferences("LogInPrefs", Context.MODE_PRIVATE);
        Utilities.status = prefs.getInt("Logged_in",0);
        Utilities.f_id = prefs.getString("f_id", "");
        Utilities.f_pass=prefs.getString("f_pass","");
        Utilities.f_email=prefs.getString("f_email","");

        if(Utilities.status!=0)
        Profile.setProfile(Integer.valueOf(Utilities.f_id),prefs.getString("f_name",""),Utilities.f_email,prefs.getString("f_fullname",""));

    }

    class Utilstask extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new Apicalls().execute();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Sharedprefs-content-Utils

            updateUtils();
            //SharedPrefs-Profile Class

            //JSON FILE-EventListdesc-Event objects


            return null;
        }
    }
    class Apicalls extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //bm.setText(o);
            //state5();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Profile-state-api

            //Events-api

            //Events-description-once

            //
            return null;
        }
    }
    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Log.e("ll","taskdone");
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            final Bitmap bitmap = Bitmaphandle.decodeSampledBitmapFromResource(
                    getResources(), params[0], screen_width, screen_height);
            if(bitmap!=null)
                Log.e("ll",String.valueOf(params[0])+"---notnull in decode");

            Bitmaphandle.addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);

            if(Bitmaphandle.getBitmapFromMemCache(String.valueOf(params[0]))!=null)
                Log.e("ll",String.valueOf(params[0])+"---notnull in cache");
            return bitmap;
        }

    }
    @Override
    public void onBackPressed() {
        myHandler.removeCallbacks(r);

        super.onBackPressed();
    }

    private void StartAnimations() {
        final ImageView ivl = (ImageView) findViewById(R.id.logol);
        final ImageView ivr = (ImageView) findViewById(R.id.logor);
        final LinearLayout l=(LinearLayout)findViewById(R.id.logo);
        final LinearLayout l2=(LinearLayout)findViewById(R.id.logoin);

        //Animation anim = AnimationUtils.loadAnimation(this, R.anim.translater);
        //Animation animr = AnimationUtils.loadAnimation(this, R.anim.translate);
        //anim.reset();

        final Animation animation = new TranslateAnimation((screen_width)/2-screen_width/10,0,0, 0);
        animation.setDuration(1500);
        animation.setInterpolator(new BounceInterpolator());

        final Animation animationl = new TranslateAnimation( -((screen_width)/2-screen_width/10),0,0, 0);
        animationl.setDuration(1500);
        animationl.setInterpolator(new BounceInterpolator());
        final Animation anim = new ScaleAnimation(
                0.0125f, 1f, // Start and end values for the X axis scaling
                1f, 1f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF,0f); // Pivot point of Y scaling
        anim.setDuration(1500);
        anim.setInterpolator(new BounceInterpolator());
        //anim.setFillAfter(true); // Needed to keep the result of the animation


       anim.setAnimationListener(new Animation.AnimationListener() {
           @Override
           public void onAnimationStart(Animation animation) {

           }

           @Override
           public void onAnimationEnd(Animation animation) {


               myHandler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       l2.setBackgroundResource(R.drawable.logo1);
                       state2(l2);
                   }
               }, 200);


           }

           @Override
           public void onAnimationRepeat(Animation animation) {

           }
       });



        //animr.reset();

        ivl.clearAnimation();
        ivr.clearAnimation();


        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivl.setVisibility(View.VISIBLE);
                ivr.setVisibility(View.VISIBLE);
                l.setVisibility(View.VISIBLE);
                ivl.startAnimation(animation);
                ivr.startAnimation(animationl);
                l.startAnimation(anim);
            }
        }, 700);



    }

}