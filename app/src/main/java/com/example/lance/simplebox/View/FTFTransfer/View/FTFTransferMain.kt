package com.example.lance.simplebox.View.FTFTransfer.View

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.example.lance.simplebox.Adapter.SendTableViewAdapter
import com.example.lance.simplebox.R
import com.example.lance.simplebox.View.FTFTransfer.Fragment.ApkFragment
import com.example.lance.simplebox.View.FTFTransfer.Fragment.AuVideoFragment
import com.example.lance.simplebox.View.FTFTransfer.Fragment.DocumentFragment
import com.example.lance.simplebox.View.FTFTransfer.Fragment.PictureFragment
import kotlinx.android.synthetic.main.ftf_main.*
import kotlinx.android.synthetic.main.ftf_sender.*

/**
 * Created by Lance on 2017/11/21.
 */
class FTFTransferMain : AppCompatActivity(){

    var senderTitleList = ArrayList<String>()
    var senderFragmentList = ArrayList<android.support.v4.app.Fragment>()
    var sendAdapter : SendTableViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ftf_main)
        //初始化主页面的控件;
        initMainWight()
    }

    fun initMainWight(){
        //主页面返回按键;
        ftfback.setOnClickListener { finish() }
        //主页面发送按键；
        ftfSender.setOnClickListener { FTFSend() }
        //主页面接收按键;
        ftfReceiver.setOnClickListener { FTFReceive() }
    }

    /**
     * 发送文件的逻辑;
     * */
    fun FTFSend(){
        setContentView(R.layout.ftf_sender)
        initSenderData()
        initSenderWight()
    }

    /**
     * 初始化发送端的数据部分;
     * */
    fun initSenderData(){
        senderTitleList.add("影音")
        senderTitleList.add("图片")
        senderTitleList.add("文档")
        senderTitleList.add("应用")

        senderFragmentList.add(AuVideoFragment())
        senderFragmentList.add(PictureFragment())
        senderFragmentList.add(DocumentFragment())
        senderFragmentList.add(ApkFragment())
    }

    /**
     * 初始化发送端的控件
     * */
    fun initSenderWight(){

        sendback.setOnClickListener { recreate() }

        for(i in 0 until senderTitleList.size){
            tab_layout.addTab(tab_layout.newTab().setText(senderTitleList[i]))
        }
        sendAdapter = SendTableViewAdapter(supportFragmentManager,senderFragmentList,senderTitleList)
        tab_layout.tabMode = TabLayout.MODE_FIXED
        sendViewPage.adapter = sendAdapter
        tab_layout.setupWithViewPager(sendViewPage)
    }

    /**
     * 接收文件的逻辑
     * */
    fun FTFReceive(){

    }
}