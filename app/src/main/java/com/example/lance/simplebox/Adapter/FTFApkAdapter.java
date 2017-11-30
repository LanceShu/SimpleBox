package com.example.lance.simplebox.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lance.simplebox.DataBean.ApkBean;
import com.example.lance.simplebox.DataBean.FileBean;
import com.example.lance.simplebox.R;

import java.util.List;

/**
 * Created by Lance on 2017/11/29.
 */

public class FTFApkAdapter extends RecyclerView.Adapter<FTFApkAdapter.ViewHolder> {

    private Context context;
    private List<ApkBean> apkBeans;

    public FTFApkAdapter(Context context, List<ApkBean> apkBeans){
        this.context = context;
        this.apkBeans = apkBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ftf_media_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ApkBean apkBean = apkBeans.get(apkBeans.size()-1-position);
        if(apkBean.getApkSelected()){
            holder.apkSelected.setImageResource(R.mipmap.ftf_select2);
        }else{
            holder.apkSelected.setImageResource(R.mipmap.ftf_select1);
        }

        holder.apkImage.setImageDrawable(apkBean.getApkIcon());

        holder.apkName.setText(apkBean.getApkName());
        holder.apkSize.setText(apkBean.getApkDate() + "  " + apkBean.getApkSize());
        holder.apkLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(apkBean.getApkSelected()){
                    holder.apkSelected.setImageResource(R.mipmap.ftf_select1);
                    apkBeans.get(apkBeans.size()-1-position).setApkSelected(false);
                }else{
                    holder.apkSelected.setImageResource(R.mipmap.ftf_select2);
                    apkBeans.get(apkBeans.size()-1-position).setApkSelected(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return apkBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout apkLayout;
        private ImageView apkSelected;
        private ImageView apkImage;
        private TextView apkName;
        private TextView apkSize;

        public ViewHolder(View view) {
            super(view);
            apkLayout = (LinearLayout) view.findViewById(R.id.media_layout);
            apkSelected = (ImageView) view.findViewById(R.id.media_isSeleted);
            apkImage = (ImageView) view.findViewById(R.id.media_image);
            apkName = (TextView) view.findViewById(R.id.media_name);
            apkSize = (TextView) view.findViewById(R.id.media_size);
        }
    }
}
