package com.example.lance.simplebox.Persenter;

import android.content.Context;

import com.example.lance.simplebox.MVPContract.CallLogContract;

/**
 * Created by Lance on 2017/10/24.
 */

public class CallLogPersenter implements CallLogContract.CallLogPersenter {

    private CallLogContract.CallLogMode mode;
    private CallLogContract.CallLogView view;
    private Context context;

    public CallLogPersenter(CallLogContract.CallLogMode mode,CallLogContract.CallLogView view ,Context context){
        this.mode = mode;
        this.view = view;
        this.context = context;
    }

    @Override
    public void getCallLog() {
        view.showCallLog(mode.doCallLog(context));
    }
}
