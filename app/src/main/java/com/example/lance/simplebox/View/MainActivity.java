package com.example.lance.simplebox.View;

import android.content.Intent;
import android.graphics.Color;
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

    private LinearLayout smsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        toolbar.inflateMenu(R.menu.toolbar);
        toolbar.setTitle("简盒");
        toolbar.setTitleTextColor(Color.parseColor("#4d4d4d"));

        hTime = (TextView) findViewById(R.id.hTime);
        hCalendar = (TextView) findViewById(R.id.hCalendar);
        hWeather = (TextView) findViewById(R.id.hWeather);

        smsLayout = (LinearLayout) findViewById(R.id.sms);

        timePersenter.getTimeData();
        smsLayout.setOnClickListener(this);
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
                Intent smsIntent = new Intent(this,CallLogActivity.class);
                startActivity(smsIntent);
                break;
        }
    }
}
