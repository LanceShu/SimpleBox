package com.example.lance.simplebox.View.FTFTransfer.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.lance.simplebox.Adapter.FTFSendAdapter
import com.example.lance.simplebox.R
import com.example.lance.simplebox.View.FTFTransfer.FTFContent.FTFContent
import kotlinx.android.synthetic.main.ftf_sender.*

/**
 * Created by Lance on 2017/12/1.
 */
class FTFSendActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ftf_sender)
        //初始化数据
        initData()
        //初始化控件
        initWight()
    }

    fun initData(){

    }

    fun initWight(){

        /**
         * 返回按钮
         * */
        ftfsendback.setOnClickListener { finish() }

        /**
         * 初始化RecyclerView
         * */
        val linearLayout = LinearLayoutManager(this)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        ftf_send_recycler.layoutManager = linearLayout
        val adapter = FTFSendAdapter(this,FTFContent.sendFileBeans)
        ftf_send_recycler.adapter = adapter
    }

}