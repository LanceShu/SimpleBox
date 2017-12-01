package com.example.lance.simplebox.View.FTFTransfer.View

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.lance.simplebox.R
import kotlinx.android.synthetic.main.ftf_main.*

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
            val sendIntent = Intent(this@FTFTransferMainActivity, SenderActivity::class.java)
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