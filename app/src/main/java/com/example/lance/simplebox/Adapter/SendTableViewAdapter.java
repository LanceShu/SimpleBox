package com.example.lance.simplebox.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Lance on 2017/11/21.
 */

public class SendTableViewAdapter extends FragmentPagerAdapter {

    private List<Fragment> pagerList;
    private List<String> titleList;

    public SendTableViewAdapter(FragmentManager fm,List<Fragment> pagerList,List<String> titleList) {
        super(fm);
        this.pagerList = pagerList;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return pagerList.get(position);
    }

    @Override
    public int getCount() {
        return pagerList != null ? pagerList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
