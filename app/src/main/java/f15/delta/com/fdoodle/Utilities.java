package f15.delta.com.fdoodle;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;

import java.util.ArrayList;

public class Utilities
{


    public static int screen_width;
    public static int screen_height;



    public static String url_events="https://api.festember.com/events/list";
    public static String url_eventsdesc="https://api.festember.com/events/desclist";
    public static String url_registerevent="https://";
    public static String url_profile_events="https://api.festember.com/user/getEvents";
    public static String url_raffle="https://festember.com/final15/festember15api/festember-raffle/raffleapi.php";
    public static String url_reg = "https://api.festember.com/user/register";
    public static String url_auth = "https://api.festember.com/user/auth";
    public static String url_details = "https://api.festember.com/user/getDetails";

    public static String f_id="";
    public static String f_pass="";
    public static String f_email="";

//Raffle module
    public static String fb_name;
    public static String fb_id;
    public static int status=0;
    public static int check_desc = 0;

        //Status 0 - Not logged into the app (Festember registration)
        //Status 1 - Logged into the app (Festember registration)
        //Status 2 - Logged into the app + Facebook registration
        //Status 3 - Logged into the app + Facebook registration + Facebook shared #festember
        //Status 4 - Logged into the app + Logged out of FB + 1 coupon received.
        //Status 5 - Logged into the app + Logged out of FB + #festember coupons received.
        //Status 6 - Logged into the app + Facebook registration + Facebook doodle shared
        //Status 7 - Logged into the app + Logged out of FB + Facebook doodle shared.
    public static String[] coupons = new String[11];

//Doodle module
    public static Bitmap doodleBitmap = null;

//QR Code module
    public static String webmail_username="";
    public static String webmail_password="";
    public static SharedPreferences qr_prefs;
    public static SharedPreferences desc_check;
    public static int upcoming_check;
    public static String url_qr = "https://festember.com/final15/festember15api/mobile_tshirt_qr.php";
    public static String url_qr_auth = "https://festember.com/final15/festember15api/mobile_auth.php";
    public static int qr_status=0;

//Events
    public static String event_name;
    public static String event_desc;
    public static Typeface typeface;
    public static int event_id;


   public static ArrayList<Integer> colours=new ArrayList<Integer>();

    public static void initcolours(Context context)
    {
        colours=new ArrayList<Integer>();
        //colours.add(context.getResources().getColor(R.color.ColorUpcoming));
        colours.add(context.getResources().getColor(R.color.ColorRaffle));
        colours.add(context.getResources().getColor(R.color.ColorDoodle));
        colours.add(context.getResources().getColor(R.color.ColorProfile));
        colours.add(context.getResources().getColor(R.color.ColorSchedule));
        colours.add(context.getResources().getColor(R.color.ColorEvents));
    }

}
