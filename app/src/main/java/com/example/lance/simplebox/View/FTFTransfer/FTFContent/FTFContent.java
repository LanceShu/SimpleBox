package com.example.lance.simplebox.View.FTFTransfer.FTFContent;

import android.content.pm.PackageInfo;
import android.text.style.ImageSpan;

import com.example.lance.simplebox.DataBean.ApkBean;
import com.example.lance.simplebox.DataBean.AudioBean;
import com.example.lance.simplebox.DataBean.FileBean;
import com.example.lance.simplebox.DataBean.ImageBean;
import com.example.lance.simplebox.DataBean.MusicBean;
import com.example.lance.simplebox.DataBean.SendFileBean;

import java.util.List;

/**
 * Created by Lance on 2017/11/29.
 */

public class FTFContent {

    //音频文件列表;
    public static List<MusicBean> musicBeans;

    //视频文件列表；
    public static List<AudioBean> audioBeans;

    //图片的列表;
    public static List<ImageBean> pictureBeans;

    //Word文档的列表;
    public static List<FileBean> wordList;

    //PPT文档的列表;
    public static List<FileBean> pptList;

    //Excel文档的列表;
    public static List<FileBean> excelList;

    //PDF文档的列表;
    public static List<FileBean> pdfList;

    //用户安装的apk列表;
    public static List<ApkBean> apkList;

    //系统预安装的apk列表;
    public static List<ApkBean> sysApkList;

    //用户选中发送的列表;
    public static List<SendFileBean> sendFileBeans;

}
