package com.example.lance.simplebox.View.FTFTransfer.View

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.lance.simplebox.Adapter.SendTableViewAdapter
import com.example.lance.simplebox.Content.Content
import com.example.lance.simplebox.DataBean.SendFileBean
import com.example.lance.simplebox.R
import com.example.lance.simplebox.View.FTFTransfer.FTFContent.FTFContent
import com.example.lance.simplebox.View.FTFTransfer.FTFContent.FTFContent.*
import com.example.lance.simplebox.View.FTFTransfer.Fragment.ApkFragment
import com.example.lance.simplebox.View.FTFTransfer.Fragment.AuVideoFragment
import com.example.lance.simplebox.View.FTFTransfer.Fragment.DocumentFragment
import com.example.lance.simplebox.View.FTFTransfer.Fragment.PictureFragment
import kotlinx.android.synthetic.main.file_sender.*

/**
 * Created by Lance on 2017/11/21.
 */
class SenderActivity : AppCompatActivity(){

    var senderTitleList = ArrayList<String>()
    var senderFragmentList = ArrayList<android.support.v4.app.Fragment>()
    var sendAdapter : SendTableViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.file_sender)
        initSenderData()
        initSenderWight()

        Content.FTFhandler = object : Handler(){
            override fun handleMessage(msg: Message?) {
                when(msg!!.what){
                    Content.SEND_FILE_LIST ->
                        ftf_send_file.setText("下一步(${FTFContent.sendFileBeans.size.toString()})")

                }
            }
        }
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
         * 初始化选中列表
         * */
        if(sendFileBeans == null){
            sendFileBeans = ArrayList<SendFileBean>()
        }else{
            sendFileBeans.clear()
        }

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
            for(i in wordList.indices){
                wordList[i].isFileSelected = false
            }
        }

        /**
         * 初始所有PPT未点击;
         * */
        if(pptList != null){
            for(i in pptList.indices){
                pptList[i].isFileSelected = false
            }
        }

        /**
         * 初始所有Excel未点击;
         * */
        if(excelList != null){
            for(i in excelList.indices){
                excelList[i].isFileSelected = false
            }
        }

        /**
         * 初始所有PDF未点击;
         * */
        if(pdfList != null){
            for(i in pdfList.indices){
                pdfList[i].isFileSelected = false
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

        ftf_send_file.setText("下一步(${FTFContent.sendFileBeans.size.toString()})")

        /**
         * 导航栏初始化；
         * */
        for(i in 0 until senderTitleList.size){
            tab_layout.addTab(tab_layout.newTab().setText(senderTitleList[i]))
        }
        sendAdapter = SendTableViewAdapter(supportFragmentManager,senderFragmentList,senderTitleList)
        tab_layout.tabMode = TabLayout.MODE_FIXED
        sendViewPage.adapter = sendAdapter
        tab_layout.setupWithViewPager(sendViewPage)
//        ReFTFFragUtil.replaceFTFSendFragment(AuVideoFragment(),this)
        /**
         * 首次打开设置为第一个Fragment
         * */
        sendViewPage.setCurrentItem(0)

        ftf_send_file.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val ftfSendIntent = Intent(this@SenderActivity,FTFSendActivity::class.java)
                startActivity(ftfSendIntent)
                finish()
            }
        })
    }
}