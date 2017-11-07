package com.example.lance.simplebox.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.Toast;


import com.example.lance.simplebox.Adapter.ExpandableAdapter;
import com.example.lance.simplebox.Content.Content;
import com.example.lance.simplebox.DataBean.StrBean;
import com.example.lance.simplebox.Mode.DocumenMode;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.Utils.FileUtils;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiyu0 on 2017/10/31.
 */

public class DocumentBackUpActivity extends AppCompatActivity implements View.OnClickListener,
        ExpandableListView.OnGroupClickListener,ExpandableListView.OnChildClickListener{
    private List<String> groupArray;
    private  List<List<String>> childArray;
    private Button buttonCancel;
    private Button buttonBackUp;
    private Toolbar toolbar;
    private  ExpandableListView expandableListView ;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_back_up);
        initWidget();
        initData();

    }

    public void initWidget() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.font));
        toolbar.setTitle("文档备份");
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnGroupClickListener(this);
        expandableListView.setOnChildClickListener(this);
        buttonCancel= (Button) findViewById(R.id.cancel);
        buttonBackUp= (Button) findViewById(R.id.back_up);
        buttonCancel.setOnClickListener(this);
        buttonBackUp.setOnClickListener(this);
    }


     public void initData(){
        childArray=new ArrayList<List<String>>();
        childArray.add(DocumenMode.getWordList(this));
        childArray.add(DocumenMode.getExcelList(this));
        groupArray = new  ArrayList<String>();
        groupArray.add("Word" );
        groupArray.add("Excel" );
        expandableListView.setAdapter(new ExpandableAdapter(DocumentBackUpActivity.this,childArray,groupArray));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cancel:
                finish();
            case R.id.back_up:

        }
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        if(expandableListView.isGroupExpanded(groupPosition)){
            expandableListView.collapseGroup(groupPosition);
        }
        else{
            expandableListView.expandGroup(groupPosition,false);
        }
        return true;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

        return true;
    }
}