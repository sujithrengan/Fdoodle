package f15.delta.com.fdoodle;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class Single_Activity extends FragmentActivity implements ActionBar.TabListener{
private ViewPager viewPager;
private TabPagerAdapter mAdapter;
private ActionBar actionBar;
// Tab titles
private String[] tabs = { "Introduction", "Rules & Format", "Prizes","Timings","Contacts" };

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_single_);

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        //  actionBar.setHomeButtonEnabled(false);
     //   actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ColorPrimary)));
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
        for (String tab_name : tabs) {
        actionBar.addTab(actionBar.newTab().setText(tab_name)
        .setTabListener(this));
        }
        }


@Override
public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());

        }

@Override
public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }

@Override
public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }

        }
