package com.example.lance.simplebox.View.Main.Mode.Weather.impl;

import com.example.lance.simplebox.View.Main.Mode.Weather.GetWeather_retrofit;
import com.example.lance.simplebox.View.Main.Mode.Weather.Gson_WeatherBean;
import com.example.lance.simplebox.View.Main.Mode.Weather.WeatherMode;
import com.example.lance.simplebox.View.Main.Persenter.Weather.OnWeatherListener;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kiboooo on 2017/12/9.
 *
 */

public class WeatherModeIMPL implements WeatherMode{

/*利用OkHttp3.0 实现网络请求*/
//    @Override
//    public void LoadWeather(final String CityName, final OnWeatherListener weatherListener) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client = new OkHttpClient.Builder()
//                            .readTimeout(10, TimeUnit.SECONDS)
//                            .connectTimeout(10, TimeUnit.SECONDS)
//                            .build();
//
//                    RequestBody body = new FormBody.Builder()
//                            .add("key", "9a34661c87ba414a9557a337db2f12cc")
//                            .add("cityname", CityName)
//                            .build();
//
//                    Request request = new Request.Builder()
//                            .url("http://api.avatardata.cn/Weather/Query")
//                            .post(body)
//                            .build();
//
//                    Response response = client.newCall(request).execute();
//
//                    if (response.isSuccessful()) {
//                        String content = response.body().string();
//                        JSONObject object = new JSONObject(content);
//                        if (object.getInt("error_code") == 0){
//                            Message message = new Message();
//                            Log.e("result", object.getString("result"));
//                            WeatherResult weatherResult = new Gson()
//                                    .fromJson(object.getString("result"), WeatherResult.class);
//                            weatherListener.onSuccess(weatherResult);
//                        }else {
//                            weatherListener.onError();
//                        }
//                    }else
//                        weatherListener.onError();
//                } catch (IOException | JSONException e) {
//                    e.printStackTrace();
//                    weatherListener.onError();
//                }
//            }
//        }).start();
//    }

    /*使用Retrofit2.0框架进行的网络请求*/
    public void LoadWeather(final String CityName, final OnWeatherListener weatherListener){
        new Thread(new Runnable() {
            @Override
            public void run() {


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.avatardata.cn/Weather/")
                        .addConverterFactory(GsonConverterFactory.create())//设置Gson构造器
                        .build();

                //接口实例化
                GetWeather_retrofit request = retrofit.create(GetWeather_retrofit.class);

                //设置相应的需要Gson解析的与之对应的Bean类
                Call<Gson_WeatherBean> call = request.getCall(CityName);

                try {
                    Response<Gson_WeatherBean> response = call.execute();//同步回调
                    if (response.isSuccessful()) {
                        /*利用Gson解析后的结果的Body为相应的类*/
                        Gson_WeatherBean gson_weatherBean = response.body();
                        if (gson_weatherBean.getError_code() == 0) {
                            weatherListener.onSuccess(gson_weatherBean.getResult());
                        }else
                            weatherListener.onError();
                    }else
                        weatherListener.onError();
                } catch (IOException e) {
                    e.printStackTrace();
                    weatherListener.onError();
                }
            }
        }).start();
    }
}
