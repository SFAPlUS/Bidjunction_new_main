package com.bidding.dell.BIDJUNCTION.Adapters;

import com.bidding.dell.BIDJUNCTION.Fragments.HistoryFragment;
import com.bidding.dell.BIDJUNCTION.Fragments.LiveFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new LiveFragment();
        }
        else if (position == 1)
        {
            fragment = new HistoryFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Live";
        }
        else if (position == 1)
        {
            title = "History";
        }

        return title;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
