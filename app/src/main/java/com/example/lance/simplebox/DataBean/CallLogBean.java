package com.example.lance.simplebox.DataBean;

/**
 * Created by Lance on 2017/10/24.
 */

/**通话记录Bean*/
public class CallLogBean {

    //通话时长
    private String duration;
    //呼出/呼入/未接
    private String type;
    //通话时间;
    private String date;
    //手机号;
    private String number;

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public String getNumber() {
        return number;
    }
}
