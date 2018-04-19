package com.example.lance.simplebox.View.PictureBed.View

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.LruCache
import android.view.View
import com.bumptech.glide.Glide
import com.example.lance.simplebox.R
import kotlinx.android.synthetic.main.photo_view.*
import okhttp3.internal.cache.DiskLruCache
import uk.co.senab.photoview.PhotoViewAttacher

/**
 * Created by Lance on 2017/10/30.
 */
class PhotoViewActivity : AppCompatActivity(){

    var imagePath :String? = ""
    var watchType :Int? = 0
    var mAttcher : PhotoViewAttacher? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_view)
        imagePath = intent.getStringExtra("imagePath")
        watchType = intent.getIntExtra("watchType",-1)

        //初始化控件;
        initWight()
    }

    fun initWight(){
        back.setOnClickListener { finish() }

        mAttcher = PhotoViewAttacher(photoView)
        if(watchType == 1){
            var bitmap : Bitmap? = null
            bitmap = BitmapFactory.decodeFile(imagePath)
            photoView.setImageBitmap(bitmap)
            mAttcher!!.update()
        }else if(watchType == 2){
            Glide.with(this@PhotoViewActivity)
                    .load(imagePath)
                    .fitCenter()
                    .into(photoView)
            mAttcher!!.update()
        }


    }

}