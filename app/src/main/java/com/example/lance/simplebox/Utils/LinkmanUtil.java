package com.example.lance.simplebox.Utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.lance.simplebox.Content.Content;
import com.example.lance.simplebox.DataBean.LinkmanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/10/26.
 */

public class LinkmanUtil{

    public static List<LinkmanBean> getLinkmenData(Context context){

        List<LinkmanBean> linkmanBeanList = new ArrayList<>();
        linkmanBeanList.clear();

        Cursor cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                LinkmanBean linkmanBean = new LinkmanBean();
                linkmanBean.phone = new ArrayList<>();
                linkmanBean.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME)));
                linkmanBean.setContactId(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)));

                Cursor phone = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null
                        , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + linkmanBean.getContactId(),null,null);
                if(phone.moveToFirst()){
                    do{
                        String p = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        linkmanBean.phone.add(p);
                    }while(phone.moveToNext());
                    phone.close();
                }
                linkmanBeanList.add(linkmanBean);
                Log.e("name",linkmanBean.getName());
            }while(cursor.moveToNext());
            cursor.close();
        }
        return linkmanBeanList;
    }

}
