package com.example.lance.simplebox.DataBean;

/**
 * Created by Lance on 2017/10/22.
 */

public class SMSBean {

    //手机号;
    private String address;
    //人数;
    private String person;
    //内容;
    private String body;
    //时间;
    private String date;
    //类型;
    private String type;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public String getPerson() {
        return person;
    }

    public String getType() {
        return type;
    }
}
