package com.example.lance.simplebox.Persenter;

import android.content.Context;

import com.example.lance.simplebox.MVPContract.SMSContract;
import com.example.lance.simplebox.MVPContract.TimeContract;

/**
 * Created by Lance on 2017/10/22.
 */

public class SMSPersenter implements SMSContract.SMSPersenter {

    private SMSContract.SMSView smsView;
    private SMSContract.SMSMode smsMode;
    private Context context;

    public SMSPersenter(SMSContract.SMSMode mode, SMSContract.SMSView view, Context context){
        smsMode = mode;
        smsView = view;
        this.context = context;
    }

    @Override
    public void getSMSData() {
        smsView.showSMSData(smsMode.doSMSData(context));
    }
}
