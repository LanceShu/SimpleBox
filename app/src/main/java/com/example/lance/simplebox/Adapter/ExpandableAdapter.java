package com.example.lance.simplebox.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.lance.simplebox.DataBean.StrBean;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.View.DocumentBackUpActivity;

import java.util.List;

import static org.jetbrains.anko.AsyncKt.runOnUiThread;

/**
 * Created by xiyu0 on 2017/11/1.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private  List<List<String>> childArray;
    private List<String> groupArray;
    static int flag=1;
    public ExpandableAdapter(Context context, List<List<String>> childArray, List<String> groupArray) {
        this.context=context;
        this.childArray=childArray;
        this.groupArray=groupArray;
    }


    @Override
    public int getGroupCount() {
        return groupArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childArray.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childArray.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {//获取子元素id
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view=convertView;
        GroupHolder groupholder = null;
        if(view!=null){
            groupholder = (GroupHolder) view.getTag();
        }else{
            groupholder=new GroupHolder();
            view = LayoutInflater.from(context).inflate(R.layout.document_back_up_group,parent,false);
            groupholder.textView = (TextView) view.findViewById(R.id.textView);
            view.setTag(groupholder);
        }
        groupholder.textView.setText(groupArray.get(groupPosition));
        return view;
    }

    @Override   //加载子元素并显示
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view=convertView;
        ChildHolder childHolder=null;
        if(view!=null){
            childHolder= (ChildHolder) view.getTag();
        }else {
            childHolder=new ChildHolder();
            view = LayoutInflater.from(context).inflate(R.layout.document_back_up_child,null);
            childHolder.imageView= (ImageView) view.findViewById(R.id.chlid_pic);
            childHolder.textView= (TextView) view.findViewById(R.id.child_text);
            childHolder.imageView2= (ImageView) view.findViewById(R.id.chlid_select);
            childHolder.relativeLayout= (RelativeLayout) view.findViewById(R.id.layout_child);
            view.setTag(childHolder);
        }
        final ChildHolder finalChildHolder = childHolder;
        finalChildHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==1){
                    finalChildHolder.imageView2.setImageResource(R.mipmap.select2);
                    flag=2;
                }else{
                    finalChildHolder.imageView2.setImageResource(R.mipmap.select1);
                    flag=1;
                }
            }
        });
        if(groupPosition==0){
            childHolder.imageView.setImageResource(R.mipmap.word);
        }
        if(groupPosition==1){
            childHolder.imageView.setImageResource(R.mipmap.excel);
        }
        childHolder.textView.setText(childArray.get(groupPosition).get(childPosition));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class ChildHolder{
        RelativeLayout relativeLayout;
        ImageView imageView;
        ImageView imageView2;
        TextView textView;
    }

    static class GroupHolder{
        TextView textView;
        ImageView imageView;
    }

}
