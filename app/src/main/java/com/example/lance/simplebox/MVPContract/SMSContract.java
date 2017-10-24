package com.example.lance.simplebox.MVPContract;

import android.content.Context;

import com.example.lance.simplebox.DataBean.SMSBean;

import java.util.List;

/**
 * Created by Lance on 2017/10/24.
 */

public class SMSContract {

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
