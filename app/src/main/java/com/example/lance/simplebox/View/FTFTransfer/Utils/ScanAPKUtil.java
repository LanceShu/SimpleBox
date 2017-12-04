package com.example.lance.simplebox.View.FTFTransfer.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.lance.simplebox.DataBean.ApkBean;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Lance on 2017/11/30.
 */

public class ScanAPKUtil {

    private static List<ApkBean> apkList;
    private static List<ApkBean> sysApkList;

    /**
     * 获取手机内安装的应用
     *
     * @param context
     * */
    public static List<ApkBean> scanAPKsFile(Context context){

        apkList = new ArrayList<>();

        PackageManager manager = context.getPackageManager();
        List<PackageInfo> packageInfos = manager.getInstalledPackages(0);

        for(int i = 0;i<packageInfos.size();i++){
            PackageInfo apk = packageInfos.get(i);

            /**
             * 如果(apk.applicationInfo.flags & apk.applicationInfo.FLAG_SYSTEM) < 0,则获取第三方应用;
             * */
            if((apk.applicationInfo.flags & apk.applicationInfo.FLAG_SYSTEM) <= 0){
                ApkBean apkBean = new ApkBean();
                //获取apk的名字；
                apkBean.setApkName(String.valueOf(manager.getApplicationLabel(apk.applicationInfo)));
                //获取apk的包名；
                apkBean.setApkPackgeName(apk.applicationInfo.packageName);
                //获取apk的路径;
                apkBean.setApkPath(apk.applicationInfo.sourceDir);
                //获取apk的大小
                String size = (Integer.valueOf((int)new File(apk.applicationInfo.publicSourceDir).length()) /1024f /1024f + "").substring(0,3);
                apkBean.setApkSize(size);
                //获取apk在手机中的下载日期；
                Date date = new Date(new File(apk.applicationInfo.publicSourceDir).lastModified());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String apkDate = format.format(date);
                apkBean.setApkDate(apkDate);
                //获取apk的图标;
                apkBean.setApkIcon(manager.getApplicationIcon(apk.applicationInfo));
                apkBean.setApkSelected(false);
                apkList.add(apkBean);
            }
        }

        return apkList;
    }

    /**
     * 获取系统预安装的应用
     *
     * @param context
     * */
    public static List<ApkBean> scanSysAPKsFile(Context context){

        sysApkList = new ArrayList<>();

        PackageManager manager = context.getPackageManager();
        List<PackageInfo> packageInfos = manager.getInstalledPackages(0);

        for(int i = 0;i<packageInfos.size();i++){
            PackageInfo apk = packageInfos.get(i);

            /**
             * 如果(apk.applicationInfo.flags & apk.applicationInfo.FLAG_SYSTEM) > 0,则获取系统应用;
             * */
            if((apk.applicationInfo.flags & apk.applicationInfo.FLAG_SYSTEM) > 0){
                ApkBean apkBean = new ApkBean();
                apkBean.setApkName(String.valueOf(manager.getApplicationLabel(apk.applicationInfo)));
                apkBean.setApkPackgeName(apk.applicationInfo.packageName);
                apkBean.setApkPath(apk.applicationInfo.sourceDir);
                String size = (Integer.valueOf((int)new File(apk.applicationInfo.publicSourceDir).length()) /1024f /1024f + "").substring(0,3);
                apkBean.setApkSize(size + "M");
                Date date = new Date(new File(apk.applicationInfo.publicSourceDir).lastModified());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String apkDate = format.format(date);
                apkBean.setApkDate(apkDate);
                apkBean.setApkIcon(manager.getApplicationIcon(apk.applicationInfo));
                apkBean.setApkSelected(false);
                sysApkList.add(apkBean);
            }
        }

        return sysApkList;
    }

}
