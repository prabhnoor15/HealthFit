package com.prabh.cheeku.healthfit;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by cheeku on 2017-09-04.
 */

public class category extends FragmentPagerAdapter {

    private Context mContext;

    public category(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new diet();
        } else if (position == 1) {
            return new calorie();
        } else if (position == 2) {
            return new steps();
        } else {
            return new bmichart();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "BMI" ;
        } else if (position == 1) {
            return "CALORIE";
        } else if (position == 2) {
            return "STEPS";
        } else {
            return "CHART";
        }
    }


}
