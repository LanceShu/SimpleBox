package com.example.lance.simplebox.View.Main.Persenter.Weather.impl;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.example.lance.simplebox.DataBean.WeatherBean.WeatherResult;
import com.example.lance.simplebox.View.Main.Mode.Weather.WeatherMode;
import com.example.lance.simplebox.View.Main.Mode.Weather.impl.WeatherModeIMPL;
import com.example.lance.simplebox.View.Main.Persenter.Weather.OnWeatherListener;
import com.example.lance.simplebox.View.Main.Persenter.Weather.WeatherPresenter;
import com.example.lance.simplebox.View.Main.View.WeatherView;

/**
 * Created by Kiboooo on 2017/12/9.
 * Presenter作为中间层，持有View和Model的引用
 */

public class WeatherPresenterIMPL extends BDAbstractLocationListener implements WeatherPresenter,OnWeatherListener {

    private WeatherView weatherView;
    private WeatherMode weatherMode;

    public WeatherPresenterIMPL(WeatherView weatherView){
        this.weatherView = weatherView;
        weatherMode = new WeatherModeIMPL();
    }


    @Override
    public void onSuccess(WeatherResult weatherResult) {
        weatherView.setWeatherInfo(weatherResult.getRealtime());
    }

    @Override
    public void onError() {

    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        String CityName =
                bdLocation.getDistrict().substring(0, bdLocation.getDistrict().length() - 1);
        if (!CityName.isEmpty())
            weatherMode.LoadWeather(CityName,this);
    }
}
