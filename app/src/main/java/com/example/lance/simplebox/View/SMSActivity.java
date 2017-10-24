package com.example.lance.simplebox.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.lance.simplebox.DataBean.LinkmanBean;
import com.example.lance.simplebox.DataBean.SMSBean;
import com.example.lance.simplebox.MVPContract.LinkmanContract;
import com.example.lance.simplebox.MVPContract.SMSContract;
import com.example.lance.simplebox.MVPContract.TimeContract;
import com.example.lance.simplebox.Mode.LinkmanMode;
import com.example.lance.simplebox.Mode.SMSMode;
import com.example.lance.simplebox.Persenter.LinkmanPersenter;
import com.example.lance.simplebox.Persenter.SMSPersenter;
import com.example.lance.simplebox.R;

import java.util.List;

/**
 * Created by Lance on 2017/10/22.
 */

public class SMSActivity extends AppCompatActivity implements SMSContract.SMSView,LinkmanContract.LinkmanView {

    private SMSPersenter smsPersenter = new SMSPersenter(SMSMode.getInstance(),(SMSContract.SMSView)this,this);
    private LinkmanPersenter linkmanPersenter = new LinkmanPersenter(LinkmanMode.getInstance(),(LinkmanContract.LinkmanView)this,this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_layout);

        initWight();
    }

    private void initWight() {

        smsPersenter.getSMSData();
        linkmanPersenter.getLinkman();
    }

    @Override
    public void showSMSData(List<SMSBean> smsBeanList) {

        /*for(int i =0;i<smsBeanList.size();i++){
            Log.e("smsInfo:",smsBeanList.get(i).getAddress());
        }*/
    }

    @Override
    public void showLinkman(List<LinkmanBean> linkmanBeanList) {
        for(int i =0;i<linkmanBeanList.size();i++){
            Log.e("linkmanName:",linkmanBeanList.get(i).getName());
            Log.e("linkmanPhone:",linkmanBeanList.get(i).phone.toString());
        }
    }
}
