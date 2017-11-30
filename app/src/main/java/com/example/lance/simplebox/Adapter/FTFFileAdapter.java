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
import com.example.lance.simplebox.DataBean.AudioBean;
import com.example.lance.simplebox.DataBean.FileBean;
import com.example.lance.simplebox.R;

import java.util.List;

/**
 * Created by Lance on 2017/11/29.
 */

public class FTFFileAdapter extends RecyclerView.Adapter<FTFFileAdapter.ViewHolder> {

    private Context context;
    private List<FileBean> fileBeans;
    private int mipmapID;

    public FTFFileAdapter(Context context, List<FileBean> fileBeans ,int mipmapID){
        this.context = context;
        this.fileBeans = fileBeans;
        this.mipmapID = mipmapID;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ftf_media_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final FileBean fileBean = fileBeans.get(fileBeans.size()-1-position);
        if(fileBean.isFileSelected()){
            holder.fileSelected.setImageResource(R.mipmap.ftf_select2);
        }else{
            holder.fileSelected.setImageResource(R.mipmap.ftf_select1);
        }

        Glide.with(context)
                .load(mipmapID)
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                .into(holder.fileImage);

        holder.fileName.setText(fileBean.getFileName());
        holder.fileSize.setText(fileBean.getFileSize());
        holder.fileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fileBean.isFileSelected()){
                    holder.fileSelected.setImageResource(R.mipmap.ftf_select1);
                    fileBeans.get(fileBeans.size()-1-position).setFileSelected(false);
                }else{
                    holder.fileSelected.setImageResource(R.mipmap.ftf_select2);
                    fileBeans.get(fileBeans.size()-1-position).setFileSelected(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout fileLayout;
        private ImageView fileSelected;
        private ImageView fileImage;
        private TextView fileName;
        private TextView fileSize;

        public ViewHolder(View view) {
            super(view);
            fileLayout = (LinearLayout) view.findViewById(R.id.media_layout);
            fileSelected = (ImageView) view.findViewById(R.id.media_isSeleted);
            fileImage = (ImageView) view.findViewById(R.id.media_image);
            fileName = (TextView) view.findViewById(R.id.media_name);
            fileSize = (TextView) view.findViewById(R.id.media_size);
        }
    }
}
