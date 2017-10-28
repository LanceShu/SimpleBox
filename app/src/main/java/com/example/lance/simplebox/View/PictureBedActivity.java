package com.example.lance.simplebox.View;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lance.simplebox.R;

/**
 * Created by Lance on 2017/10/28.
 */

public class PictureBedActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView picture;
    private Button toUrl;
    private Button toPicture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picturebed_layout);
        //初始化控件
        initWight();
    }

    private void initWight() {

        picture = (ImageView) findViewById(R.id.picture);
        toUrl = (Button) findViewById(R.id.toUrl);
        toPicture = (Button) findViewById(R.id.topicture);

        picture.setOnClickListener(this);
        toUrl.setOnClickListener(this);
        toPicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
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
        final BottomSheetDialog dialog = new BottomSheetDialog(this,R.style.DialogTheme);
        dialog.setContentView(R.layout.select_or_take_photo);
        Button selectPicture = (Button) dialog.findViewById(R.id.select_picture);
        Button takeCamera = (Button) dialog.findViewById(R.id.take_camera);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);

        /**选择照片*/
        selectPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        /**打开相机*/
        takeCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
