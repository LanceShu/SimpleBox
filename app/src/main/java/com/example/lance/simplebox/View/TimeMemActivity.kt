package com.example.lance.simplebox.View

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
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
    var date : Date? = null

    /**
     * 是否设置了出生的时间
     * 是否设置了生命终结的时间
     * */
    var settted : Boolean? = false
    var setted_dead : Boolean? = false

    var liveDate : String? = null
    var deadDate : String? = null

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

    /**
     * 用户选择的生命终结日期；
     * */
    var userDead : Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_live_layout)

        pref = PreferenceManager.getDefaultSharedPreferences(this)
        editor = pref!!.edit()
        settted = pref!!.getBoolean("isSetted",false)

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
        date = Date(currentTime)

        timeView.setTime(date!!.hours,date!!.minutes,date!!.seconds)
        timeView.start()

        back.setOnClickListener({
            finish()
        })

        /**
         * 是否已经设置了出生的时间
         * */
        if(settted == true){
            time_start.visibility = View.GONE
            time_live_text.visibility = View.VISIBLE
            timelive_info.visibility = View.VISIBLE

            setLiveInfor(pref!!.getInt("liveYear",-1),pref!!.getInt("liveMonth",-1),pref!!.getInt("liveDays",-1))

            //死之钟按钮;
            time_dead_but.setOnClickListener {
                setContentView(R.layout.time_dead_layout)
                initDateDead()
                initWightDead()
            }

            //年龄选择编辑;
            time_live_edit.setOnClickListener {
                openDataPicker()
            }

            //心愿按钮;
            time_live_wish.setOnClickListener {
                Toast.makeText(this@TimeMemActivity,"心愿功能，还未添加~",Toast.LENGTH_SHORT).show()
            }

        }else{
            time_start.visibility = View.VISIBLE
            time_live_text.visibility = View.GONE
            timelive_info.visibility = View.GONE
            time_live_select.setOnClickListener(object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    openDataPicker()
                }
            })
        }
    }

    /**
     * 设置生之时的信息
     * */
    fun setLiveInfor(liveYear : Int,liveMonth : Int,liveDays : Int){

        var userage : Float = (curYear + (curMonth-1) /12.0f + curDays/30.0f) - (liveYear + (liveMonth -1)/12.0f + liveDays/30.0f)

        text_center.text = "你已经 "+userage+" 岁了..."
        time_live_year.text = userage!!.toInt().toString()
        time_live_month.text = (userage!! * 12).toInt().toString()
        time_live_weeks.text = (userage!! * 52).toInt().toString()
        time_live_days.text = (userage!! * 365).toInt().toString()
        time_live_hours.text = (userage!! * 365 * 12).toInt().toString()
        time_live_minutes.text = (userage!! * 365 * 12 * 60).toInt().toString()
    }

    /**
     * 打开生之时
     * 时间选择器
     * */
    fun openDataPicker(){

        if(pref!!.getString("liveDate","") == ""){
            liveDate = date.toString()
            Log.e("liveDate",liveDate)
        }else{
            liveDate = pref!!.getString("liveDate","")
            Log.e("liveDate",liveDate)
        }

        val dialog = DatePickDialog(this)
        dialog.setStartDate((Date(liveDate)))
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

                editor!!.putInt("liveYear",userYear)
                editor!!.putInt("liveMonth",userMonth)
                editor!!.putInt("liveDays",userDays)
                editor!!.putString("liveDate",date.toString())

                if(settted == true){
                    editor!!.apply()
                    setLiveInfor(pref!!.getInt("liveYear",-1),pref!!.getInt("liveMonth",-1),pref!!.getInt("liveDays",-1))
                }else{
                    editor!!.putBoolean("isSetted",true)
                    editor!!.apply()
                    recreate()
                }
                Log.e("datePicker",pref!!.getFloat("userAge",-1.0f).toString())

            }
        })
        dialog.show()

    }

    /**
     * 死之钟：
     * 初始化数据；
     * */
    fun initDateDead(){
        setted_dead = pref!!.getBoolean("setted_dead",false)
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

        if(setted_dead == true){

            setDeadInfor(pref!!.getInt("deadYear",-1),pref!!.getInt("deadMonth",-1),pref!!.getInt("deadDays",-1))

        }else{
            openDeadDatePicker()
        }

        //返回按钮;
        back_d.setOnClickListener{
            finish()
        }

        //生之时的按钮;
        time_live_but.setOnClickListener {
            recreate()
        }

        //终结时间的选择按钮;
        time_dead_edit.setOnClickListener {
            openDeadDatePicker()
        }

        //心愿按钮;
        time_dead_wish.setOnClickListener {
            Toast.makeText(this@TimeMemActivity,"心愿功能，还未添加~",Toast.LENGTH_SHORT).show()
        }

    }

    /**
     * 打开死之钟的日期选择器
     * */
    fun openDeadDatePicker(){

        if(pref!!.getString("deadDate","") == ""){
            deadDate = date.toString()
            Log.e("liveDate",deadDate)
        }else{
            deadDate = pref!!.getString("deadDate","")
            Log.e("liveDate",deadDate)
        }

        val dialog = DatePickDialog(this)
        dialog.setStartDate(Date(deadDate))
        //设置时间选择器的上下年限;
        dialog.setYearLimt(100)
        //时间选择器的标题;
        dialog.setTitle("请选择你的终结时间：")
        //时间选择器的样式;
        dialog.setType(DateType.TYPE_YMD)
        dialog.setOnSureLisener(object : OnSureLisener{
            override fun onSure(date: Date?) {
                val format = SimpleDateFormat("yyyy-MM-dd")
                val dates = format.format(date)
                userYear = (dates.split("-")[0]).toInt()
                userMonth = dates.split("-")[1].toInt()
                userDays = dates.split("-")[2].toInt()

                editor!!.putString("deadDate",date.toString())
                editor!!.putBoolean("setted_dead",true)

                editor!!.putInt("deadYear",userYear)
                editor!!.putInt("deadMonth",userMonth)
                editor!!.putInt("deadDays",userDays)
                editor!!.apply()
                Log.e("DeaddatePicker",pref!!.getFloat("userDead",-1.0f).toString())

                setDeadInfor(pref!!.getInt("deadYear",-1),pref!!.getInt("deadMonth",-1),pref!!.getInt("deadDays",-1))
            }
        })
        dialog.show()
    }

    /**
     * 设置死之钟的信息
     * */
    fun setDeadInfor(deadYear : Int,deadMonth : Int,deadDays : Int){

        var userdead = (deadYear + (deadMonth-1) /12.0f + deadDays/30.0f) - (curYear + (curMonth -1)/12.0f + curDays/30.0f)

        var deadDyas = userdead * 365
        time_dead_content.text = "距离生命的终结还有 "+ deadDyas +" 天..."
        time_dead_book.text = "看 "+ (deadDyas/7).toInt() +" 本书"
        time_dead_travel.text = "旅游 "+ (userdead*2).toInt() +" 次"
        time_dead_food.text = "吃 "+ (deadDyas*3).toInt() +" 顿饭"
        time_dead_love.text = "啪啪啪 "+(deadDyas/5).toInt()+" 次"
        time_dead_weeks.text = "度过 "+(deadDyas/7).toInt()+" 次周末"
        time_dead_holidays.text = "享受 "+(userdead*3).toInt()+" 个长假"
    }

}

