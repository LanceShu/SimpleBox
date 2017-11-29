package com.example.lance.simplebox.View.FTFTransfer.View

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.example.lance.simplebox.Adapter.SendTableViewAdapter
import com.example.lance.simplebox.R
import com.example.lance.simplebox.View.FTFTransfer.FTFContent.FTFContent.*
import com.example.lance.simplebox.View.FTFTransfer.Fragment.ApkFragment
import com.example.lance.simplebox.View.FTFTransfer.Fragment.AuVideoFragment
import com.example.lance.simplebox.View.FTFTransfer.Fragment.DocumentFragment
import com.example.lance.simplebox.View.FTFTransfer.Fragment.PictureFragment
import kotlinx.android.synthetic.main.ftf_sender.*

/**
 * Created by Lance on 2017/11/21.
 */
class FTFSenderActivity : AppCompatActivity(){

    var senderTitleList = ArrayList<String>()
    var senderFragmentList = ArrayList<android.support.v4.app.Fragment>()
    var sendAdapter : SendTableViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ftf_sender)
        initSenderData()
        initSenderWight()
    }

    /**
     * 初始化发送端的数据部分;
     * */
    fun initSenderData(){
        senderTitleList.add("图片")
        senderTitleList.add("影音")
        senderTitleList.add("文档")
        senderTitleList.add("应用")

        senderFragmentList.add(PictureFragment())
        senderFragmentList.add(AuVideoFragment())
        senderFragmentList.add(DocumentFragment())
        senderFragmentList.add(ApkFragment())

        if(pictureBeans != null){
            for (i in pictureBeans.indices) {
                pictureBeans[i].isSelected = false
            }
        }

        if(musicBeans != null){
            for(i in musicBeans.indices){
                musicBeans[i].isSelected = false
            }
        }
    }

    /**
     * 初始化发送端的控件
     * */
    fun initSenderWight(){

        sendback.setOnClickListener { finish() }

        for(i in 0 until senderTitleList.size){
            tab_layout.addTab(tab_layout.newTab().setText(senderTitleList[i]))
        }
        sendAdapter = SendTableViewAdapter(supportFragmentManager,senderFragmentList,senderTitleList)
        tab_layout.tabMode = TabLayout.MODE_FIXED
        sendViewPage.adapter = sendAdapter
        tab_layout.setupWithViewPager(sendViewPage)
//        ReFTFFragUtil.replaceFTFSendFragment(AuVideoFragment(),this)
        sendViewPage.setCurrentItem(0)
    }
}