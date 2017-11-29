package com.example.lance.simplebox.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.lance.simplebox.DataBean.ImageBean;
import com.example.lance.simplebox.R;

import java.util.List;

/**
 * Created by Lance on 2017/11/29.
 */

public class FTFPictureAdapter extends RecyclerView.Adapter<FTFPictureAdapter.ViewHolder> {

    private List<ImageBean> pictures;
    private Context context;

    public FTFPictureAdapter(List<ImageBean> pictures,Context context){
        this.pictures = pictures;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ftf_pic_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ImageBean imageBean = pictures.get(pictures.size()-1-position);
        Glide.with(context).load(imageBean.getImagePath()).centerCrop().into(holder.imageView);
        if(imageBean.isSelected()){
            holder.isSelected.setImageResource(R.mipmap.ftf_select2);
        }else{
            holder.isSelected.setImageResource(R.mipmap.ftf_select1);
        }
        holder.pictureLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageBean.isSelected()){
                    holder.isSelected.setImageResource(R.mipmap.ftf_select1);
                    pictures.get(pictures.size()-1-position).setSelected(false);
                }else{
                    holder.isSelected.setImageResource(R.mipmap.ftf_select2);
                    pictures.get(pictures.size()-1-position).setSelected(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout pictureLayout;
        private ImageView imageView;
        private ImageView isSelected;

        public ViewHolder(View view) {
            super(view);
            pictureLayout = (RelativeLayout) view.findViewById(R.id.picture_item);
            imageView = (ImageView) view.findViewById(R.id.picture_icon);
            isSelected = (ImageView) view.findViewById(R.id.pic_idSelected);
        }
    }
}
