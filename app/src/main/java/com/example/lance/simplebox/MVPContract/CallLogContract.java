package com.example.lance.simplebox.MVPContract;

import android.content.Context;

import com.example.lance.simplebox.DataBean.CallLogBean;

import java.util.List;

/**
 * Created by Lance on 2017/10/24.
 */

public class CallLogContract {

    public interface CallLogMode{
        List<CallLogBean> doCallLog(Context context);
    }

    public interface CallLogView{
        void showCallLog(List<CallLogBean> callLogBeen);
    }

    public interface CallLogPersenter{
        void getCallLog();
    }
}
