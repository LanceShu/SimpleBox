package com.example.lance.simplebox.View.FTFTransfer.Utils

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import com.example.lance.simplebox.DataBean.MusicBean
import java.io.File
import java.util.Vector

/**
 * Created by Lance on 2017/11/29.
 */

object ScanMusicUtil{

    fun scanMusicFile(context : Context) : ArrayList<MusicBean>{

        var musicList = ArrayList<MusicBean>()

        val cursor = context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                arrayOf(MediaStore.Audio.Media.DISPLAY_NAME,MediaStore.Audio.Media.SIZE,MediaStore.Audio.Media.DATA,MediaStore.Audio.Media.ALBUM_ID),
                MediaStore.Audio.Media.MIME_TYPE + "=? or " + MediaStore.Audio.Media.MIME_TYPE + "=?",
                arrayOf("audio/mpeg","audio/x-ms-wma"),null)

        if(cursor.moveToFirst()){
            var music : MusicBean? = null
            do{
                music = MusicBean()
                music.musicName = cursor.getString(0)

                if(cursor.getInt(1) != null){
                    var size : Float = cursor.getInt(1)/1024f/1024f
                    music.musicSize = "$size".substring(0,4) + "M"
                }else{
                    music.musicSize = "unkown"
                }

                if(cursor.getString(2) != null){
                    music.musicPath = cursor.getString(2)
                }

                if(cursor.getString(3) == null){
                    music.album_id = ""
                }else{
                    music.album_id = cursor.getString(3)
                }

                music.isSelected = false
                musicList.add(music)

            }while (cursor.moveToNext())

            cursor.close()
        }
        return musicList
    }
}
