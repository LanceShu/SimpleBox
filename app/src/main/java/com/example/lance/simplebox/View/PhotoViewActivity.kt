package com.example.lance.simplebox.View

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.lance.simplebox.R
import kotlinx.android.synthetic.main.photo_view.*
import uk.co.senab.photoview.PhotoViewAttacher

/**
 * Created by Lance on 2017/10/30.
 */
class PhotoView : AppCompatActivity(){

    var imagePath :String? = ""
    var mAttcher : PhotoViewAttacher? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_view)
        imagePath = intent.getStringExtra("imagePath")
        //初始化控件;
        initWight()
    }

    fun initWight(){
        back.setOnClickListener { finish() }

        mAttcher = PhotoViewAttacher(photoView)
        val bitmap = BitmapFactory.decodeFile(imagePath)
        photoView.setImageBitmap(bitmap)
        mAttcher!!.update()
    }

}