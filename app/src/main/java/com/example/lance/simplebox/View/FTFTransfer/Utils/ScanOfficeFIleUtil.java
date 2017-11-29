package com.example.lance.simplebox.View.FTFTransfer.Utils;

import android.content.Context;
import android.util.Log;

import java.io.File;

/**
 * Created by Lance on 2017/11/29.
 */

public class ScanOfficeFIleUtil implements Runnable{

    private Context context;

    public ScanOfficeFIleUtil(Context context){
        this.context = context;
    }

    public void scanOfficeFile(String filePath){
        File file = new File(filePath);
        File[] files = file.listFiles();
        Log.e("filesLength:",files.length+"");
        for(int i = 0 ;i<files.length;i++){
            if(!files[i].isDirectory()){
                String filename = files[i].getName();
                if(filename.trim().toLowerCase().endsWith(".doc")){
                    Log.e("doc:",filename);
                }
            }else{
                scanOfficeFile(files[i].getPath());
            }
        }
    }

    @Override
    public void run() {
        scanOfficeFile(android.os.Environment.getExternalStorageState());
    }
}
