package com.example.lance.simplebox.View.FTFTransfer.Utils

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import com.example.lance.simplebox.DataBean.ImageBean

/**
 * Created by Lance on 2017/11/23.
 */
object ScanImageUtil {

    fun scanImageFile(context: Context) : List<ImageBean>{

        var pictures = ArrayList<ImageBean>()

        val cursor = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                , arrayOf(MediaStore.Images.Media.BUCKET_DISPLAY_NAME,MediaStore.Images.Media.DATA)
                ,null,null,null)

        if(cursor.moveToFirst()){
            do {
                val picture = ImageBean()
                picture.imageDisplayName = cursor.getString(0)
                picture.imagePath = cursor.getString(1)
                picture.isSelected = false
                pictures.add(picture)
            }while(cursor.moveToNext())
            cursor.close()
        }
        return pictures
    }
}