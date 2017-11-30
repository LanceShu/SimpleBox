package com.example.lance.simplebox.View.FTFTransfer.View

import android.app.ProgressDialog
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
import com.example.lance.simplebox.View.FTFTransfer.Utils.*
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

        /**
         * 初始所有图片未点击;
         * */
        if(pictureBeans != null){
            for (i in pictureBeans.indices) {
                pictureBeans[i].isSelected = false
            }
        }

        /**
         * 初始所有音频未点击;
         * */
        if(musicBeans != null){
            for(i in musicBeans.indices){
                musicBeans[i].isSelected = false
            }
        }

        /**
         * 初始所有视频未点击;
         * */
        if(audioBeans != null){
            for(i in audioBeans.indices){
                audioBeans[i].isSelected = false
            }
        }

        /**
         * 初始所有word未点击;
         * */
        if(wordList != null){
            for(i in audioBeans.indices){
                audioBeans[i].isSelected = false
            }
        }

        /**
         * 初始所有PPT未点击;
         * */
        if(pptList != null){
            for(i in audioBeans.indices){
                audioBeans[i].isSelected = false
            }
        }

        /**
         * 初始所有Excel未点击;
         * */
        if(excelList != null){
            for(i in audioBeans.indices){
                audioBeans[i].isSelected = false
            }
        }

        /**
         * 初始所有PDF未点击;
         * */
        if(pdfList != null){
            for(i in audioBeans.indices){
                audioBeans[i].isSelected = false
            }
        }

        /**
         * 初始所有apk列表与系统apk列表为未点击;
         * */
        if(apkList != null){
            for(i in apkList.indices){
                apkList[i].apkSelected = false
            }
        }

        if(sysApkList != null){
            for(i in sysApkList.indices){
                sysApkList[i].apkSelected = false
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