package com.example.lance.simplebox.Utils;

import com.example.lance.simplebox.DataBean.ChildBean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MultipartBody;
import okhttp3.Response;

/**
 * Created by xiyu0 on 2017/11/21.
 */

public class UploadFileUtil implements Runnable {
    OkHttpClient okHttpClient;
    String fileUrl;
    String filename;
    List<ChildBean> childBeans;
    private static final String urls = "http://172.20.0.152:8080/FileService/recive2";
    public UploadFileUtil(List<ChildBean> filePath){
        fileUrl=filePath.get(0).getFileUri().get(0);
        filename=filePath.get(0).getFileName().get(0);
        childBeans=filePath;
    }
    public void upLoad(){
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("user", "lu");
        for(int i=0;i<childBeans.size();i++){
            filename =childBeans.get(i).getFileName().get(0);
            fileUrl =childBeans.get(i).getFileUri().get(0);
            File file =new File(fileUrl);
            builder.addFormDataPart("file1",filename,RequestBody.create(null,file));
        }
        RequestBody body = builder.build();
        //封装请求数据
//        RequestBody body = new MultipartBody.Builder()
//                .addFormDataPart("user", "lu")  //提交普通表单数据
//                .addFormDataPart("file1", filename, RequestBody.create(null, file))
//                .build();
        //封装request,使用okhttp框架上传数据只能使用post提交方式
        Request request = new Request.Builder().url(urls)
                .post(body)
                .build();
        //执行请求    enqueue:异步执行
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    //服务器端把返回的结果封装在了body()中
                    byte[] result = response.body().bytes();
                    System.out.println(new String(result));
                }
            }
        });
    }

    @Override
    public void run() {
        upLoad();
    }
}
