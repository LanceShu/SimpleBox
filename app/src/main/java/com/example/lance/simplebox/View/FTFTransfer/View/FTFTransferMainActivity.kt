package com.example.lance.simplebox.View.FTFTransfer.View

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import com.example.lance.simplebox.Adapter.SendTableViewAdapter
import com.example.lance.simplebox.R
import com.example.lance.simplebox.View.FTFTransfer.Fragment.ApkFragment
import com.example.lance.simplebox.View.FTFTransfer.Fragment.AuVideoFragment
import com.example.lance.simplebox.View.FTFTransfer.Fragment.DocumentFragment
import com.example.lance.simplebox.View.FTFTransfer.Fragment.PictureFragment
import com.example.lance.simplebox.View.FTFTransfer.Utils.ReFTFFragUtil
import kotlinx.android.synthetic.main.ftf_main.*
import kotlinx.android.synthetic.main.ftf_sender.*

/**
 * Created by Lance on 2017/11/21.
 */
class FTFTransferMainActivity : AppCompatActivity(){

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
        ftfSender.setOnClickListener {
            val sendIntent = Intent(this@FTFTransferMainActivity,FTFSenderActivity::class.java)
            startActivity(sendIntent)
        }
        //主页面接收按键;
        ftfReceiver.setOnClickListener { FTFReceive() }
    }

    /**
     * 接收文件的逻辑
     * */
    fun FTFReceive(){

    }
}