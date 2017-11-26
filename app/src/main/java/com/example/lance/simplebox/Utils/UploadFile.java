package com.example.lance.simplebox.Utils;

import android.util.Log;

import com.example.lance.simplebox.DataBean.ChildBean;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Created by xiyu0 on 2017/11/17.
 */

public class UploadFile implements Runnable{
    private static final String urls = "http://192.168.1.101:8080/FileService/recive";
    String fileUrl;

    public UploadFile(List<ChildBean> filePath){
        fileUrl=filePath.get(0).getFileUri().get(0);
    }

    public  String upload()  {
        try {
            URL url = new URL(urls);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            BufferedOutputStream bufferedOutputStream =new BufferedOutputStream(httpURLConnection.getOutputStream());
            //表单参数
            byte[] bytea = new byte[1024];
            String string ="username=admin&password=1344";
            int d=0;
            bytea=string.getBytes();
            bufferedOutputStream.write(bytea);

            //上传文件
            InputStream inputStream=new FileInputStream(fileUrl);
            BufferedInputStream bufferedInputStream= new BufferedInputStream(inputStream);
            byte[] bytes = new byte[1024];
            int data = 0;

            while((data = bufferedInputStream.read(bytes)) != -1){
                bufferedOutputStream.write(bytes,0,data);
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            inputStream.close();
            bufferedOutputStream.close();

            StringBuilder response = new StringBuilder();
            Scanner in = new Scanner(httpURLConnection.getInputStream());
            while(in.hasNextLine()){
                response.append(in.nextLine());
                response.append("&");
            }
          //  Log.e("response:",response.toString().split("&")[1]);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void run(){
        upload();
    }
}
