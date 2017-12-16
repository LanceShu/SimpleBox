package com.example.lance.simplebox.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Lance on 2017/12/16.
 */

public class InforAdapter extends FragmentPagerAdapter{

    private List<String> tablist;
    private List<Fragment> fragmentList;

    public InforAdapter(FragmentManager fm,List<String> tablist
            ,List<Fragment> fragmentList) {
        super(fm);
        this.tablist = tablist;
        this.fragmentList = fragmentList;
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
    public CharSequence getPageTitle(int position) {
        return tablist.get(position % tablist.size());
    }
}
