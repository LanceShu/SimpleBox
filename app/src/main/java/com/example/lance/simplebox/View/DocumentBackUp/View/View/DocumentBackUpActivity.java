package com.example.lance.simplebox.View.DocumentBackUp.View.View;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.lance.simplebox.Adapter.ExpandableAdapter;
import com.example.lance.simplebox.DataBean.ChildBean;
import com.example.lance.simplebox.Mode.DocumenMode;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.Utils.UploadFileUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiyu0 on 2017/11/15.
 */

public class DocumentBackUpActivity extends AppCompatActivity implements View.OnClickListener,
        ExpandableListView.OnGroupClickListener,ExpandableListView.OnChildClickListener{
    private List<String> groupArray;
    private  List<ChildBean> childArray;
    private Button buttonCancel;
    private Button buttonBackUp;
    private Toolbar toolbar;
    private  ExpandableListView expandableListView ;
    private ExpandableAdapter expandableAdapter;

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
        ChildBean childBean1=new ChildBean();
        ChildBean childBean2=new ChildBean();
        childArray=new ArrayList<ChildBean>();
        List<Boolean>list1=new ArrayList<Boolean>();
        List<Boolean>list2=new ArrayList<Boolean>();
        List<String> listW = DocumenMode.getWordList(this);
        List<String> listE =DocumenMode.getExcelList(this);
        childBean1.setFileName(listW);
        childBean1.setFileUri(DocumenMode.getWordUriList(this));
        for(int i=0;i<listW.size();i++){
            list1.add(false);
        }
        childBean1.setSelect(list1);
        childBean2.setFileName(listE);
        childBean2.setFileUri(DocumenMode.getExcelUriList(this));
        for(int i=0;i<listE.size();i++){
            list2.add(false);
        }
        childBean2.setSelect(list2);
        childArray.add(childBean1);
        childArray.add(childBean2);
        groupArray = new  ArrayList<String>();
        groupArray.add("Word" );
        groupArray.add("Excel" );
        expandableAdapter=new ExpandableAdapter(DocumentBackUpActivity.this,childArray,groupArray);
        expandableListView.setAdapter(expandableAdapter);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cancel:
                finish();
            case R.id.back_up:
                List<ChildBean> childListBean =expandableAdapter.getChildMessage();
               // UploadFile uploadFile=new UploadFile(childListBean);
                UploadFileUtil uploadFile =new UploadFileUtil(childListBean);
                Thread thread =new Thread(uploadFile);
                thread.start();
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
