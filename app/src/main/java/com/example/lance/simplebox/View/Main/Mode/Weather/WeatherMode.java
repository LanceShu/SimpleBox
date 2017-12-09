package com.example.lance.simplebox.View.Main.Mode.Weather;


import com.example.lance.simplebox.View.Main.Persenter.Weather.OnWeatherListener;

/**
 * Created by Kiboooo on 2017/12/9.
 */

public interface WeatherMode {
    void LoadWeather(String CityName,OnWeatherListener weatherListener);
}
