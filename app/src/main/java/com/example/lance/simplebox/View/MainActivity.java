package com.example.lance.simplebox.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lance.simplebox.Content.Content;
import com.example.lance.simplebox.DataBean.LinkmanBean;
import com.example.lance.simplebox.MVPContract.TimeContract;
import com.example.lance.simplebox.Mode.TimeMode;
import com.example.lance.simplebox.Persenter.TimePersenter;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.Utils.LinkmanUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TimeContract.TimeView,View.OnClickListener{

    private TimePersenter timePersenter = new TimePersenter(TimeMode.getInstance(), (TimeContract.TimeView) this);

    @Bind(R.id.toolbar)
    private Toolbar toolbar;

    @Bind(R.id.hTime)
    private TextView hTime;

    @Bind(R.id.hCalendar)
    private TextView hCalendar;

    @Bind(R.id.hWeather)
    private TextView hWeather;

    //短信；
    @Bind(R.id.sms)
    private LinearLayout smsLayout;

    //图床;
    @Bind(R.id.picture)
    private LinearLayout pictureBed;

    //文档备份;
    @Bind(R.id.document_back_up)
    private LinearLayout documentUpDate;

    //软件管理;
    @Bind(R.id.software_management)
    private LinearLayout softwareManagement;

    //面对面传输;
    @Bind(R.id.faceTransform)
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
        toolbar.inflateMenu(R.menu.toolbar);
        toolbar.setTitle("简盒");
        toolbar.setTitleTextColor(Color.parseColor("#4d4d4d"));

        timePersenter.getTimeData();
        smsLayout.setOnClickListener(this);
        pictureBed.setOnClickListener(this);
        documentUpDate.setOnClickListener(this);
        softwareManagement.setOnClickListener(this);

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
                Intent smsIntent = new Intent(this,SMSorCallAcivity.class);
                startActivity(smsIntent);
                break;
            case R.id.picture:
                Intent picIntent = new Intent(this,PictureBedActivity.class);
                startActivity(picIntent);
                break;
            case R.id.document_back_up:
                Intent documentBackUp =new Intent(this,DocumentBackUpActivity.class);
                startActivity(documentBackUp);
                break;
            case R.id.software_management:
                Intent SoftwareManagement =new Intent(this,SoftwareManagementActivity.class);
                startActivity(SoftwareManagement);
                break;
        }
    }
}
