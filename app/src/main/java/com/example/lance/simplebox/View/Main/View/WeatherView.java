package com.example.lance.simplebox.View.Main.View;

import com.example.lance.simplebox.DataBean.WeatherBean.Realtime;

/**
 * Created by Kiboooo on 2017/12/9.
 */

public interface WeatherView {

    /**
     * 实现在主View中显示天气信息;
     *
     */
    void setWeatherInfo(Realtime WeatherRealtime);

}
