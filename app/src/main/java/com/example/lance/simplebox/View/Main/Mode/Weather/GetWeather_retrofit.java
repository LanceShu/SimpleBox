package com.example.lance.simplebox.View.Main.Mode.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kiboooo on 2017/12/11.
 * 使用 Retrofit 框架需要实现的接口
 */

public interface GetWeather_retrofit {

    /*利用标注的方式，实现的Url的绑定*/
    @GET("Query?key=9a34661c87ba414a9557a337db2f12cc")
    /*请求回调函数的设置*/
    Call<Gson_WeatherBean> getCall(@Query("cityname") String cityname);


}

