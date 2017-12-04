package com.example.lance.simplebox.View.PictureBed.View;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.Utils.ImageToURLUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Lance on 2017/10/28.
 *
 */

public class PictureBedActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int OPEN_CAMERA = 1;

    public static final int SELECT_PICTURE = 2;

    private ImageView back;
    private ImageView picture;
    private EditText imageEdit;
    private Button toUrl;
    private Button toPicture;
    private BottomSheetDialog dialog;
    private Button selectPicture;
    private Button takeCamera;
    private Button lookPicture;
    private Button cancel;

    private ProgressDialog progressDialog;

    //利用临时文件存储拍照的照片;
    private File outputImage;
    private Uri imageUri;
    //图册里image的路径;
    private String ImagePath = "";

    public static Handler handler ;

    private boolean isHasPicture = false;
    private int watchType = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picturebed_layout);
        //初始化控件
        initWight();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String imageURl = (String) msg.obj;
                switch (msg.what){
                    case ImageToURLUtil.SUCCESS:
                        imageEdit.setText(imageURl);
                        progressDialog.dismiss();
                        break;
                    case ImageToURLUtil.FAILURE:
                        imageEdit.setText(imageURl);
                        progressDialog.dismiss();
                        break;
                }
            }
        };
    }

    private void initWight() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        back = (ImageView) findViewById(R.id.back);
        picture = (ImageView) findViewById(R.id.bpicture);
        imageEdit = (EditText) findViewById(R.id.imageUrlEdit);
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
            case R.id.bpicture:
                selectPictureOrTakeCamera();
                break;
            case R.id.toUrl:
                File file = new File(ImagePath);
                //图片转URL;
                if(!file.exists()){
                    Toast.makeText(this,"您还未选择照片",Toast.LENGTH_SHORT).show();
                }else{
                    progressDialog.show();
                    new Thread(new ImageToURLUtil(ImagePath)).start();
                }
                break;
            case R.id.topicture:
                if(imageEdit.getText().toString().length() != 0){
                    watchType = 2;
                    ImagePath = imageEdit.getText().toString();
                    Glide.with(this)
                            .load(imageEdit.getText().toString())
                            .centerCrop()
                            .into(picture);
                    isHasPicture = true;
                }else{
                    Toast.makeText(this,"请输入图片的URL",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void selectPictureOrTakeCamera() {
        dialog = new BottomSheetDialog(this,R.style.DialogTheme);
        dialog.setContentView(R.layout.select_or_take_photo);
        selectPicture = (Button) dialog.findViewById(R.id.select_picture);
        takeCamera = (Button) dialog.findViewById(R.id.take_camera);
        lookPicture = (Button) dialog.findViewById(R.id.look_picture);
        cancel = (Button) dialog.findViewById(R.id.cancel);

        if(isHasPicture){
            lookPicture.setVisibility(View.VISIBLE);
        }else{
            lookPicture.setVisibility(View.GONE);
        }

        /**选择照片*/
        selectPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                watchType = 1;
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

                watchType = 1;

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

        //查看原图；
        lookPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent picToView = new Intent(PictureBedActivity.this, PhotoViewActivity.class);
                picToView.putExtra("imagePath",ImagePath);
                picToView.putExtra("watchType",watchType);
                startActivity(picToView);
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
                    isHasPicture = true;
                    ImagePath = outputImage.getAbsolutePath();
                    Log.e("outputimage6666:",outputImage.getPath());
                    picture.setImageBitmap(bitmap);
                }
                break;
            case SELECT_PICTURE:
                if(resultCode == RESULT_OK){
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

        Log.e("path123456",uri+"");
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
        Log.e("path333",imagePath);
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if(imagePath != null){
            isHasPicture = true;
            ImagePath = imagePath;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            bitmap.createScaledBitmap(bitmap,100,100,true);
            picture.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this,"获取图片失败",Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = "";
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
                    Snackbar.make(toUrl,"您拒绝了权限申请",Snackbar.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openPicuture();
                }else{
                    Snackbar.make(toPicture,"您拒绝了权限申请",Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
