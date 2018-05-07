package com.example.lance.simplebox.Utils;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.example.lance.simplebox.View.PictureBed.View.PictureBedActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Lance
 * on 2017/11/3.
 */

public class ImageToURLUtil implements Runnable{

    //服务器地址;
    private static final String urls = "http://123.207.145.251:8080/SimpleBox/ImageUrl";
    private String imageUrl;
    private ImageView picture;
    private boolean decodeBitmap;
    private Context context;
    public  final static int SUCCESS = 1;
    public  final static int FAILURE = 2;

    public ImageToURLUtil(Context context, String imageToUrl, ImageView imageView, boolean decodeBitmap){
        imageUrl = imageToUrl;
        picture = imageView;
        this.decodeBitmap = decodeBitmap;
        this.context = context;
    }

    public ImageToURLUtil(Context context, String imageToUrl){
        imageUrl = imageToUrl;
        this.context = context;
        new ImageToURLUtil(context, imageToUrl, null, false);
    }

    private void imageToUrl(String imagePath){
        try {
            URL url = new URL(urls);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
            InputStream is = new FileInputStream(imagePath);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] bytes = new byte[1024];
            int data = 0;
            while((data = bis.read(bytes)) != -1){
                bos.write(bytes, 0, data);
            }
            bis.close();
            is.close();
            bos.close();
            StringBuilder response = new StringBuilder();
            Scanner in = new Scanner(connection.getInputStream());
            while(in.hasNextLine()){
                response.append(in.nextLine());
                response.append("&");
            }
            Log.e("response:",response.toString().split("&")[1]);
            Message message = Message.obtain();
            if(response.toString().contains("success")){
                message.what = SUCCESS;
                message.obj = response.toString().split("&")[1];
            }else{
                message.what = FAILURE;
                message.obj = "failure";
            }
            PictureBedActivity.pictureBedActivityHandler.sendMessage(message);
        } catch (IOException e) {
            Log.e("Exception",e.toString());
        }
    }

    @Override
    public void run() {
        if (decodeBitmap && picture != null) {
            String reduceImagePath = BitmapUtil.createReduceBitmapFromOrigin(context, imageUrl, picture);
            imageToUrl(reduceImagePath);
        } else {
            imageToUrl(imageUrl);
        }
    }
}
