package com.example.lance.simplebox.View

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.codbking.widget.DatePickDialog
import com.codbking.widget.OnSureLisener
import com.codbking.widget.bean.DateType

import com.example.lance.simplebox.R
import kotlinx.android.synthetic.main.time_dead_layout.*
import kotlinx.android.synthetic.main.time_live_layout.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Lance on 2017/11/13.
 */

class TimeMemActivity : AppCompatActivity() {

    var pref : SharedPreferences? = null
    var editor : SharedPreferences.Editor? = null

    /**
     * 是否设置了出生的时间
     * */
    var settted : Boolean? = false

    /**
     * 系统当前的时间（年、月、日）
     * */
    var curYear : Int = 0
    var curMonth : Int = 0
    var curDays : Int = 0

    /**
     * 用户出生的的时间（年、月、日、年龄）
     * */
    var userYear : Int = 0
    var userMonth : Int = 0
    var userDays : Int = 0
    var userAge : Float? = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_live_layout)

        pref = PreferenceManager.getDefaultSharedPreferences(this)
        editor = pref!!.edit()
        settted = pref!!.getBoolean("isSetted",false)
        userAge = pref!!.getFloat("userAge",-1.0f)

        initDateLive()
        initWightLive()
    }

    /**
     * 生之时：
     * 获取当前的系统时间
     * */
    fun initDateLive(){
        val calendar = Calendar.getInstance()
        curYear = calendar.get(Calendar.YEAR)
        curMonth = calendar.get(Calendar.MONTH)
        curDays = calendar.get(Calendar.DAY_OF_MONTH)
    }

    /**
     * 生之时：
     * 初始化控件
     * */
    fun initWightLive(){
        var currentTime : Long = System.currentTimeMillis()
        var date = Date(currentTime)
        timeView.setTime(date.hours,date.minutes,date.seconds)
        timeView.start()

        back.setOnClickListener({
            finish()
        })

        /**
         * 是否已经设置了出生的时间
         * */
        if(settted == true){
            time_start.visibility = View.GONE
            text_center.visibility = View.VISIBLE
            timelive_info.visibility = View.VISIBLE

            text_center.text = "你已经 "+userAge+" 岁了..."
            time_live_year.text = userAge!!.toInt().toString()
            time_live_month.text = (userAge!! * 12).toInt().toString()
            time_live_weeks.text = (userAge!! * 52).toInt().toString()
            time_live_days.text = (userAge!! * 365).toInt().toString()
            time_live_hours.text = (userAge!! * 365 * 12).toInt().toString()
            time_live_minutes.text = (userAge!! * 365 * 12 * 60).toInt().toString()

            time_dead_but.setOnClickListener {
                setContentView(R.layout.time_dead_layout)
                initDateDead()
                initWightDead()
            }
        }else{
            time_start.visibility = View.VISIBLE
            text_center.visibility = View.GONE
            timelive_info.visibility = View.GONE
            time_live_select.setOnClickListener(object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    openDataPicker()
                }
            })
        }
    }

    /**
     * 打开时间选择器
     * */
    fun openDataPicker(){

        val dialog = DatePickDialog(this)
        //设置时间选择器的上下年限;
        dialog.setYearLimt(60)
        //时间选择器的标题;
        dialog.setTitle("请选择你的诞辰：")
        //时间选择器的样式;
        dialog.setType(DateType.TYPE_YMD)
        dialog.setOnSureLisener(object : OnSureLisener{
            override fun onSure(date: Date?) {
                val format = SimpleDateFormat("yyyy-MM-dd")
                val dates = format.format(date)
                userYear = (dates.split("-")[0]).toInt()
                userMonth = dates.split("-")[1].toInt()
                userDays = dates.split("-")[2].toInt()

                editor!!.putBoolean("isSetted",true)
                editor!!.putFloat("userAge",(curYear + (curMonth-1) /12.0f + curDays/30.0f) - (userYear + (userMonth -1)/12.0f + userDays/30.0f))
                editor!!.apply()
                Log.e("datePicker",pref!!.getFloat("userAge",-1.0f).toString())
                recreate()
            }
        })
        dialog.show()

    }

    /**
     * 死之钟：
     * 初始化数据；
     * */
    fun initDateDead(){

    }

    /**
     * 死之钟：
     * 初始化控件
     * */
    fun initWightDead(){

        var currentTime : Long = System.currentTimeMillis()
        var date = Date(currentTime)
        timeView_d.setTime(date.hours,date.minutes,date.seconds)
        timeView_d.start()

        back_d.setOnClickListener{
            finish()
        }

        time_live_but.setOnClickListener {
            setContentView(R.layout.time_live_layout)
            initDateLive()
            initWightLive()
        }
    }


}

