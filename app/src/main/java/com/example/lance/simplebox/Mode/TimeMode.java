package com.example.lance.simplebox.Mode;

import android.os.Message;
import android.provider.Settings;

import com.example.lance.simplebox.Content.Content;
import com.example.lance.simplebox.MVPContract.MVPContract;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lance on 2017/10/13.
 */

public class TimeMode implements MVPContract.TimeMode{
    @Override
    public String doTimeData() {
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd&HH:mm:ss&EEE");
        String tDate = format.format(date);
        new TimeThread().start();
        return tDate;
    }

    private static TimeMode timeMode;
    public static TimeMode getInstance(){
        if(timeMode == null){
            timeMode = new TimeMode();
        }
        return timeMode;
    }

    public class TimeThread extends Thread{
        @Override
        public void run() {
            super.run();
            do{
                try{
                    Thread.sleep(1000);
                    long time = System.currentTimeMillis();
                    Date date = new Date(time);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd&HH:mm:ss&EEE");
                    String tDate = format.format(date);

                    Message msg = Message.obtain();
                    msg.what = Content.UPDATE_TIME;
                    msg.obj = tDate;
                    Content.handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while(true);
        }
    }

}
