package com.example.lance.simplebox.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.lance.simplebox.DataBean.ChildBean;
import com.example.lance.simplebox.R;

import java.util.ArrayList;
import java.util.List;

import static org.jetbrains.anko.AsyncKt.runOnUiThread;

/**
 * Created by xiyu0 on 2017/11/1.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private  List<ChildBean> childArray;
    private List<String> groupArray;
    private ChildBean childBean;
    public ExpandableAdapter(Context context, List<ChildBean> childArray, List<String> groupArray) {
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
        return childArray.get(groupPosition).getFileName().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childArray.get(groupPosition);
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
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view=convertView;
        ChildHolder childHolder=null;
        childBean= (ChildBean) getChild(groupPosition,childPosition);
        if(view!=null){
            childHolder= (ChildHolder) view.getTag();
            childHolder.imageView2.setTag(childBean);
        }else {
            childHolder=new ChildHolder();
            view = LayoutInflater.from(context).inflate(R.layout.document_back_up_child,null);
            childHolder.imageView= (ImageView) view.findViewById(R.id.chlid_pic);
            childHolder.textView= (TextView) view.findViewById(R.id.child_text);
            childHolder.imageView2= (ImageView) view.findViewById(R.id.chlid_select);
            childHolder.relativeLayout= (RelativeLayout) view.findViewById(R.id.layout_child);
            view.setTag(childHolder);
            childHolder.imageView2.setTag(childBean);
        }

        final ChildHolder finalChildHolder = childHolder;
        childHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildBean childBean1 = (ChildBean) finalChildHolder.imageView2.getTag();
                if(childBean1.getSelect().get(childPosition)) {
                finalChildHolder.imageView2.setImageResource(R.mipmap.select1);
                    childBean1.setSelect(childPosition,false);
                }else{
                    finalChildHolder.imageView2.setImageResource(R.mipmap.select2);
                    childBean1.setSelect(childPosition,true);

                    Log.e("呵呵","又是这");
                }
            }
        });

        if(childBean.getSelect().get(childPosition)){
            childHolder.imageView2.setImageResource(R.mipmap.select2);
            Log.e("呵呵",childBean.getFileName().get(childPosition)+childBean.getSelect().get(childPosition)+"呵呵");
        }else {
            childHolder.imageView2.setImageResource(R.mipmap.select1);
            Log.e("呵呵2",childBean.getFileName().get(childPosition)+childBean.getSelect().get(childPosition)+"呵呵2");
        }
        if(groupPosition==0){
            childHolder.imageView.setImageResource(R.mipmap.word);
        }
        if(groupPosition==1){
            childHolder.imageView.setImageResource(R.mipmap.excel);
        }
        childHolder.textView.setText(childArray.get(groupPosition).getFileName().get(childPosition));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public List<ChildBean> getChildMessage(){
        List<ChildBean> childListBean=new ArrayList<ChildBean>();
        ChildBean childBean;
        List<String> temp=null;
        for(int i=0;i<2;i++){
            for(int j=0;j<childArray.get(i).getFileName().size();j++){
                childBean=new ChildBean();
                if(childArray.get(i).getSelect().get(j)){
                    Log.e("getchild",childArray.get(i).getFileName().get(j));
                    temp =new ArrayList<String>();
                    temp.add(childArray.get(i).getFileName().get(j));
                    childBean.setFileName(temp);
                    temp =new ArrayList<String>();
                    temp.add(childArray.get(i).getFileUri().get(j));
                    childBean.setFileUri(temp);
                    childListBean.add(childBean);
                    temp.clear();
                }
            }
        }
        return childListBean;
    }


     class ChildHolder{
        RelativeLayout relativeLayout;
        ImageView imageView;
        ImageView imageView2;
        TextView textView;
    }

     class GroupHolder{
        TextView textView;
        ImageView imageView;
    }

}
