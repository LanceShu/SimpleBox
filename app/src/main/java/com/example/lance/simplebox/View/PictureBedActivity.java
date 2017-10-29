package com.example.lance.simplebox.View;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lance.simplebox.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Lance on 2017/10/28.
 */

public class PictureBedActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int OPEN_CAMERA = 1;
    public static final int SELECT_PICTURE = 2;

    private ImageView back;
    private ImageView picture;
    private Button toUrl;
    private Button toPicture;

    private File outputImage;
    private Uri imageUri;
    private String path;

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
        final BottomSheetDialog dialog = new BottomSheetDialog(this,R.style.DialogTheme);
        dialog.setContentView(R.layout.select_or_take_photo);
        Button selectPicture = (Button) dialog.findViewById(R.id.select_picture);
        Button takeCamera = (Button) dialog.findViewById(R.id.take_camera);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);

        /**选择照片*/
        selectPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ContextCompat.checkSelfPermission(PictureBedActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(PictureBedActivity.this
                            ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
                }else{
                    openPicuture();
                }
                dialog.dismiss();
            }
        });
        /**打开相机*/
        takeCamera.setOnClickListener(new View.OnClickListener() {
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
                    openCamera();
                }
                dialog.dismiss();
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

    private void openPicuture() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,SELECT_PICTURE);
    }

    private void openCamera() {
        if(Build.VERSION.SDK_INT >= 24){
            imageUri = FileProvider.getUriForFile(PictureBedActivity.this
                    ,"com.example.lance.simplebox",outputImage);
        }else{
            imageUri = Uri.fromFile(outputImage);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);

        startActivityForResult(intent,OPEN_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case OPEN_CAMERA:
                if(resultCode == RESULT_OK){
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    bitmap.createScaledBitmap(bitmap,100,100,true);
                    picture.setImageBitmap(bitmap);
                }
                break;
            case SELECT_PICTURE:
                if(requestCode == RESULT_OK){
                    if(Build.VERSION.SDK_INT >= 19){
                        handleImageOnKitKat(data);
                    }else{
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
        }
    }

    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            String documentId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = documentId.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads,documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads")
                        ,Long.valueOf(documentId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if(imagePath != null){
            Glide.with(this).load(imagePath).into(picture);
        }else{
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagePath(Uri uri, String selection) {
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }else{
                    Snackbar.make(toUrl,"You denied the permission",Snackbar.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openPicuture();
                }else{
                    Snackbar.make(toPicture,"You denied the permission",Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
