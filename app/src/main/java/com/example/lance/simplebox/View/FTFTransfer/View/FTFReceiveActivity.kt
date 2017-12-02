package com.example.lance.simplebox.View.FTFTransfer.View

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.lance.simplebox.R
import kotlinx.android.synthetic.main.file_receiver.*

/**
 * Created by Lance on 2017/12/2.
 */
class FTFReceiveActivity : AppCompatActivity(){

    var dialog : Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.file_receiver)
        //初始化数据
        initData()
        //初始化控件
        initWight()
    }

    fun initData(){

    }

    fun initWight(){

        //初始化Dialog;
        dialog = Dialog(this,R.style.DialogTheme)
        dialog!!.setCanceledOnTouchOutside(true)

        //返回按键
        ftfReceiveBack.setOnClickListener { finish() }

        //连接附近
        ftf_receive_radar.setOnClickListener {

            dialog!!.setContentView(R.layout.ftf_receive_scan_radar)
            dialog!!.show()
        }

        //扫描二维码连接
        ftf_receive_td_code.setOnClickListener {
            dialog!!.setContentView(R.layout.ftf_receive_td_code)
            dialog!!.show()
        }

    }

}