package com.example.lance.simplebox.View.Main.Mode.Weather;

import com.example.lance.simplebox.DataBean.WeatherBean.WeatherResult;

/**
 * Created by Kiboooo on 2017/12/12.
 */

public class Gson_WeatherBean {

    private WeatherResult result;
    private int error_code;
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public WeatherResult getResult() {
        return result;
    }

    public void setResult(WeatherResult result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

}
