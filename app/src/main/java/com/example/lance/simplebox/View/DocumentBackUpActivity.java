package com.example.lance.simplebox.View;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;


import com.example.lance.simplebox.Adapter.ExpandableAdapter;
import com.example.lance.simplebox.Content.Content;
import com.example.lance.simplebox.R;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiyu0 on 2017/10/31.
 */

public class DocumentBackUpActivity extends AppCompatActivity{
    private List<String> groupArray;
    private  List<List<String>> childArray;
    private Toolbar toolbar;
    private  ExpandableListView expandableListView ;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_back_up);
        initWidget();
        initData();
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setAdapter(new ExpandableAdapter(DocumentBackUpActivity.this,childArray,groupArray));
    }

    public void initWidget() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.font));
        toolbar.setTitle("文档备份");
    }

     public void initData(){
        groupArray = new  ArrayList<String>();    
        childArray = new  ArrayList<List<String>>();  
        groupArray.add("第一行" );
        groupArray.add("第二行" );  
        List<String> tempArray = new  ArrayList<String>();
        tempArray.add("第一条" );  
        tempArray.add("第二条" );  
        tempArray.add("第三条" );
        for (int  index = 0 ; index <groupArray.size(); ++index)  
        {  
            childArray.add(tempArray);  
        }
     }
}