package com.example.lance.simplebox.Utils;

import com.example.lance.simplebox.DataBean.ChildBean;

import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by xiyu0 on 2017/11/26.
 */

public class DownloadUtil {
    private String fileUrl;
    private String filename;
    private String imei;
    List<ChildBean> childBeans;
    private static final String urls = "http://172.20.0.152:8080/FileService/returnFile";
    public DownloadUtil(){

    }

    OkHttpClient okHttpClient=new OkHttpClient();

}
