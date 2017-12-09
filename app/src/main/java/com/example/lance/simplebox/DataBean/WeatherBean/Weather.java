package com.example.lance.simplebox.DataBean.WeatherBean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kiboooo on 2017/12/7.
 * 示例：
 * "weather":[
 {
 "date":"2017-12-07",
 "week":"四",
 "nongli":"十月二十",
 "info":{
 "dawn":[
 "0",
 "晴",
 "0",
 "西南风",
 "微风",
 "17:35"
 ],
 */

public class Weather {
    private String date ;
    private String week ;
    private String nongli ;
    @SerializedName("info")
    private InFo inFo;

    public class InFo  {

        /**
         * 数组结构如下
         * "0",    【0】最低温度
         * "晴",   【1】天气情况
         * "-1",   【2】最高温度
         * "南风", 【3】  风向
         * "微风", 【4】 风速度
         * "17:34" 【5】 时间
         */

        List<String> dawn;//黎明
        List<String> day;//白天
        List<String> night;//晚间

        public List<String> getDawn() {
            return dawn;
        }

        public void setDawn(List<String> dawn) {
            this.dawn = dawn;
        }

        public List<String> getDay() {
            return day;
        }

        public void setDay(List<String> day) {
            this.day = day;
        }

        public List<String> getNight() {
            return night;
        }

        public void setNight(List<String> night) {
            this.night = night;
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getNongli() {
        return nongli;
    }

    public void setNongli(String nongli) {
        this.nongli = nongli;
    }

    public InFo getInFo() {
        return inFo;
    }

    public void setInFo(InFo inFo) {
        this.inFo = inFo;
    }
}
