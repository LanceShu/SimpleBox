package com.example.lance.simplebox.View.FTFTransfer.Utils

import android.content.Context
import android.provider.MediaStore
import com.example.lance.simplebox.DataBean.AudioBean

/**
 * Created by Lance on 2017/11/22.
 */
object ScanAudioUtil{

    fun scanAudioFile(context : Context) : ArrayList<AudioBean>{
        var audioList = ArrayList<AudioBean>()

        val cursor = context.contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                , arrayOf(MediaStore.Video.Media.DISPLAY_NAME,MediaStore.Video.Media.SIZE,MediaStore.Video.Media.DATA)
                ,null,null,null)

        if(cursor.moveToFirst()){
            var audio : AudioBean
            do{
                audio = AudioBean()
                audio.audioName = cursor.getString(0)
                if(cursor.getInt(1) != null){
                    var size : Float = cursor.getInt(1)/1024f/1024f
                    audio.audioSize = "$size".substring(0,4) + "M"
                }else{
                    audio.audioSize = "unkown"
                }
                if(cursor.getString(2) != null){
                    audio.audioPath = cursor.getString(2)
                }
                audioList.add(audio)
            }while(cursor.moveToNext())
            cursor.close()
        }
        return audioList
    }
}