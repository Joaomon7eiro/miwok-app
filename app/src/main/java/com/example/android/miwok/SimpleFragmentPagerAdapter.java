package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGES_COUNT = 4;
    private String mTabTitles[] = new String[] { "Numbers", "Family", "Colors", "Phrases" };
    private Context mContext;

    public SimpleFragmentPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NumbersFragment();
            case 1:
                return new FamilyFragment();
            case 2:
                return new ColorsFragment();
            case 3:
                return new PhrasesFragment();
            default:
                return null;
        }
    }

    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }
}
