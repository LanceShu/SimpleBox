package com.example.lance.simplebox.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Lance
 * on 2017/12/16.
 */

class InforAdapter(fm: FragmentManager, private val tablist: List<String>, private val fragmentList: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tablist[position % tablist.size]
    }
}
