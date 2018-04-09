package com.example.lance.simplebox.View.Main.View;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.example.lance.simplebox.Content.Content;
import com.example.lance.simplebox.DataBean.WeatherBean.Realtime;
import com.example.lance.simplebox.DataBean.WeatherBean.WeatherIconBean;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.View.DocumentBackUp.View.View.DocumentBackUpActivity;
import com.example.lance.simplebox.View.FTFTransfer.View.FTFTransferMainActivity;
import com.example.lance.simplebox.View.Informations.view.InforMainActivity;
import com.example.lance.simplebox.View.Main.Contract.TimeContract;
import com.example.lance.simplebox.View.Main.Mode.TimeMode;
import com.example.lance.simplebox.View.Main.Persenter.TimePersenter;
import com.example.lance.simplebox.View.Main.Persenter.Weather.impl.WeatherPresenterIMPL;
import com.example.lance.simplebox.View.PictureBed.View.PictureBedActivity;
import com.example.lance.simplebox.View.SoftManageActivity;
import com.example.lance.simplebox.View.TimeMemory.View.TimeMemActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements WeatherView,TimeContract.TimeView,View.OnClickListener{

    private TimePersenter timePersenter = new TimePersenter(TimeMode.getInstance(), (TimeContract.TimeView) this);
    private Toolbar toolbar;
    private TextView hTime;
    private TextView hCalendar;
    private TextView hWeather;
    private ImageView hWpic;

    private LocationClient locationClient;

    //短信；
    private LinearLayout smsLayout;

    /*简盒工具*/
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
    //支付工具：启动扫码支付
    private LinearLayout payTool;

    /*简盒资讯：*/
    //今日热点；
    private LinearLayout hotpot;

    @SuppressLint("HandlerLeak")
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
        hWpic = (ImageView) findViewById(R.id.hWpic);
        smsLayout = (LinearLayout) findViewById(R.id.sms);
        timeMemory = (LinearLayout) findViewById(R.id.time_memory);
        pictureBed = (LinearLayout) findViewById(R.id.picture);
        documentUpDate= (LinearLayout) findViewById(R.id.document_back_up);
        softwareManagement= (LinearLayout) findViewById(R.id.software_management);
        faceTransformer = (LinearLayout) findViewById(R.id.faceTransform);
        payTool = (LinearLayout) findViewById(R.id.pay_tool);
        hotpot= (LinearLayout) findViewById(R.id.hotpot);
        timePersenter.getTimeData();
        smsLayout.setOnClickListener(this);
        timeMemory.setOnClickListener(this);
        pictureBed.setOnClickListener(this);
        documentUpDate.setOnClickListener(this);
        softwareManagement.setOnClickListener(this);
        faceTransformer.setOnClickListener(this);
        payTool.setOnClickListener(this);
        hotpot.setOnClickListener(this);
        //检测权限
        CheckPermission();
    }

    //初始化启动百度的Location定位；
    private void initLocationClient(){
        locationClient = new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(new WeatherPresenterIMPL(MainActivity.this));
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);//获取当前位置的详细地址信息
        locationClient.setLocOption(option);
        locationClient.start();
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
    public void setWeatherInfo(final Realtime WeatherRealtime) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Realtime.NowWeather nowWeather = WeatherRealtime.getNowWeather();
                String NowWeather = nowWeather.getTemperature() + "℃  "
                        + nowWeather.getInfo() + "  "
                        + WeatherRealtime.getCity_name();
                hWeather.setText(NowWeather);
                //加载图片
                Glide.with(MainActivity.this)
                        .load(WeatherIconBean.weatherimage.get(nowWeather.getInfo()))
                        .into(hWpic);
            }
        });
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
                Intent documentBackUp =new Intent(this,DocumentBackUpActivity.class);
                startActivity(documentBackUp);
                break;
            case R.id.software_management:
                Intent SoftwareManagement =new Intent(this,SoftManageActivity.class);
                startActivity(SoftwareManagement);
                break;
            case R.id.faceTransform:
                Intent FTFIontent = new Intent(this, FTFTransferMainActivity.class);
                startActivity(FTFIontent);
                break;
            case R.id.pay_tool:
                //进入支付设置；
                Log.e("pay_tool", "点击了支付工具");
                break;
            case R.id.hotpot:
                Intent hotpotIntent = new Intent(this, InforMainActivity.class);
                startActivity(hotpotIntent);
                break;
        }
    }

    /*获取权限*/
    private void CheckPermission(){
        List<String> permissionList = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                ){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE)
                !=PackageManager.PERMISSION_GRANTED
                ){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED
                ){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissionList.isEmpty()) {
            /*如果不需要请求权限就执行操作*/
            initLocationClient();
        }else{
            String[] permission = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permission, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            //权限若没有全部通过，就结束Demo
                            finish();
                            break;
                        }
                    }
                    /*请求权限成功后执行操作*/
                    initLocationClient();
                }else {
                    finish();
                }
                break;
            default:
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationClient.stop();
    }
}
