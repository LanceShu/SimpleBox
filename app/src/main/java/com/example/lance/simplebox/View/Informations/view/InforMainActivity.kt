package com.example.lance.simplebox.View.Informations.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.widget.TableLayout
import com.example.lance.simplebox.Adapter.InforAdapter
import com.example.lance.simplebox.R
import com.example.lance.simplebox.View.Informations.fragment.HotpotFragment
import kotlinx.android.synthetic.main.information_layout.*

/**
 * Created by Lance on 2017/12/16.
 */
class InforMainActivity : AppCompatActivity() {

    val tablist = ArrayList<String>()
    val fragmentlist = ArrayList<Fragment>()
    var adapter : InforAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.information_layout)
        initData()
        initWight()
    }

    fun initData(){
        tablist.add("今日热点")
        tablist.add("新闻")
        tablist.add("科技")
        tablist.add("段子")
        tablist.add("娱乐")

        fragmentlist.add(HotpotFragment())
        fragmentlist.add(HotpotFragment())
        fragmentlist.add(HotpotFragment())
        fragmentlist.add(HotpotFragment())
        fragmentlist.add(HotpotFragment())

        infor_tab.addTab(infor_tab.newTab().setText(tablist[0]))
        infor_tab.addTab(infor_tab.newTab().setText(tablist[1]))
        infor_tab.addTab(infor_tab.newTab().setText(tablist[2]))
        infor_tab.addTab(infor_tab.newTab().setText(tablist[3]))
        infor_tab.addTab(infor_tab.newTab().setText(tablist[4]))
    }

    fun initWight(){

        adapter = InforAdapter(fragmentManager,tablist,fragmentlist)

    }
}