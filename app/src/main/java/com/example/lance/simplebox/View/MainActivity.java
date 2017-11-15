package com.example.lance.simplebox.View;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lance.simplebox.Content.Content;
import com.example.lance.simplebox.MVPContract.TimeContract;
import com.example.lance.simplebox.Mode.TimeMode;
import com.example.lance.simplebox.Persenter.TimePersenter;
import com.example.lance.simplebox.R;

public class MainActivity extends AppCompatActivity implements TimeContract.TimeView,View.OnClickListener{

    private TimePersenter timePersenter = new TimePersenter(TimeMode.getInstance(), (TimeContract.TimeView) this);

    private Toolbar toolbar;
    private TextView hTime;
    private TextView hCalendar;
    private TextView hWeather;

    //短信；
    private LinearLayout smsLayout;
    //时光简记
    private LinearLayout timeMemory;
    //图床;
    private LinearLayout pictureBed;
    //文档备份;
    private LinearLayout documentUpDate;
    //软件管理;
    private LinearLayout softwareManagement;
    //面对面传输;
    private LinearLayout faceTransformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initWight();

        Content.handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case Content.UPDATE_TIME:
                        String tDate = (String) msg.obj;
                        String time = tDate.split("&")[1];
                        String calendar = tDate.split("&")[0];
                        String week = tDate.split("&")[2];
                        hTime.setText(time);
                        hCalendar.setText(calendar+"   "+week);
                        break;
                }
            }
        };
    }

    private void initWight() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        hTime = (TextView) findViewById(R.id.hTime);
        hCalendar = (TextView) findViewById(R.id.hCalendar);
        hWeather = (TextView) findViewById(R.id.hWeather);

        smsLayout = (LinearLayout) findViewById(R.id.sms);
        timeMemory = (LinearLayout) findViewById(R.id.time_memory);
        pictureBed = (LinearLayout) findViewById(R.id.picture);
        documentUpDate= (LinearLayout) findViewById(R.id.document_back_up);
        softwareManagement= (LinearLayout) findViewById(R.id.software_management);
        faceTransformer = (LinearLayout) findViewById(R.id.faceTransform);

        timePersenter.getTimeData();
        smsLayout.setOnClickListener(this);
        timeMemory.setOnClickListener(this);
        pictureBed.setOnClickListener(this);
        documentUpDate.setOnClickListener(this);
        softwareManagement.setOnClickListener(this);
        faceTransformer.setOnClickListener(this);

    }

    @Override
    public void showTimeData(String data) {

        String time = data.split("&")[1];
        String calendar = data.split("&")[0];
        String week = data.split("&")[2];
        hTime.setText(time);
        hCalendar.setText(calendar+"   "+week);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sms:
                Intent smsIntent = new Intent(this,SoftManageActivity.SMSorCallAcivity.class);
                startActivity(smsIntent);
                break;
            case R.id.time_memory:
                Intent timeIntent = new Intent(this,TimeMemActivity.class);
                startActivity(timeIntent);
                break;
            case R.id.picture:
                Intent picIntent = new Intent(this,PictureBedActivity.class);
                startActivity(picIntent);
                break;
            case R.id.document_back_up:
                Intent documentBackUp =new Intent(this,DocuBackUpActivity.class);
                startActivity(documentBackUp);
                break;
            case R.id.software_management:
                Intent SoftwareManagement =new Intent(this,SoftManageActivity.class);
                startActivity(SoftwareManagement);
                break;
            case R.id.faceTransform:
                break;
        }
    }
}
