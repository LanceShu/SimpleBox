package com.example.lance.simplebox.View.Main.Contract;

/**
 * Created by Lance on 2017/10/13.
 */

public class TimeContract {

    public interface TimeView{
        void showTimeData(String data);
    }

    public interface TimePersenter{
        void getTimeData();
    }

    //获取当前系统时间的数据;
    public interface  TimeMode{
        String doTimeData();
    }

}
