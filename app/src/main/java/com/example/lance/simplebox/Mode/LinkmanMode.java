package com.example.lance.simplebox.Mode;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.lance.simplebox.DataBean.LinkmanBean;
import com.example.lance.simplebox.MVPContract.LinkmanContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/10/24.
 */

public class LinkmanMode implements LinkmanContract.LinkmanMode {

    private List<LinkmanBean> linkmanBeens;

    public static String getLinkman(List<LinkmanBean> linkmanBeanList,String phone){
        String man = "";
        return man;
    }

    @Override
    public List<LinkmanBean> doLickman(Context context) {

        linkmanBeens = new ArrayList<>();

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
                linkmanBeens.add(linkmanBean);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return linkmanBeens;
    }

    private static LinkmanMode linkmanMode;
    public static LinkmanMode getInstance(){
        if(linkmanMode == null){
            linkmanMode = new LinkmanMode();
        }
        return linkmanMode;
    }
}
