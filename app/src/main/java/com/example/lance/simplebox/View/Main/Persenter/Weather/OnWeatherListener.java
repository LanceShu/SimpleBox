package com.example.lance.simplebox.View.Main.Persenter.Weather;

import com.example.lance.simplebox.DataBean.WeatherBean.WeatherResult;

/**
 * Created by Kiboooo on 2017/12/9.
 * 在Presenter层实现，给Model层回调，更改View层的状态，确保Model层不直接操作View层
 */

public interface OnWeatherListener {
    /**
     * 成功时回调
     */
    void onSuccess(WeatherResult weatherResult);

    /**
     * 失败时回调，简单处理，没做什么
     */
    void onError();
}
