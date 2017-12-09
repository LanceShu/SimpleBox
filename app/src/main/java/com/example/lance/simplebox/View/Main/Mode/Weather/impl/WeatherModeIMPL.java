package com.example.lance.simplebox.View.Main.Mode.Weather.impl;

import android.os.Message;
import android.util.Log;

import com.example.lance.simplebox.DataBean.WeatherBean.WeatherResult;
import com.example.lance.simplebox.View.Main.Mode.Weather.WeatherMode;
import com.example.lance.simplebox.View.Main.Persenter.Weather.OnWeatherListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Kiboooo on 2017/12/9.
 */

public class WeatherModeIMPL implements WeatherMode{


    @Override
    public void LoadWeather(final String CityName, final OnWeatherListener weatherListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient.Builder()
                            .readTimeout(10, TimeUnit.SECONDS)
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .build();

                    RequestBody body = new FormBody.Builder()
                            .add("key", "9a34661c87ba414a9557a337db2f12cc")
                            .add("cityname", CityName)
                            .build();

                    Request request = new Request.Builder()
                            .url("http://api.avatardata.cn/Weather/Query")
                            .post(body)
                            .build();

                    Response response = client.newCall(request).execute();

                    if (response.isSuccessful()) {
                        String content = response.body().string();
                        JSONObject object = new JSONObject(content);
                        if (object.getInt("error_code") == 0){
                            Message message = new Message();
                            Log.e("result", object.getString("result"));
                            WeatherResult weatherResult = new Gson()
                                    .fromJson(object.getString("result"), WeatherResult.class);
                            weatherListener.onSuccess(weatherResult);
                        }else {
                            weatherListener.onError();
                        }
                    }else
                        weatherListener.onError();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    weatherListener.onError();
                }
            }
        }).start();
    }

}
