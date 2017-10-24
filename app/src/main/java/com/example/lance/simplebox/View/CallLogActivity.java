package com.example.lance.simplebox.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.lance.simplebox.DataBean.CallLogBean;
import com.example.lance.simplebox.MVPContract.CallLogContract;
import com.example.lance.simplebox.Mode.CallLogMode;
import com.example.lance.simplebox.Persenter.CallLogPersenter;
import com.example.lance.simplebox.R;

import java.util.List;

/**
 * Created by Lance on 2017/10/24.
 */

public class CallLogActivity extends AppCompatActivity implements CallLogContract.CallLogView{

    private CallLogPersenter callLogPersenter = new CallLogPersenter(CallLogMode.getInstance(),this,this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_layout);

        initWight();
    }

    private void initWight() {
        callLogPersenter.getCallLog();
    }

    @Override
    public void showCallLog(List<CallLogBean> callLogBeen) {
        for(int i =0;i<callLogBeen.size();i++){
            Log.e("smsInfo:",callLogBeen.get(i).getNumber());
        }
    }
}
