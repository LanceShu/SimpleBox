package com.example.lance.simplebox.Utils;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xiyu0 on 2017/11/20.
 */

public class DownloadFile implements Runnable  {
    private static final String urls = "http://172.20.0.152:8080/JavaWeb/HelloWorld";
    private URL url;
    void downLoad(){
        try{
            HttpURLConnection httpURLConnection;

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        downLoad();
    }
}
