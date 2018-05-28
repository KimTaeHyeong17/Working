package com.example.uuzaz.daily_note_third;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.uuzaz.daily_note_third.FragmentOne;
import com.example.uuzaz.daily_note_third.FragmentTwo;

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
                FragmentOne fragmentOne = new FragmentOne();
                return fragmentOne;
            case 1:
                FragmentTwo fragmentTwo = new FragmentTwo();
                return fragmentTwo;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return tabCount;
    }


}
