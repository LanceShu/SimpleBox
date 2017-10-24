package com.example.lance.simplebox.Mode;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;

import com.example.lance.simplebox.DataBean.CallLogBean;
import com.example.lance.simplebox.MVPContract.CallLogContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lance on 2017/10/24.
 */

public class CallLogMode implements CallLogContract.CallLogMode {

    private List<CallLogBean> callLogBeen;

    //获取系统的通话记录;
    @Override
    public List<CallLogBean> doCallLog(Context context) {
        callLogBeen = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                new String[]{CallLog.Calls.DURATION, CallLog.Calls.TYPE, CallLog.Calls.DATE, CallLog.Calls.NUMBER},
                null, null, CallLog.Calls.DEFAULT_SORT_ORDER);

        if(cursor.moveToFirst()){
            do{
                CallLogBean callLogBean = new CallLogBean();
                int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
                if(type == CallLog.Calls.INCOMING_TYPE){
                    callLogBean.setType("呼入");
                }else if(type == CallLog.Calls.OUTGOING_TYPE){
                    callLogBean.setType("呼出");
                }
                callLogBean.setNumber(cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER)));
                long duration = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DURATION));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date d = new Date(duration);
                callLogBean.setDuration(format.format(d));
                callLogBeen.add(callLogBean);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return callLogBeen;
    }

    private static CallLogMode callLogMode;
    public static CallLogMode getInstance(){
        if(callLogMode == null){
            callLogMode = new CallLogMode();
        }
        return callLogMode;
    }
}
