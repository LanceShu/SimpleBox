package com.example.lance.simplebox.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Lance
 * on 2018/5/7.
 */

public class BitmapUtil {
    public static Bitmap decodeSampledBitmmapFromImagePath(String imagePath, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(imagePath, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth || height > reqHeight) {
            int halfWidth = width / 2;
            int halfHeight = height / 2;
            while ((halfWidth / inSampleSize) >= reqWidth && (halfHeight / inSampleSize) >= reqHeight) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 19) {
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= 12) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public static String createReduceBitmapFromOrigin(Context context, String imagePath, ImageView picture) {
        String newImagePath = context.getExternalCacheDir() + "reduce_picture_bed_image.jpg";
        File newFile = new File(newImagePath);
        Bitmap oldBitmap = BitmapFactory.decodeFile(imagePath);
        Log.e("BitmapOld", BitmapUtil.getBitmapSize(oldBitmap) + "");
        Bitmap newBitmap = BitmapUtil.decodeSampledBitmmapFromImagePath(imagePath, 360, 360);
        Log.e("BitmapNew", BitmapUtil.getBitmapSize(newBitmap) + "");
        if (newFile.exists()) {
            newFile.delete();
        }
        try {
            newFile.createNewFile();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            BufferedInputStream bis = new BufferedInputStream(is);
            FileOutputStream fos = new FileOutputStream(newImagePath);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            fos.flush();
            fos.close();
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("newImagePath", newImagePath + " " + BitmapUtil.getBitmapSize(BitmapFactory.decodeFile(newImagePath)));
        return newImagePath;
    }
}
