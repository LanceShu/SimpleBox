package com.example.lance.simplebox.MVPContract;

import android.content.Context;

import com.example.lance.simplebox.DataBean.SMSBean;
import com.example.lance.simplebox.DataBean.WeatherBean;

import java.util.List;

/**
 * Created by Lance on 2017/10/13.
 */

public class TimeContract {

    public interface TimeView{
        void showTimeData(String data);
    }

    public interface  TimePersenter{
        void getTimeData();
    }

    //获取当前系统时间的数据;
    public interface  TimeMode{
        String doTimeData();
    }

}
