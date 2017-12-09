package com.example.lance.simplebox.DataBean.WeatherBean;

/**
 * Created by Kiboooo on 2017/12/7.
 *
 * "pm25":{
 "key":"Xian",
 "show_desc":"0",
 "pm25":{
 "curPm":"87",
 "pm25":"59",
 "pm10":"109",
 "level":"2",
 "quality":"良",
 "des":"可以正常在户外活动，易敏感人群应减少外出"
 },
 "dateTime":"2017年12月07日22时",
 "cityName":"西安"
 },
 */

public class Pm25 {

    private String key;
    private String show_desc;
    private PM25 pm25;
    private String dateTime;
    private String cityName;

    public class PM25{
        String curPm;
        String pm25;
        String pm10;
        String level;
        String quality;
        String des;

        public String getCurPm() {
            return curPm;
        }

        public void setCurPm(String curPm) {
            this.curPm = curPm;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getShow_desc() {
        return show_desc;
    }

    public void setShow_desc(String show_desc) {
        this.show_desc = show_desc;
    }

    public PM25 getPm25() {
        return pm25;
    }

    public void setPm25(PM25 pm25) {
        this.pm25 = pm25;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
