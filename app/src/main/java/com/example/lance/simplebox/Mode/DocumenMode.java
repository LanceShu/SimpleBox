package com.example.lance.simplebox.Mode;

import android.content.Context;
import android.util.Log;

import com.example.lance.simplebox.DataBean.StrBean;
import com.example.lance.simplebox.Utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiyu0 on 2017/11/6.
 */

public class DocumenMode {
    public static List<String> getWordList(Context context){
        List <String> fullList=new ArrayList<String>();
        List<String> wordList =new ArrayList<String>();
        fullList=FileUtils.getSpecificTypeOfFile(context,new String[]{".docx",".doc"});
        for(int i=0;i<fullList.size();i++){
            String temp=fullList.get(i);
            for(int j=temp.length()-1;j>0;j--){
                String target = null ;
                if(temp.substring(j-1,j).charAt(0)=='/'){
                    target = temp.substring(j,temp.length());
                    Log.e("tagg",target);
                    wordList.add(target);
                    break;
                }
            }

        }
        return wordList;
    }

    public static List<String> getExcelList(Context context){
        List <String> fullList2=new ArrayList<String>();
        List<String> excelList =new ArrayList<String>();
        fullList2=FileUtils.getSpecificTypeOfFile(context,new String[]{".xls",".xlsx"});
        for(int i=0;i<fullList2.size();i++){
            String temp=fullList2.get(i);
            for(int j=temp.length()-1;j>0;j--){
                String target = null ;
                if(temp.substring(j-1,j).charAt(0)=='/'){
                    target = temp.substring(j,temp.length());
                    Log.e("tagggg",target);
                    excelList.add(target);
                    break;
                }
            }
        }
        return excelList;
    }

}
