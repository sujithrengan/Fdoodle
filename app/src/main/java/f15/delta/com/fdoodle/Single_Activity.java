package f15.delta.com.fdoodle;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Single_Activity extends FragmentActivity implements ActionBar.TabListener{
private ViewPager viewPager;
private TabPagerAdapter mAdapter;
private ActionBar actionBar;
int id=0;
String name="";
String desc;
// Tab titles
private String[] tabs = { "Introduction", "Rules & Format", "Prizes","Timings","Contacts" };
private String[] tabs2 = { "Introduction","Timings","Contacts" };

TextView title;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_single_);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
                name = extras.getString("name");
                id  = extras.getInt("id");
                desc = extras.getString("desc");
            Utilities.event_name=name;
            Utilities.event_desc=desc;
                // and get whatever type user account id is

        }



        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ColorEvents)));
        // actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ColorPrimaryDark)));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

@Override
public void onPageSelected(int position) {
        // on changing the page
        // make respected tab selected
        actionBar.setSelectedNavigationItem(position);

        }

@Override
public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

@Override
public void onPageScrollStateChanged(int arg0) {
        }
        });

        // Adding Tabs
        for (String tab_name : tabs2) {
        actionBar.addTab(actionBar.newTab().setText(tab_name)
        .setTabListener(this));
        }
        }


@Override
public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
       // TextView t = (TextView)viewPager.findViewById(R.id.introText);
        //t.setText(name);
        }

@Override
public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }

@Override
public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }
    public void callRegEvent(View view){
        String url = "https://api.festember.com/user/register/event";
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int status = jsonResponse.getInt("status");
                            String error = jsonResponse.getString("data");
                            pDialog.dismiss();
                            Toast.makeText(Single_Activity.this, error, Toast.LENGTH_SHORT).show();
                            System.out.println(error);



                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("error");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        error.printStackTrace();
                        Toast.makeText(Single_Activity.this, "Error", Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("user_id", Utilities.f_id);
                params.put("user_pass", Utilities.f_pass);
                params.put("event_id",""+id);

                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);

    }
    }



