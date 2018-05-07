package com.example.lance.simplebox.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

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

    public static String createReduceBitmapFromOrigin(String imagePath, ImageView picture) {
//        File newImage = new File(getExternalCacheDir(), "reduce_picture_bed_image.jpg");
//        if (newImage.exists()) {
//            newImage.delete();
//        }
//        try {
//            newImage.createNewFile();
//            Bitmap reduceBitmap = DecodeSampledBitmapUtil.decodeSampledBitmmapFromImagePath(imagePath, 100, 100);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Bitmap oldBitmap = BitmapFactory.decodeFile(imagePath);
        Log.e("BitmapOld", BitmapUtil.getBitmapSize(oldBitmap) + "");
        Bitmap newBitmap = BitmapUtil.decodeSampledBitmmapFromImagePath(imagePath, 512, 512);
        Log.e("BitmapNew", BitmapUtil.getBitmapSize(newBitmap) + "");
        return null;
    }
}
