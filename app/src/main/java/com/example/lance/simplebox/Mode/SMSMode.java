package com.example.lance.simplebox.Mode;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.lance.simplebox.DataBean.SMSBean;
import com.example.lance.simplebox.MVPContract.SMSContract;
import com.example.lance.simplebox.MVPContract.TimeContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lance on 2017/10/22.
 */

public class SMSMode implements SMSContract.SMSMode {


    @Override
    public List<SMSBean> doSMSData(Context context) {
        List<SMSBean> smsBeanList = new ArrayList<>();

        final String SMS_URI_ALL = "content://sms/";
        StringBuilder smsBuilder = new StringBuilder();

        try{
            Uri uri = Uri.parse(SMS_URI_ALL);
            String[] projection = new String[]{"_id","address","person","body","date","type"};
            Cursor cursor = context.getContentResolver().query(uri,projection,null,null,"date desc");

            if(cursor.moveToFirst()){
                do{
                    SMSBean smsBean = new SMSBean();
                    smsBean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                    smsBean.setPerson(cursor.getInt(cursor.getColumnIndex("person"))+"");
                    smsBean.setBody(cursor.getString(cursor.getColumnIndex("body")));
                    Long longDate = cursor.getLong(cursor.getColumnIndex("date"));
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date d = new Date(longDate);
                    String strDate = dateFormat.format(d);
                    smsBean.setDate(strDate);
                    int intType = cursor.getInt(cursor.getColumnIndex("type"));
                    if(intType == 1){
                        smsBean.setType("接收");
                    }else if(intType  == 2){
                        smsBean.setType("发送");
                    }else{
                        smsBean.setType("null");
                    }
                    smsBeanList.add(smsBean);
                }while(cursor.moveToNext());

                if(!cursor.isClosed()){
                    cursor.close();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return smsBeanList;
    }

    private static SMSMode smsMode;

    public static SMSMode getInstance(){
        if(smsMode == null){
            smsMode = new SMSMode();

        }
        return smsMode;
    }
}
