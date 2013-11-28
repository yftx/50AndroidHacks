package com.github.yftx.AndroidHacks.sectionTipsAndTricks;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * User: Liuzl
 * Date: 13-11-27
 */
public class TestFragmentAdapter extends FragmentPagerAdapter{
    protected static final String[] CONTENT = new String[] { "This", "Is", "A", "Test", };
    private int mCount = CONTENT.length;

    public TestFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TestFragmentAdapter.CONTENT[position % CONTENT.length];
    }
}
