package com.example.uuzaz.daily_note_1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.ImageButton;

public class TabPagerAdapter extends android.support.v4.app.FragmentStatePagerAdapter{
    private final int tabCount;

    //Count number of tabs

    public TabPagerAdapter(FragmentManager fm,int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        //Returning the current tabs
        switch(position)
        {
            case 0:
                Fragment_Plan fragment_plan = new Fragment_Plan();
                return fragment_plan;
            case 1:
                Fragment_Execute fragment_execute = new Fragment_Execute();
                return fragment_execute;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return tabCount;
    }


}
