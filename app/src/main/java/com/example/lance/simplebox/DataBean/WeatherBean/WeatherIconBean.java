package com.example.lance.simplebox.DataBean.WeatherBean;

import com.example.lance.simplebox.R;

import java.util.HashMap;

/**
 * Created by Kiboooo on 2017/12/7.
 */

public class WeatherIconBean {
    public static HashMap<String, Integer> weatherimage = new HashMap<String, Integer>(){
        {
            put("晴", R.drawable.qingtian);
            put("多云", R.drawable.duoyun);
            put("少云", R.drawable.yintian);
            put("晴间多云", R.drawable.duoyun);
            put("阴", R.drawable.yintian);
            put("有风", R.drawable.feng);
            put("平静", R.drawable.yintian);
            put("微风", R.drawable.feng);
            put("和风", R.drawable.feng);
            put("清风", R.drawable.feng);
            put("强风/劲风", R.drawable.taifeng);
            put("疾风", R.drawable.taifeng);
            put("大风", R.drawable.taifeng);
            put("烈风", R.drawable.taifeng);
            put("风暴", R.drawable.taifeng);
            put("狂爆风", R.drawable.taifeng);
            put("飓风", R.drawable.taifeng);
            put("龙卷风", R.drawable.taifeng);
            put("热带风暴", R.drawable.dabaoyu);
            put("阵雨", R.drawable.zhenyu);
            put("强阵雨", R.drawable.zhenyu);
            put("雷阵雨", R.drawable.leizhenyu);
            put("强雷阵雨", R.drawable.leizhenyu);
            put("雷阵雨伴有冰雹", R.drawable.baoxue);
            put("小雨", R.drawable.xiaoyu);
            put("中雨", R.drawable.zhongyu);
            put("大雨", R.drawable.dayu);
            put("暴雨", R.drawable.baoyu);
            put("大暴雨", R.drawable.dabaoyu);
            put("特大暴雨", R.drawable.tedabaoyu);
            put("冻雨", R.drawable.dongyu);
            put("雨夹雪", R.drawable.yujiaxue);
            put("小雪", R.drawable.xiaoxue);
            put("中雪", R.drawable.zhongxue);
            put("大雪", R.drawable.daxue);
            put("暴雪", R.drawable.baoxue);
            put("薄雾", R.drawable.wu);
            put("雾", R.drawable.wu);
            put("霾", R.drawable.mai);
            put("扬沙", R.drawable.yangsha);
            put("浮尘", R.drawable.shachen);
            put("沙尘暴", R.drawable.shachenbao);
            put("强沙尘暴", R.drawable.shachenbao);
            put("冰雹", R.drawable.bingbao);
            put("热", R.drawable.qingtian);
            put("冷", R.drawable.len);
            put("未知", R.drawable.weizhi);
        }
    };
}
