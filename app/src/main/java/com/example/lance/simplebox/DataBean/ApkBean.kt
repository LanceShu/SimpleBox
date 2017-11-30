package com.example.lance.simplebox.DataBean

import android.graphics.drawable.Drawable

/**
 * Created by Lance on 2017/11/30.
 */
class ApkBean{

    //Apk的名字;
    var apkName : String = ""

    //Apk的包名;
    var apkPackgeName : String = ""

    //apk的路径
    var apkPath : String = ""

    //apk的大小
    var apkSize : String = ""

    //apk的安装日期;
    var apkDate : String = ""

    //apk的图片;
    var apkIcon : Drawable? = null

    //apk是否被选中;
    var apkSelected : Boolean = false

}