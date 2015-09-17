package f15.delta.com.fdoodle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new introFragment();
            case 1:
                // Games fragment activity
                return new rulesFragment();
            case 2:
                // Movies fragment activity
                return new prizeFragment();

            case 3:
                return new timeFragment();

            case 4:
                return new contactFragment();


        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 5;
    }

}