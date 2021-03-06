package me.stupideme.history;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by StupidL on 2016/8/12.
 */

class ViewPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragmentList;
    private String[] tabTitles = new String[]{"今天","昨天","前天"};

    ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        fragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }

}
