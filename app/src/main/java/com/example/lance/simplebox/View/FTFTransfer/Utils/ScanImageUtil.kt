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
                , arrayOf(MediaStore.Images.Media.BUCKET_DISPLAY_NAME,MediaStore.Images.Media.DATA,MediaStore.Images.Media.SIZE)
                ,null,null,null)

        if(cursor.moveToFirst()){
            do {
                val picture = ImageBean()
                picture.imageDisplayName = cursor.getString(0)
                picture.imagePath = cursor.getString(1)
                if(cursor.getInt(2) != null){
                    var size : Float = cursor.getInt(1)/1024f/1024f
                    picture.imageSize = "$size".substring(0,3)
                }else{
                    picture.imageSize = "unkown"
                }
                picture.isSelected = false
                pictures.add(picture)
            }while(cursor.moveToNext())
            cursor.close()
        }
        return pictures
    }
}