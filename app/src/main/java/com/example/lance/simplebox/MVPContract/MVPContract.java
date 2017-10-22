package com.example.lance.simplebox.MVPContract;

import android.content.Context;

import com.example.lance.simplebox.DataBean.SMSBean;
import com.example.lance.simplebox.DataBean.WeatherBean;

import java.util.List;

/**
 * Created by Lance on 2017/10/13.
 */

public class MVPContract {

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

    //获取手机短信内容;
    public interface SMSMode{
        List<SMSBean> doSMSData(Context context);
    }

    public interface SMSView{
        void showSMSData(List<SMSBean> smsBeanList);
    }

    public interface SMSPersenter{
        void getSMSData();
    }

}
