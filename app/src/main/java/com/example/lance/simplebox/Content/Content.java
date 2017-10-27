package com.example.lance.simplebox.Content;

import android.os.Handler;

import com.example.lance.simplebox.DataBean.LinkmanBean;
import com.example.lance.simplebox.DataBean.SMSBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/10/13.
 */

public class Content {

    public static Handler handler = new Handler();

    public static final int UPDATE_TIME = 1;

    //获取通讯录数据;
    public static List<LinkmanBean> linkmanBeanList = new ArrayList<>();
    //获取信息数据;
    public static List<SMSBean> smsBeanList = new ArrayList<>();

}
