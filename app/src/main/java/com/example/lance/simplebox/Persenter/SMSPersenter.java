package com.example.lance.simplebox.Persenter;

import android.content.Context;

import com.example.lance.simplebox.MVPContract.MVPContract;

/**
 * Created by Lance on 2017/10/22.
 */

public class SMSPersenter implements MVPContract.SMSPersenter {

    private MVPContract.SMSView smsView;
    private MVPContract.SMSMode smsMode;
    private Context context;

    public SMSPersenter(MVPContract.SMSMode mode,MVPContract.SMSView view,Context context){
        smsMode = mode;
        smsView = view;
        this.context = context;
    }

    @Override
    public void getSMSData() {
        smsView.showSMSData(smsMode.doSMSData(context));
    }
}
