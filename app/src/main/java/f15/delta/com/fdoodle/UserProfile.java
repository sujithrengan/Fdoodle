package f15.delta.com.fdoodle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserProfile extends ActionBarActivity {

    SharedPreferences prefs=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_profile);


        setData();
    }

    private void setData() {
        Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/gnu.ttf");
        String name = null, F_ID = null, email_ID = null, couponCode = null;
        ArrayList<String> reg_events = new ArrayList<>();
        TextView nameView, fIDView, emailIDView, couponView;
        nameView = (TextView) findViewById(R.id.welcomeText);
        nameView.setTypeface(face);
        fIDView = (TextView) findViewById(R.id.f_id);
        fIDView.setTypeface(face);
        emailIDView = (TextView) findViewById(R.id.email_id);
        emailIDView.setTypeface(face);
        couponView = (TextView) findViewById(R.id.couponText);
        couponView.setTypeface(face);

        //Getting the details from SharedPreferences/Utilities
        //Get name, F_ID, college, email_ID, reg_events, couponCode
            for(int i=0;i<15;i++)
            {
                reg_events.add("Event "+i);
            }
        //Setting the details
        ArrayAdapter<String> eventsArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.single_list_item, reg_events);
        ListView regEventsList = (ListView) findViewById(R.id.registeredEventsListView);
        nameView.setText(nameView.getText().toString() + Profile.name + "!");
        fIDView.setText(fIDView.getText().toString() + String.valueOf(Profile.id));
        emailIDView.setText(emailIDView.getText().toString() + Profile.email);
        couponView.setText(couponView.getText().toString() + Profile.coupon);
        regEventsList.setAdapter(eventsArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            //Logout
            Toast.makeText(UserProfile.this, "Logout", Toast.LENGTH_SHORT).show();
            Utilities.status=0;
            prefs=getSharedPreferences("LogInPrefs",0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("Logged_in", Utilities.status);
            editor.putString("f_id","");
            editor.putString("f_email","");
            editor.putString("f_pass","");

            editor.apply();
            Intent i=new Intent(UserProfile.this,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            //finish();


            return true;
        }
        if (id==R.id.qr_code)
        {
            //Open QR Code activity
            Intent intent = new Intent(UserProfile.this, QRSplashScreen.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
