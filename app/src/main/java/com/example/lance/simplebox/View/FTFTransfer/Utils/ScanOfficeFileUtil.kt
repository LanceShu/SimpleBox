package com.example.lance.simplebox.View.FTFTransfer.Utils

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.example.lance.simplebox.DataBean.FileBean

/**
 * Created by Lance
 * on 2017/11/30.
 */
object ScanOfficeFileUtil{

    /**
     * 获取手机内的Word文件
     * */
    fun scanWordFile(context : Context) : List<FileBean>{
        
        val wordList = ArrayList<FileBean>()

        val extension = arrayOf(".doc",".docx",".wps")

        var selection = ""
        for(i in extension.indices){

            if(i != 0){
                selection += " OR "
            }
            selection = selection + MediaStore.Files.FileColumns.DATA + " LIKE '%" + extension[i] + "'"
        }

        val cursor : Cursor = context.contentResolver.query(MediaStore.Files.getContentUri("external")
                , arrayOf(MediaStore.Files.FileColumns.TITLE,MediaStore.Files.FileColumns.DATA,MediaStore.Files.FileColumns.SIZE)
                ,selection,null,null)

        if(cursor.moveToFirst()){
            do{
                val file = FileBean()
                if (cursor.getString(0) != null) {
                    file.fileName = cursor.getString(0)
                } else {
                    file.fileName = "unknown"
                }
                if(cursor.getString(1) != null){
                    file.filePath = cursor.getString(1)
                }else{
                    file.filePath = "unknown"
                }
                if(cursor.getInt(2) != 0){
                    val size : Float = cursor.getInt(2)/1024f/1024f
                    file.fileSize = "$size".substring(0,3)
                }else{
                    file.fileSize = "unknown"
                }
                file.isFileSelected = false
                wordList.add(file)
            }while(cursor.moveToNext())
        }
        cursor.close()
        
        return wordList
        
    }

    /**
     * 获取手机内的PPT列文件
     * */
    fun scanPPTFile(context: Context) : List<FileBean>{

        val pptList = ArrayList<FileBean>()

        val extension = arrayOf(".ppt",".pptx",".dps")

        var selection = ""
        for(i in extension.indices){

            if(i !=0 ){
                selection +=" OR "
            }
            selection = selection + MediaStore.Files.FileColumns.DATA + " LIKE '%" + extension[i] + "'"
        }

        val cursor : Cursor = context.contentResolver.query(MediaStore.Files.getContentUri("external")
                , arrayOf(MediaStore.Files.FileColumns.TITLE,MediaStore.Files.FileColumns.DATA,MediaStore.Files.FileColumns.SIZE)
                ,selection,null,null)

        if(cursor.moveToFirst()){
            do{
                val file = FileBean()
                if (cursor.getString(0) != null) {
                    file.fileName = cursor.getString(0)
                } else {
                    file.fileName = "unknown"
                }
                if(cursor.getString(1) != null){
                    file.filePath = cursor.getString(1)
                }else{
                    file.filePath = "unknown"
                }
                if(cursor.getInt(2) != 0){
                    val size : Float = cursor.getInt(2)/1024f/1024f
                    file.fileSize = "$size".substring(0,3)
                }else{
                    file.fileSize = "unknown"
                }
                file.isFileSelected = false
                pptList.add(file)
            }while(cursor.moveToNext())
        }
        cursor.close()

        return pptList
    }

    /**
     * 获取手机内的Excel文件
     * */
    fun scanExcelFile(context: Context) : List<FileBean>{

        val excelList = ArrayList<FileBean>()

        val extension = arrayOf(".xls",".xlsx",".et")

        var selection = ""
        for(i in extension.indices){

            if(i !=0 ){
                selection += " OR "
            }
            selection = selection + MediaStore.Files.FileColumns.DATA + " LIKE '%" + extension[i] + "'"
        }

        val cursor : Cursor = context.contentResolver.query(MediaStore.Files.getContentUri("external")
                , arrayOf(MediaStore.Files.FileColumns.TITLE,MediaStore.Files.FileColumns.DATA,MediaStore.Files.FileColumns.SIZE)
                ,selection,null,null)

        if(cursor.moveToFirst()){
            do{
                val file = FileBean()
                if (cursor.getString(0) != null) {
                    file.fileName = cursor.getString(0)
                } else {
                    file.fileName = "unknown"
                }
                if(cursor.getString(1) != null){
                    file.filePath = cursor.getString(1)
                }else{
                    file.filePath = "unknown"
                }
                if(cursor.getInt(2) != 0){
                    val size : Float = cursor.getInt(2)/1024f/1024f
                    file.fileSize = "$size".substring(0,3)
                }else{
                    file.fileSize = "unknown"
                }
                file.isFileSelected = false
                excelList.add(file)
            }while(cursor.moveToNext())
        }
        cursor.close()

        return excelList
    }

    /**
     * 获取手机内的PDF的文件
     * */
    fun scanPDFFile(context: Context) : List<FileBean>{

        val pdfList = ArrayList<FileBean>()

        val extension = arrayOf(".pdf")

        var selection = ""
        for(i in extension.indices){

            if(i !=0 ){
                selection += " OR "
            }
            selection = selection + MediaStore.Files.FileColumns.DATA + " LIKE '%" + extension[i] + "'"
        }

        val cursor : Cursor = context.contentResolver.query(MediaStore.Files.getContentUri("external")
                , arrayOf(MediaStore.Files.FileColumns.TITLE,MediaStore.Files.FileColumns.DATA,MediaStore.Files.FileColumns.SIZE)
                ,selection,null,null)

        if(cursor.moveToFirst()){
            do{
                val file = FileBean()
                if (cursor.getString(0) != null) {
                    file.fileName = cursor.getString(0)
                } else {
                    file.fileName = "unknown"
                }
                if(cursor.getString(1) != null){
                    file.filePath = cursor.getString(1)
                }else{
                    file.filePath = "unknown"
                }
                if(cursor.getInt(2) != 0){
                    val size : Float = cursor.getInt(2)/1024f/1024f
                    file.fileSize = "$size".substring(0,3)
                }else{
                    file.fileSize = "unknown"
                }
                file.isFileSelected = false
                pdfList.add(file)
            }while(cursor.moveToNext())
        }
        cursor.close()

        return pdfList
    }

}