package com.example.lance.simplebox.DataBean.WeatherBean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kiboooo on 2017/12/7.
 *
 * "realtime":{
 "wind":{
 "windspeed":"",
 "direct":"西风",
 "power":"4级",
 "offset":""
 },
 "time":"21:57:25",
 "weather":{
 "humidity":"29",
 "img":"01",
 "info":"多云",
 "temperature":"4"
 },
 "dataUptime":"1512655045",
 "date":"2017-12-07",
 "city_code":"101110102",
 "city_name":"长安",
 "week":"4",
 "moon":"十月二十"
 },
 */

public class Realtime {
    private Wind wind;
    public String time;
    @SerializedName("weather")
    private NowWeather nowWeather;
    private String dataUptime;
    public String date;
    private String city_code;
    private String city_name;
    private String week;
    private String moon;

    public class Wind{
        String windspeed;
        String direct;
        String power;
        String offset;

        public String getWindspeed() {
            return windspeed;
        }

        public void setWindspeed(String windspeed) {
            this.windspeed = windspeed;
        }

        public String getDirect() {
            return direct;
        }

        public void setDirect(String direct) {
            this.direct = direct;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getOffset() {
            return offset;
        }

        public void setOffset(String offset) {
            this.offset = offset;
        }
    }

    public class NowWeather{
        String humidity;
        String img;
        @SerializedName("info")
        String Info;
        String temperature;

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getInfo() {
            return Info;
        }

        public void setInfo(String info) {
            Info = info;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public NowWeather getNowWeather() {
        return nowWeather;
    }

    public void setNowWeather(NowWeather nowWeather) {
        this.nowWeather = nowWeather;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getDataUptime() {
        return dataUptime;
    }

    public void setDataUptime(String dataUptime) {
        this.dataUptime = dataUptime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getMoon() {
        return moon;
    }

    public void setMoon(String moon) {
        this.moon = moon;
    }
}
