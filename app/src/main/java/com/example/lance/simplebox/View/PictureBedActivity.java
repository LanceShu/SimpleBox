package com.example.lance.simplebox.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lance.simplebox.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by Lance on 2017/10/28.
 */

public class PictureBedActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private ImageView picture;
    private Button toUrl;
    private Button toPicture;
    private BottomSheetDialog dialog;

    private File outputImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picturebed_layout);
        //初始化控件
        initWight();
    }

    private void initWight() {

        back = (ImageView) findViewById(R.id.back);
        picture = (ImageView) findViewById(R.id.picture);
        toUrl = (Button) findViewById(R.id.toUrl);
        toPicture = (Button) findViewById(R.id.topicture);

        back.setOnClickListener(this);
        picture.setOnClickListener(this);
        toUrl.setOnClickListener(this);
        toPicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.picture:
                selectPictureOrTakeCamera();
                break;
            case R.id.toUrl:
                break;
            case R.id.topicture:
                break;
        }
    }

    private void selectPictureOrTakeCamera() {
        dialog = new BottomSheetDialog(this,R.style.DialogTheme);
        dialog.setContentView(R.layout.select_or_take_photo);
        Button selectPicture = (Button) dialog.findViewById(R.id.select_picture);
        Button takeCamera = (Button) dialog.findViewById(R.id.take_camera);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);

        /**选择照片*/
        selectPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(PictureBedActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(PictureBedActivity.this
                            ,new String[]{Manifest.permission.CAMERA},1);
                }else{
                    //创建一个output_image.jpg作为临时保存;
                    outputImage = new File(getExternalCacheDir(),"output_image.jpg");
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    try {
                        outputImage.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //openCamera();
                }
            }
        });
        /**打开相机*/
        takeCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(PictureBedActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(PictureBedActivity.this
                            ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    //openCamera();
                }
            }
        });
        /**取消*/
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
