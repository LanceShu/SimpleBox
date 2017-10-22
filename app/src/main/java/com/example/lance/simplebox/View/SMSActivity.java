package com.example.lance.simplebox.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.lance.simplebox.DataBean.SMSBean;
import com.example.lance.simplebox.MVPContract.MVPContract;
import com.example.lance.simplebox.Mode.SMSMode;
import com.example.lance.simplebox.Persenter.SMSPersenter;
import com.example.lance.simplebox.R;

import java.util.List;

/**
 * Created by Lance on 2017/10/22.
 */

public class SMSActivity extends AppCompatActivity implements MVPContract.SMSView {

    private SMSPersenter smsPersenter = new SMSPersenter(SMSMode.getInstance(),(MVPContract.SMSView)this,this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_layout);

        initWight();
    }

    private void initWight() {

        smsPersenter.getSMSData();
    }

    @Override
    public void showSMSData(List<SMSBean> smsBeanList) {

        for(int i =0;i<smsBeanList.size();i++){
            Log.e("smsInfo:",smsBeanList.get(i).getAddress());
        }
    }
}
