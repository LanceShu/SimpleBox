package com.example.lance.simplebox.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

import com.example.lance.simplebox.R
import kotlinx.android.synthetic.main.time_memory_layout.*
import java.util.*

/**
 * Created by Lance on 2017/11/13.
 */

class TimeMemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_memory_layout)
        //初始化控件;
        initWight();
    }

    fun initWight(){
        timeView.start()
        var currentTime : Long = System.currentTimeMillis()
        var date = Date(currentTime)
        timeView.setTime(date.hours,date.minutes,date.seconds)
    }
}
