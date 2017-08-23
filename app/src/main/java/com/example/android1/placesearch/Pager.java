package com.example.android1.placesearch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by Android1 on 7/5/2017.
 */

public class Pager extends FragmentStatePagerAdapter {
    int tabCount;


    public Pager(FragmentManager fm, int tabCount) {
        super(fm);

        this.tabCount= tabCount;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Map map= new Map();
                Log.d("Pager","map");
                return map;

            case 1:
                List tab1 = new List();
                Log.d("Pager","List");
                return tab1;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Map";
            case 1:
                return "List";

        }
        return null;
    }
}
