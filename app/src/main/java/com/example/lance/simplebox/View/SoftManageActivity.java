package com.example.lance.simplebox.View;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.lance.simplebox.Content.Content;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.Utils.LinkmanUtil;

/**
 * Created by xiyu0 on 2017/10/31.
 */

public class SoftManageActivity extends AppCompatActivity{
    private Toolbar toolbar;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.software_management);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.font));
        toolbar.setTitle("软件管理");
    }

    /**
     * Created by Lance on 2017/10/26.
     */

    public static class SMSorCallAcivity extends AppCompatActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.sms_layout);

            //初始化数据;
            initData();
        }

        private void initData() {
            Content.linkmanBeanList = LinkmanUtil.getLinkmenData(this);
            for(int i =0 ;i<Content.linkmanBeanList.size();i++){
                Log.e("linkman:",Content.linkmanBeanList.get(i).getName()+ "   "+Content.linkmanBeanList.get(i).phone.toString());
            }
        }
    }
}
